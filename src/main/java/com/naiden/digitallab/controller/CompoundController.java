package com.naiden.digitallab.controller;

import com.naiden.digitallab.model.Compound;
import com.naiden.digitallab.service.CompoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/compounds")
public class CompoundController {

    @Autowired
    private CompoundService compoundService;

    @RequestMapping("/add-compound")
    public ModelAndView addNewCompound(Map<String, Object> model) {
        if (!model.containsKey("compound")) {
            model.put("compound", new Compound());
        }
        return new ModelAndView("add-compound", model);
    }

    @RequestMapping("/view-compounds")
    public String viewAllCompounds(Map<String, Object> model) {
        Iterable<Compound> compounds = compoundService.findAll();
        model.put("compounds", compounds);
        return "view-compounds";
    }

    @RequestMapping(value = "/add-compound", method = RequestMethod.POST, params = "action=save")
    public ModelAndView saveNewCompound(Compound compound) {
        try {
            compoundService.save(compound);
            return new ModelAndView("redirect:/compounds/view-compounds");
        } catch (Exception ex) {
            ModelAndView mav = new ModelAndView("add-compound");
            mav.addObject("compound", compound);
            mav.addObject("message", "Can't save compound. Probably this compound exists in DB!");
            return mav;
        }
    }

    @RequestMapping(value = "/add-compound", method = RequestMethod.POST, params = "action=find-by-smiles")
    public ModelAndView findBySmiles(Compound compound) throws IOException {
        compound.setIupacName("");
        compound.setCid("");
        compound.setShortName("");
        compoundService.setCompoundInfoBySmiles(compound.getSmiles(), compound);
        Map<String, Object> model = new HashMap<>();
        model.put("compound", compound);
        return new ModelAndView("add-compound", model);
    }

    @RequestMapping(value = "{id}/delete")
    public ModelAndView deleteById(@PathVariable Long id) {
        compoundService.deleteById(id);
        return new ModelAndView("redirect:/compounds/view-compounds");
    }

    @RequestMapping(value = "{id}/edit")
    public ModelAndView editById(@PathVariable Long id) {
        Compound compound = compoundService.findById(id);
        Map<String, Object> model = new HashMap<>();
        model.put("compound", compound);
        return addNewCompound(model);
    }

    @RequestMapping(value = "{id}/edit", params = "action=save")
    public ModelAndView saveById(@PathVariable Long id, Compound compound) {
        compoundService.update(compound);
        return new ModelAndView("redirect:/compounds/view-compounds");
    }

}
