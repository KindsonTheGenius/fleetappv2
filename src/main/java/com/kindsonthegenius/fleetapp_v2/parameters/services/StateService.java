package com.kindsonthegenius.fleetapp_v2.parameters.services;

import com.kindsonthegenius.fleetapp_v2.parameters.models.State;
import com.kindsonthegenius.fleetapp_v2.parameters.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;
	
	//Get All States
	public List<State> findAll(){
		return stateRepository.findAll();
	}	
	
	//Get State By Id
	public State findById(int id) {
		return stateRepository.findById(id).orElse(null);
	}

	//Get State By Country id
	public List<State > findByCountryid(int countryid) {
		return stateRepository.getAllByCountryid(countryid);
	}

	//Delete State
	public void delete(int id) {
		stateRepository.deleteById(id);
	}
	
	//Update State
	public void save( State state) {
		stateRepository.save(state);
	}
}
