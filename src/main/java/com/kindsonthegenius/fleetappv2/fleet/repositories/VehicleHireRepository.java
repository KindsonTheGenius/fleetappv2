package com.kindsonthegenius.fleetappv2.fleet.repositories;

import com.kindsonthegenius.fleetappv2.fleet.models.VehicleHire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleHireRepository extends JpaRepository<VehicleHire, Integer> {

}
