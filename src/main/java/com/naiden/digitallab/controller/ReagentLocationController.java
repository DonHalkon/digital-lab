package com.naiden.digitallab.controller;

import com.naiden.digitallab.model.ReagentLocation;
import com.naiden.digitallab.repository.ReagentLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/reagentlocations")
public class ReagentLocationController {

    @Autowired
    private ReagentLocationRepository reagentLocationRepository;

    @RequestMapping("/add-reagentlocation")
    public ModelAndView addNewReagentLocation() {
        Map<String, Object> model = new HashMap<>();
        model.put("location", new ReagentLocation());
        return new ModelAndView("add-reagentlocation", model);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveReagentNewLocation(ReagentLocation reagentLocation) {
        reagentLocationRepository.save(reagentLocation);
        return "redirect:/reagentlocations/view-reagentlocations";
    }

    @RequestMapping("/view-reagentlocations")
    public String viewAllLocations(Map<String, Object> model) {
        Iterable<ReagentLocation> locations = reagentLocationRepository.findAll();
        model.put("locations", locations);
        return "view-reagentlocations";
    }
}
