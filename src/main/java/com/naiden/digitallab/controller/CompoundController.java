package com.naiden.digitallab.controller;

import com.naiden.digitallab.model.Compound;
import com.naiden.digitallab.repository.CompoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/compounds")
public class CompoundController {

    @Autowired
    private CompoundRepository compoundRepository;

    @RequestMapping("/add-new-compound")
    public ModelAndView addNewCompound(){
        return new ModelAndView("addcompound","command", new Compound());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNewCompound(Compound compound){
        compoundRepository.save(compound);
        return "redirect:/reagents/main";
    }
}
