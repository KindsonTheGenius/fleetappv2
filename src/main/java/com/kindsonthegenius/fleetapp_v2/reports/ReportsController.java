package com.kindsonthegenius.fleetapp_v2.reports;

import com.kindsonthegenius.fleetapp_v2.accounts.repositories.TransactionRepository;
import com.kindsonthegenius.fleetapp_v2.hr.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportsController {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/reports/vehicles")
    public String vehicles(){
        return "/reports/vehicles";
    }

    @GetMapping("/reports/accounts")
    public String accounts(Model model){
        model.addAttribute("transactions", repository.findAll());
        model.addAttribute("employeeCount", employeeRepository.getCountByCountry());
        return "/reports/accounts";
    }

    @GetMapping("/reports/hr")
    public String hr(){
        return "/reports/hr";
    }

    @GetMapping("/reports/hires")
    public String hires(){
        return "/reports/hires";
    }

    @GetMapping("/reports/helpdesk")
    public String helpdesk(){
        return "/reports/helpdesk";
    }

    @GetMapping("/reports/maintenance")
    public String maintenance(){
        return "/reports/maintenance";
    }
}
