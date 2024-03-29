package com.kindsonthegenius.fleetappv2.fleet.repositories;

import com.kindsonthegenius.fleetappv2.fleet.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

}
