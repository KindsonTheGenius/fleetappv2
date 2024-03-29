package com.kindsonthegenius.fleetappv2.parameters.repositories;

import com.kindsonthegenius.fleetappv2.parameters.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

}
