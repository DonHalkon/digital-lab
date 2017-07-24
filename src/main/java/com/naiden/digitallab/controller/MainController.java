package com.naiden.digitallab.controller;

import com.naiden.digitallab.model.Compound;
import com.naiden.digitallab.model.Reagent;
import com.naiden.digitallab.repository.CompoundRepository;
import com.naiden.digitallab.repository.ReagentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.Calendar;

@Controller
@RequestMapping(path="/demo")
public class MainController {

    @Autowired
    private ReagentRepository reagentRepository;

    @Autowired
    private CompoundRepository compoundRepository;

    @GetMapping(path="/add_reagent")
    public @ResponseBody String addNewReagent (@RequestParam String comments, @RequestParam Long compoundId) {
        Reagent reagent = new Reagent();
        reagent.setComments(comments);
        reagent.setReceiptDate(new Date(Calendar.getInstance().getTime().getTime()));
        reagent.setCompound(compoundRepository.findOne(compoundId));
        reagentRepository.save(reagent);
        return "Reagent Saved";
    }

    @GetMapping(path="/add_compound")
    public @ResponseBody String addNewCompound (@RequestParam String cas, @RequestParam String name) {
        Compound compound = new Compound();
        compound.setCas(cas);
        compound.setName(name);
        compoundRepository.save(compound);
        return "Compound Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Reagent> getAllReagents() {
        // This returns a JSON or XML with the reagents
        return reagentRepository.findAll();
    }
}