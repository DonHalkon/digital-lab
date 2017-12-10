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

    @RequestMapping("/view-compounds")
    public String viewAllCompounds(Map<String, Object> model) {
        Iterable<Compound> compounds = compoundService.findAll();
        model.put("compounds", compounds);
        return "view-compounds";
    }

    @RequestMapping("/edit-compound/{id}" )
    public ModelAndView editCompound(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("edit-compound");
        Compound compound = (id == 0) ? new Compound() : compoundService.findById(id);
        mav.addObject("compound", compound);
        return mav;
    }

    @RequestMapping(value = "/edit-compound/save", method = RequestMethod.POST)
    public ModelAndView editCompound(Compound compound) {
        String messageColor;
        String message;
        try {
            compoundService.save(compound);
            message = "Saved!";
            messageColor = "alert-success";
        } catch (Exception ex) {
            message = "Can't save compound. Probably this compound exists in DB!";
            messageColor = "alert-danger";
        }
        ModelAndView mav = new ModelAndView("edit-compound");
        mav.addObject("compound", compound).
                addObject("message", message).
                addObject("messageColor", messageColor);
        return mav;
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelAndView deleteById(@PathVariable Long id) {
        compoundService.deleteById(id);
        return new ModelAndView("redirect:/compounds/view-compounds");
    }

}
