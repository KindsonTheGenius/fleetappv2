package com.kindsonthegenius.fleetappv2.security.controllers;

import com.kindsonthegenius.fleetappv2.exception.InvalidTokenException;
import com.kindsonthegenius.fleetappv2.exception.UnkownIdentifierException;
import com.kindsonthegenius.fleetappv2.security.models.ResetPasswordData;
import com.kindsonthegenius.fleetappv2.security.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PasswordResetController {

    private static final String REDIRECT_LOGIN = "redirect:/login";
    private static final String MSG = "resetPasswordMsg";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserAccountService customerAccountService;

    @PostMapping("/passwordRequest") // Display form with only email field
    public String resetPassword(final ResetPasswordData forgotPasswordForm, RedirectAttributes redirAttr) {
        try {
            customerAccountService.forgottenPassword(forgotPasswordForm.getEmail()); // Send email
        } catch (UnkownIdentifierException e) {
           // log the error
            redirAttr.addFlashAttribute("error",
                    messageSource.getMessage("error", null, LocaleContextHolder.getLocale())
            );
        }
        redirAttr.addFlashAttribute("message",
                messageSource.getMessage("user.forgotpwd.msg", null, LocaleContextHolder.getLocale())
        );
        return REDIRECT_LOGIN;
    }

    @GetMapping("/passwordChange") // Display form after user clicks on link in email
    public String changePassword(@RequestParam(required = false) String token, final RedirectAttributes redirAttr, final Model model) {
        if (StringUtils.isEmpty(token)) {
            redirAttr.addFlashAttribute("tokenError",
                    messageSource.getMessage("user.registration.verification.missing.token", null, LocaleContextHolder.getLocale())
            );
            return REDIRECT_LOGIN;
        }
        ResetPasswordData data = new ResetPasswordData();
        data.setToken(token);
        setResetPasswordForm(model, data); // add the resetPassword form to the model to send to the template
        return "security/changePassword";  // post to the same url
    }

    @PostMapping("/passwordChange") // Perform reset after user fills and submits form
    public String changePassword(final ResetPasswordData data, final Model model) {
        try {
            customerAccountService.updatePassword(data.getPassword(), data.getToken()); // perform the update
        } catch (InvalidTokenException | UnkownIdentifierException e) {
            // log error statement
            model.addAttribute("tokenError",
                    messageSource.getMessage("user.registration.verification.invalid.token", null, LocaleContextHolder.getLocale())
            );
            return "security/changePassword";
        }
        model.addAttribute("passwordUpdateMsg",
                messageSource.getMessage("user.password.updated.msg", null, LocaleContextHolder.getLocale())
        );
        setResetPasswordForm(model, new ResetPasswordData());
        return "security/passwordChangeSuccessful";
    }

    private void setResetPasswordForm(final Model model, ResetPasswordData data){
        model.addAttribute("forgotPassword",data);
    }

}
