package com.kindsonthegenius.fleetapp_v2.parameters.repositories;

import com.kindsonthegenius.fleetapp_v2.parameters.models.Country;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query(value = "SELECT c FROM Country c" +
            " where concat(c.capital, c.code, c.continent, c.nationality, c.description)" +
            " LIKE %?1%")
    List<Country> findByKeyword(String keyword);

}
