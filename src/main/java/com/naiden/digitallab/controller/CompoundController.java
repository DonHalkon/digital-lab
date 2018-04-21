package com.naiden.digitallab.controller;

import com.naiden.digitallab.model.Compound;
import com.naiden.digitallab.service.CompoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(path = "/compounds")
public class CompoundController {

    @Autowired
    private CompoundService compoundService;

    @GetMapping("/view-compounds")
    public String viewAllCompounds(Map<String, Object> model) {
        model.put("compounds", compoundService.findAll());
        return "view-compounds";
    }

    @GetMapping("/add-new-compound")
    public String addNewCompound(Model model) {
        model.addAttribute("compound", new Compound());
        return "edit-compound";
    }

    @GetMapping("/edit-compound")
    public String editCompound() {
        return "edit-compound";
    }

    @GetMapping("/edit-compound/{id}")
    public String editCompoundById(@PathVariable Long id, Model model) {
        model.addAttribute("compound", compoundService.findById(id));
        return "edit-compound";
    }

    @PostMapping(value = "/edit-compound")
    public String editCompound(@Valid Compound compound, Errors errors, RedirectAttributes attributes) {
        String messageColor = "alert-success";
        String message = "Saved!";
        if (!errors.hasErrors()) {
            try {
                compoundService.save(compound);
            } catch (DataAccessException ex) {
                attributes.addFlashAttribute(compound);
                attributes.addFlashAttribute("message", "Can't save compound. Probably compound with the same name/CID/structure exists in DB!");
                attributes.addFlashAttribute("messageColor", "alert-danger");
                return "redirect:edit-compound";
            }
        } else {
            message = errors.getFieldErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .reduce((x, y) -> x + "\n" + y).get(); //checked in "if" condition
            messageColor = "alert-danger";
        }
        attributes.addFlashAttribute("message", message);
        attributes.addFlashAttribute("messageColor", messageColor);
        if (compound.getId() != null) return "redirect:view-compounds";
        return "redirect:/compounds/add-new-compound";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        compoundService.deleteById(id);
        return "redirect:/compounds/view-compounds";
    }
}
