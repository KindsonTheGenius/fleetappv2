package com.kindsonthegenius.fleetapp_v2.parameters.controllers;

import com.kindsonthegenius.fleetapp_v2.parameters.models.Department;
import com.kindsonthegenius.fleetapp_v2.parameters.services.CountryService;
import com.kindsonthegenius.fleetapp_v2.parameters.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired private CountryService countryService;

    //Get All Departments
    @GetMapping("parameters/departments")
    public String findAll(Model model){
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("countries", countryService.findAll());
        return "departments";
    }

    @RequestMapping("departments/findById")
    @ResponseBody
    public Optional<Department> findById(Integer id)
    {
        return departmentService.findById(id);
    }

    //Add Department
    @PostMapping(value="departments/addNew")
    public String addNew(Department department) {
        departmentService.save(department);
        return "redirect:/departments";
    }

    @RequestMapping(value="departments/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(Department department) {
        departmentService.save(department);
        return "redirect:/departments";
    }

    @RequestMapping(value="departments/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Integer id) {
        departmentService.delete(id);
        return "redirect:/departments";
    }
}
