package com.naiden.digitallab.controller;

import com.naiden.digitallab.model.Location;
import com.naiden.digitallab.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/locations")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @RequestMapping("/add-new-location")
    public ModelAndView addNewLocation() {
        Map<String, Object> model = new HashMap<>();
        model.put("location", new Location());
        return new ModelAndView("addlocation", model);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNewLocation(Location location) {
        locationRepository.save(location);
        return "redirect:/reagents/main";
    }

    @RequestMapping("/view-locations")
    public String viewAllLocations(Map<String, Object> model) {
        Iterable<Location> locations = locationRepository.findAll();
        model.put("locations", locations);
        return "view-locations";
    }
}
