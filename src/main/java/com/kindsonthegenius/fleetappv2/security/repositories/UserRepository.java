package com.kindsonthegenius.fleetappv2.security.repositories;

import com.kindsonthegenius.fleetappv2.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	User findByFirstnameAndLastname(String firstname, String lastname);
	User findByEmail(String email);

}
