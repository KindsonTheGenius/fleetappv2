package com.kindsonthegenius.fleetappv2.security.services;

import com.kindsonthegenius.fleetappv2.exception.InvalidTokenException;
import com.kindsonthegenius.fleetappv2.exception.UnkownIdentifierException;
import com.kindsonthegenius.fleetappv2.mailing.EmailService;
import com.kindsonthegenius.fleetappv2.mailing.ForgotPasswordEmailContext;
import com.kindsonthegenius.fleetappv2.security.models.SecureToken;
import com.kindsonthegenius.fleetappv2.security.models.User;
import com.kindsonthegenius.fleetappv2.security.repositories.SecureTokenRepository;
import com.kindsonthegenius.fleetappv2.security.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Objects;


@Service("customerAccountService")
public class DefaultCustomerAccountService implements CustomerAccountService {

    @Resource
    UserService userService;

    @Resource
    private SecureTokenService secureTokenService;

    @Resource
    SecureTokenRepository secureTokenRepository;

    @Value("${site.base.url.https}")
    private String baseURL;

    @Resource
    private EmailService emailService;

    @Resource
    private UserRepository userRepository;

    @Resource
    private PasswordEncoder passwordEncoder;


    @Override
    public void forgottenPassword(String userName) throws UnkownIdentifierException {
        User user= userService.getUserById(userName);
        sendResetPasswordEmail(user);
    }

    @Override
    public void updatePassword(String password, String token) throws InvalidTokenException, UnkownIdentifierException {
        SecureToken secureToken = secureTokenService.findByToken(token);
        if(Objects.isNull(secureToken) || !StringUtils.equals(token, secureToken.getToken()) || secureToken.isExpired()){
            throw new InvalidTokenException("Token is not valid");
        }
        User user = userRepository.getOne(secureToken.getUser().getId());
        if(Objects.isNull(user)){
            throw new UnkownIdentifierException("unable to find user for the token");
        }
        secureTokenService.removeToken(secureToken);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }


    protected void sendResetPasswordEmail(User user) {
        SecureToken secureToken= secureTokenService.createSecureToken();
        secureToken.setUser(user);
        secureTokenRepository.save(secureToken);
        ForgotPasswordEmailContext emailContext = new ForgotPasswordEmailContext();
        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseURL, secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean loginDisabled(String username) {
        User user = userRepository.findByEmail(username);
        return user!=null ? user.isLoginDisabled() : false;
    }
}
