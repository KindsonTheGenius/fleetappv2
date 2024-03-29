package com.kindsonthegenius.fleetappv2.accounts.repositories;

import com.kindsonthegenius.fleetappv2.accounts.models.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceStatusRepository extends JpaRepository<InvoiceStatus, Integer> {

}
