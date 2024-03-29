package com.kindsonthegenius.fleetappv2.accounts.repositories;

import com.kindsonthegenius.fleetappv2.accounts.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

}
