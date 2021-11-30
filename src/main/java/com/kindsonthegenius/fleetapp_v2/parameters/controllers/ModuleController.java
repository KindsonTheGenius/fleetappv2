package com.kindsonthegenius.fleetapp_v2.parameters.controllers;
import com.kindsonthegenius.fleetapp_v2.parameters.models.Module;

import com.kindsonthegenius.fleetapp_v2.parameters.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/parameters/modules")
    public String parameters(Model model){
        List<Module> modules = moduleService.findAll();
        model.addAttribute("modules", modules);
        return "/parameters/modules";
    }

    //Get Job Title by id
    @GetMapping("/parameters/module/{id}")
    @ResponseBody
    public Module getById(@PathVariable Integer id){
        return moduleService.findById(id);
    }

    @PostMapping("/parameters/modules")
    public String save(Module module){
        moduleService.save(module);
        return "redirect:/parameters/modules";
    }

    @RequestMapping(value="/parameters/module/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        moduleService.delete(id);
        return "redirect:/parameters/modules";
    }

}
