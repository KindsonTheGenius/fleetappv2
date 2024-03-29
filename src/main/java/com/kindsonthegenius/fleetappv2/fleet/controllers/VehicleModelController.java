package com.kindsonthegenius.fleetappv2.fleet.controllers;

import java.util.Optional;

import com.kindsonthegenius.fleetappv2.fleet.models.VehicleModel;
import com.kindsonthegenius.fleetappv2.fleet.services.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleModelController {

	@Autowired private VehicleModelService vehicleModelService;
	
	//Get All VehicleModels
	@GetMapping("fleet/vehicleModels")
	public String findAll(Model model){		
		model.addAttribute("vehicleModels", vehicleModelService.findAll());
		return "/fleet/vehicleModels";
	}	
	
	@RequestMapping("/fleet/vehicleModel/{id}")
	@ResponseBody
	public Optional<VehicleModel> findById(@PathVariable Integer id)
	{
		return vehicleModelService.findById(id);
	}
	
	//Add VehicleModel
	@PostMapping(value="/fleet/vehicleModels")
	public String addNew(VehicleModel vehicleModel) {
		vehicleModelService.save(vehicleModel);
		return "redirect:/fleet/vehicleModels";
	}
	
	@RequestMapping(value="vehicleModel/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		vehicleModelService.delete(id);
		return "redirect:/fleet/vehicleModels";
	}
}
