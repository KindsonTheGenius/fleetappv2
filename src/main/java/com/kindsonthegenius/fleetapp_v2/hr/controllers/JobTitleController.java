package com.kindsonthegenius.fleetapp_v2.hr.controllers;

import com.kindsonthegenius.fleetapp_v2.hr.models.JobTitle;
import com.kindsonthegenius.fleetapp_v2.hr.repositories.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class parametersController {
    @Autowired
    private JobTitleRepository jobTitleRepository;

    @GetMapping("/hr/parameters")
    public String parameters(Model model){
        List<JobTitle> jobTitles = jobTitleRepository.findAll();
        model.addAttribute("jobTitles", jobTitles);
        return "hr/parameters";
    }

    //Get Job Title by id
    @GetMapping("/hr/parameters/jobTitle/{id}")
    @ResponseBody
    public JobTitle getById(@PathVariable Integer id){
        return jobTitleRepository.findById(id).orElse(null);
    }

    @PostMapping("/hr/parameters/jobTitles")
    public String save(JobTitle jobTitle){
        jobTitleRepository.save(jobTitle);
        return "redirect:/hr/parameters";
    }

    @RequestMapping(value="/hr/parameters/jobTitle/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        jobTitleRepository.deleteById(id);
        return "redirect:/hr/parameters";
    }



}
