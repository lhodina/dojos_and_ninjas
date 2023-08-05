package com.codingdojo.dojosandninjas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.dojosandninjas.models.Dojo;
import com.codingdojo.dojosandninjas.models.Ninja;
import com.codingdojo.dojosandninjas.services.DojoService;
import com.codingdojo.dojosandninjas.services.NinjaService;

import jakarta.validation.Valid;

@Controller
public class DojoController {
	@Autowired
	DojoService dojoService;
	
	@Autowired
	NinjaService ninjaService;
	
	@GetMapping("/dojos/new")
	public String getDojoForm(@ModelAttribute("dojo") Dojo dojo) {
		return "newDojo.jsp";
	}
	
	@PostMapping("/dojos")
	public String createDojo(
		Model model,
		@Valid @ModelAttribute("dojo") Dojo dojo, 
		BindingResult result) {
		if (result.hasErrors()) {
			return "newDojo.jsp";
		} else {
			Dojo newDojo = dojoService.createDojo(dojo);
			Long newDojoId = newDojo.getId();
			return "redirect:/dojos/" + newDojoId;
		}
	}
	
	@GetMapping("/dojos/{dojoId}")
	public String showDojo(Model model, @PathVariable("dojoId") Long dojoId) {
		Dojo dojo = dojoService.findDojo(dojoId);
		model.addAttribute("dojo", dojo);
		return "show.jsp";
	}
	
	@GetMapping("/ninjas/new")
	public String getNinjaForm(
			Model model,
			@ModelAttribute("ninja") Ninja ninja) {
			model.addAttribute("dojos", dojoService.allDojos());
		return "newNinja.jsp";
	}
	
	@PostMapping("/ninjas/new")
	public String addNinja(
			@Valid @ModelAttribute("ninja") Ninja ninja,
			BindingResult result, 
			Model model) {
		if(result.hasErrors()){
			return "newNinja.jsp";
		}else{
			Ninja newNinja = ninjaService.createNinja(ninja);
			Dojo ninjaDojo = newNinja.getDojo();
			Long dojoId = ninjaDojo.getId();
			return "redirect:/dojos/" + dojoId;
		}
	}
}
