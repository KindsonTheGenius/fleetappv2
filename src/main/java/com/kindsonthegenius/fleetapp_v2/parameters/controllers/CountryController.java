package com.kindsonthegenius.fleetmsv2.parameters.controllers;

import com.kindsonthegenius.fleetmsv2.parameters.models.Country;
import com.kindsonthegenius.fleetmsv2.parameters.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parameters")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/countries")
    public List<Country> getAll(){
        return countryService.getAll();
    }
}
