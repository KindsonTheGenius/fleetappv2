package com.kindsonthegenius.fleetapp_v2.accounts.controllers;

import com.kindsonthegenius.fleetapp_v2.accounts.models.Transaction;
import com.kindsonthegenius.fleetapp_v2.accounts.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/accounts/transactions")
    public String parameters(Model model){
        List<Transaction> transactions = transactionService.findAll();
        model.addAttribute("transactions", transactions);
        return "/accounts/transactions";
    }

    @GetMapping("/accounts/transactionAdd")
    public String addTransaction(){
        return "accounts/transactionAdd";
    }

    //Get Job Title by id
    @GetMapping("/accounts/transaction/{id}")
    @ResponseBody
    public Transaction getById(@PathVariable Integer id){
        return transactionService.findById(id).orElse(null);
    }

    @PostMapping("/accounts/transactions")
    public String save(Transaction transaction){
        transactionService.save(transaction);
        return "redirect:/accounts/transactions";
    }

    @RequestMapping(value="/accounts/transaction/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        transactionService.delete(id);
        return "redirect:/accounts/transactions";
    }
}
