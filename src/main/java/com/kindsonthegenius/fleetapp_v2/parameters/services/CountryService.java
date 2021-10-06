package com.kindsonthegenius.fleetmsv2.parameters.services;

import com.kindsonthegenius.fleetmsv2.parameters.models.Country;
import com.kindsonthegenius.fleetmsv2.parameters.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAll(){
        return countryRepository.findAll();
    }
}
