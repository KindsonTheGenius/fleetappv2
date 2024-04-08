package com.kindsonthegenius.fleetappv2.security.controllers;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

@Controller
public class SecurityController {

    @Resource
    private MessageSource messageSource;

    @RequestMapping("/login")
    public String loginPage() {
        return "security/login";
    }

    @GetMapping("/failurePage")
    public String failurePage(){
        return "security/failurePage";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess( Model model){
        model.addAttribute("loginSuccess", messageSource.getMessage("login.success", null, LocaleContextHolder.getLocale()));
        return "security/loginSuccessful";
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword(){
        return "security/forgotPassword";
    }

    @GetMapping("/register")
    public String register() {
        return "security/register";
    }

    @RequestMapping("/index")
    public String homePage() {
        return "index";
    }

    @RequestMapping("/blank")
    public String blank() {
        return "blank";
    }

}
