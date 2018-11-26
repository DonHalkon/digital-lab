package com.naiden.digitallab.controller;

import com.naiden.digitallab.model.Reagent;
import com.naiden.digitallab.repository.CompoundRepository;
import com.naiden.digitallab.repository.ReagentLocationRepository;
import com.naiden.digitallab.repository.ReagentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(path = "/reagents")
public class ReagentController {

    private final String VIEW_REAGENTS = "view-reagents";

    private final String ADD_REAGENT = "add-reagent";

    private final ReagentRepository reagentRepository;

    private final CompoundRepository compoundRepository;

    private final ReagentLocationRepository reagentLocationRepository;

    @Autowired
    public ReagentController(ReagentRepository reagentRepository, CompoundRepository compoundRepository, ReagentLocationRepository reagentLocationRepository) {
        this.reagentRepository = reagentRepository;
        this.compoundRepository = compoundRepository;
        this.reagentLocationRepository = reagentLocationRepository;
    }

    @GetMapping(VIEW_REAGENTS)
    public String main(Map<String, Object> model) {
        Iterable<Reagent> reagents = reagentRepository.findAll();
        model.put("reagents", reagents);
        return VIEW_REAGENTS;
    }

    @RequestMapping(ADD_REAGENT)
    public String addNewReagent(Map<String, Object> model) {
        model.put("reagent", new Reagent());
        model.put("compounds", compoundRepository.findAll());
        model.put("reagentlocations", reagentLocationRepository.findAll());
        return ADD_REAGENT;
    }

    @PostMapping(value = "/save")
    public String saveNewReagent(Reagent reagent) {
        reagentRepository.save(reagent);
        return "redirect:" + VIEW_REAGENTS;
    }

}