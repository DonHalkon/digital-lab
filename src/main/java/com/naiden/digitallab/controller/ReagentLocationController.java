package com.naiden.digitallab.controller;

import com.naiden.digitallab.model.ReagentLocation;
import com.naiden.digitallab.repository.ReagentLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping(path = "/reagentlocations")
public class ReagentLocationController {

    private final String ADD_REAGENTLOCATION = "add-reagentlocation";

    private final String VIEW_REAGENTLOCATIONS = "view-reagentlocations";

    private final ReagentLocationRepository reagentLocationRepository;

    @Autowired
    public ReagentLocationController(ReagentLocationRepository reagentLocationRepository) {
        this.reagentLocationRepository = reagentLocationRepository;
    }

    @RequestMapping(ADD_REAGENTLOCATION)
    public String addNewReagentLocation(Model model) {
        model.addAttribute("location", new ReagentLocation());
        return ADD_REAGENTLOCATION;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveReagentNewLocation(ReagentLocation reagentLocation) {
        reagentLocationRepository.save(reagentLocation);
        return "redirect:" + VIEW_REAGENTLOCATIONS;
    }

    @RequestMapping(VIEW_REAGENTLOCATIONS)
    public String viewAllLocations(Map<String, Object> model) {
        Iterable<ReagentLocation> locations = reagentLocationRepository.findAll();
        model.put("locations", locations);
        return VIEW_REAGENTLOCATIONS;
    }
}
