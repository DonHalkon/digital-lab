package com.naiden.digitallab.service;

import com.naiden.digitallab.model.Compound;
import com.naiden.digitallab.repository.CompoundRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IMolecularFormula;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.tools.manipulator.MolecularFormulaManipulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@Service
public class CompoundService {

    @Autowired
    private CompoundRepository compoundRepository;

    public Iterable<Compound> findAll() {
        return compoundRepository.findAll();
    }

    public Compound findById(Long id) {
        return compoundRepository.findOne(id);
    }

    public Compound save(Compound compound) throws Exception {
        Compound savedCompound;
        // try to find additional info in web
        String smiles = compound.getSmiles();
        if (!compound.getSmiles().isEmpty()) {
            try {
                setCompoundInfoBySmiles(smiles, compound);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String iupacName = compound.getIupacName();
        setMolecularFormulaBySmiles(compound);
        if (compound.getShortName().isEmpty() && !iupacName.isEmpty()) compound.setShortName(iupacName);
        return compoundRepository.save(compound);
    }

    public Compound setCompoundInfoBySmiles(String smiles, Compound compound) throws IOException {
        String requestURL = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/smiles/" + URLEncoder.encode(smiles, "UTF-8") + "/property/IUPACName/JSON";
        HttpURLConnection connection = getHttpURLConnection(requestURL);
        setCompoundIupacNameAndCid(compound, connection);
        return compound;
    }

    public void setCompoundIupacNameAndCid(Compound compound, HttpURLConnection connection) throws IOException {
        if (200 == connection.getResponseCode()) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject json = new JSONObject(response.toString());

            try {
                JSONArray jsonArray = json.getJSONObject("PropertyTable").getJSONArray("Properties");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject o = jsonArray.getJSONObject(i);
                    compound.setCid(o.get("CID").toString());
                    compound.setIupacName(o.get("IUPACName").toString());
                }
            } catch (Exception e) {
                // FIXME: What kind of Exception? What can we do here?
            }
        }
    }

    public void setMolecularFormulaBySmiles(Compound compound) {
        if (!compound.getFormula().isEmpty()) return;
        try {
            SmilesParser sp = new SmilesParser(SilentChemObjectBuilder.getInstance());
            IAtomContainer mol = sp.parseSmiles(compound.getSmiles());
            IMolecularFormula molecularFormula = MolecularFormulaManipulator.getMolecularFormula(mol);
            compound.setFormula(MolecularFormulaManipulator.getString(molecularFormula));
        } catch (InvalidSmilesException e) {
            e.printStackTrace();
        }
    }

    public HttpURLConnection getHttpURLConnection(String requestURL) throws IOException {
        URL url = new URL(requestURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }

    public void deleteById(Long aLong) {
        compoundRepository.delete(aLong);
    }

    public void update(Compound compound) {
        compoundRepository.save(compound);
    }

}
