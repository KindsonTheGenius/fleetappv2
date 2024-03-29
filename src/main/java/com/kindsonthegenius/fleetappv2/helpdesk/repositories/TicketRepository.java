package com.kindsonthegenius.fleetappv2.helpdesk.repositories;

import com.kindsonthegenius.fleetappv2.helpdesk.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
