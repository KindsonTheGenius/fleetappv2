package com.kindsonthegenius.fleetappv2.accounts.repositories;

import com.kindsonthegenius.fleetappv2.accounts.models.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionStatusRepository extends JpaRepository<TransactionStatus, Integer> {
}
