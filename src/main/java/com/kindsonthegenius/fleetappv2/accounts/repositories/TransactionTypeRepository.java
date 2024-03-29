package com.kindsonthegenius.fleetappv2.accounts.repositories;

import com.kindsonthegenius.fleetappv2.accounts.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer> {
}
