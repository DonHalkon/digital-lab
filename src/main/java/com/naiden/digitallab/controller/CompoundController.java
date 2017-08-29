package com.naiden.digitallab.controller;

import com.naiden.digitallab.model.Compound;
import com.naiden.digitallab.model.Reagent;
import com.naiden.digitallab.service.CompoundService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/compounds")
public class CompoundController {

    @Autowired
    private CompoundService compoundService;

    @RequestMapping("/add-new-compound")
    public ModelAndView addNewCompound() {
        Map<String, Object> model = new HashMap<>();
        model.put("compound", new Compound());
        return new ModelAndView("addCompound", model);
    }

    @RequestMapping("/all-compounds")
    public String viewAllCompunds(Map<String, Object> model) {
        Iterable<Compound> compounds = (Iterable<Compound>) compoundService.findAll();
        model.put("compounds", compounds);
        return "all-compounds";
    }

    @RequestMapping(value = "/add-new-compound", method = RequestMethod.POST, params="action=save" )
    public String saveNewCompound(Compound compound) {
        compoundService.save(compound);
        return "redirect:/compounds/all-compounds";
    }

    @RequestMapping(value = "/add-new-compound", method = RequestMethod.POST, params="action=find-by-smiles" )
    public ModelAndView findBySmiles(Compound compound) throws IOException {

        String smiles = compound.getSmiles();
        compound = new Compound();
        compound.setSmiles(smiles);
        smiles = URLEncoder.encode(smiles, "UTF-8");

        String requestURL = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/smiles/" + smiles +"/property/IUPACName,MolecularFormula/JSON";

        URL url = new URL(requestURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        if (200 == connection.getResponseCode()){

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());

            JSONArray jsonArray = json.getJSONObject("PropertyTable").getJSONArray("Properties");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject o = jsonArray.getJSONObject(i);
                compound.setCid(o.get("CID").toString());
//            compound.setCid(o.getString("MolecularFormula");
                compound.setIupacName(o.get("IUPACName").toString());
            }
        }


        Map<String, Object> model = new HashMap<>();
        model.put("compound", compound);
        return new ModelAndView("addCompound", model);
    }

}
