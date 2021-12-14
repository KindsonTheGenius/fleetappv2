package com.kindsonthegenius.fleetapp_v2.parameters.services;

import com.kindsonthegenius.fleetapp_v2.parameters.models.Country;
import com.kindsonthegenius.fleetapp_v2.parameters.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> findAll(){
        return countryRepository.findAll();
    }

    public  void save(Country country){
        countryRepository.save(country);
    }

    public void delete(Integer id) {
        countryRepository.deleteById(id);
    }

    public Country getById(Integer id) {
        return countryRepository.findById(id).orElse(null);
    }

    public void update(Country country) {
        countryRepository.save(country);
    }

    public List<Country> findByKeyword(String keyword){
        return countryRepository.findByKeyword(keyword);
    }

    public List<Country> findCountryWithSorting(String field){
        return countryRepository.findAll(Sort.by(field));
    }

    public List<Country> findCountryWithSorting2(String field, String direction){
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending():Sort.by(field).descending();

        return countryRepository.findAll(sort);
    }
}
