package com.kindsonthegenius.fleetapp_v2.parameters.controllers;

import java.util.List;
import java.util.Optional;

import com.kindsonthegenius.fleetapp_v2.fleet.models.Vehicle;
import com.kindsonthegenius.fleetapp_v2.parameters.models.Location;
import com.kindsonthegenius.fleetapp_v2.parameters.services.CountryService;
import com.kindsonthegenius.fleetapp_v2.parameters.services.LocationService;
import com.kindsonthegenius.fleetapp_v2.parameters.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LocationController {
	
	@Autowired	private LocationService locationService;
	@Autowired	private CountryService countryService;
	@Autowired	private StateService stateService;

	public Model addModelAttributes(Model model){
		model.addAttribute("locations", locationService.findAll());
		model.addAttribute("countries", countryService.findAll());
		model.addAttribute("states", stateService.findAll());
		return model;
	}
	
	@GetMapping("/parameters/locations")
	public String findAll(Model model){
		addModelAttributes(model);
		return "/parameters/locations";
	}

	@GetMapping("/parameters/locationAdd")
	public String addLocation(Model model){
		model.addAttribute("countries", countryService.findAll());
		return "parameters/locationAdd";
	}

	//The op parameter is either Edit or Details
	@GetMapping("/parameters/location/{op}/{id}")
	public String editLocation(@PathVariable Integer id, @PathVariable String op, Model model){
		Location location = locationService.findById(id);
		model.addAttribute("location", location);
		addModelAttributes(model);
		return "/parameters/location"+ op; //returns locationEdit or locationDetails
	}

	@PostMapping("/parameters/locations")
	public String save(Location location) {
		locationService.save(location);
		return "redirect:/parameters/locations";
	}
	
	@RequestMapping(value="/parameters/locations/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteById(@PathVariable Integer id) {
		locationService.deleteById(id);
		return "redirect:/parameters/locations";
	}
	
}
