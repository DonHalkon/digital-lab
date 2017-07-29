package com.naiden.digitallab.controller;

import com.naiden.digitallab.model.Location;
import com.naiden.digitallab.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/locations")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @RequestMapping("/add-new-location")
    public ModelAndView addNewLocation(){
        return new ModelAndView("addlocation","command", new Location());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNewLocation(Location location){
        locationRepository.save(location);
        return "redirect:/reagents/main";
    }
}
