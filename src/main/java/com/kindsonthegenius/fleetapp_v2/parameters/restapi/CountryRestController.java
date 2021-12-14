package com.kindsonthegenius.fleetapp_v2.parameters.restapi;

import com.kindsonthegenius.fleetapp_v2.parameters.models.Country;
import com.kindsonthegenius.fleetapp_v2.parameters.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryRestController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/api/parameters/countries")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Country> getAll(){
        List<Country> countries =   countryService.findAll();
        return countries;
    }
}
