package com.naiden.digitallab.controller;

import com.naiden.digitallab.model.Compound;
import com.naiden.digitallab.model.Reagent;
import com.naiden.digitallab.repository.CompoundRepository;
import com.naiden.digitallab.repository.ReagentRepository;
import com.naiden.digitallab.service.ReagentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.Calendar;
import java.util.Map;

@Controller
@RequestMapping(path="/reagents")
public class ReagentController {

    @Autowired
    private ReagentRepository reagentRepository;

    @Autowired
    private CompoundRepository compoundRepository;

    @GetMapping(path="/api/add-reagent")
    public @ResponseBody String addNewReagentApi (@RequestParam String comments, @RequestParam Long compoundId) {
        Reagent reagent = new Reagent();
        reagent.setComments(comments);
        reagent.setReceiptDate(new Date(Calendar.getInstance().getTime().getTime()));
        reagent.setCompound(compoundRepository.findOne(compoundId));
        reagentRepository.save(reagent);
        return "Reagent Saved";
    }

    @GetMapping(path="/api/add-compound")
    public @ResponseBody String addNewCompound (@RequestParam String cas, @RequestParam String name) {
        Compound compound = new Compound();
        compound.setCas(cas);
        compound.setName(name);
        compoundRepository.save(compound);
        return "Compound Saved";
    }

    @GetMapping(path="/api/get-all")
    public @ResponseBody Iterable<Reagent> getAllReagents() {
        // This returns a JSON or XML with the reagents
        return reagentRepository.findAll();
    }

    @RequestMapping(value = {"", "/", "/main"})
    public String main(Map<String, Object> model) {
        Iterable<Reagent> reagents = reagentRepository.findAll();
        model.put("reagents", reagents);
        return "main";
    }

    @RequestMapping("/add-new-reagent")
    public ModelAndView addNewReagent(){
        return new ModelAndView("addreagent","command", new Reagent());
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNewReagent(Reagent reagent){
        reagentRepository.save(reagent);
        return "redirect:main";
    }

}