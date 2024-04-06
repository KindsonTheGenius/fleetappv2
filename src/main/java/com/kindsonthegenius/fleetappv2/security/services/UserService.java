package com.kindsonthegenius.fleetappv2.security.services;

import com.kindsonthegenius.fleetappv2.exception.InvalidTokenException;
import com.kindsonthegenius.fleetappv2.exception.UnkownIdentifierException;
import com.kindsonthegenius.fleetappv2.exception.UserAlreadyExistException;
import com.kindsonthegenius.fleetappv2.mailing.AccountVerificationEmailContext;
import com.kindsonthegenius.fleetappv2.mailing.EmailService;
import com.kindsonthegenius.fleetappv2.security.models.SecureToken;
import com.kindsonthegenius.fleetappv2.security.models.User;
import com.kindsonthegenius.fleetappv2.security.repositories.SecureTokenRepository;
import com.kindsonthegenius.fleetappv2.security.repositories.UserRepository;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

	@Autowired
	private SecureTokenService secureTokenService;

	@Autowired
	private SecureTokenRepository secureTokenRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Resource
	private EmailService emailService;

	@Autowired
	private UserRepository userRepository;
	
	//Get All Users
	public List<User> findAll(){
		return userRepository.findAll();
	}	
	
	//Get User By Id
	public User findById(int id) {
		return userRepository.findById(id).orElse(null);
	}	
	
	//Delete User
	public void delete(int id) {
		userRepository.deleteById(id);
	}


	@Value("${site.base.url.https}")
	private String baseUrl;

	public void register(User user) throws UserAlreadyExistException {
		if(checkIfUserExist(user.getEmail())){
			throw new UserAlreadyExistException("User already exists for this email");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		sendRegistrationConfirmationEmail(user);
	}

	public boolean checkIfUserExist(String email){
		return userRepository.findByEmail(email) != null? true : false;
	}

	public void sendRegistrationConfirmationEmail(User user){
		SecureToken secureToken = secureTokenService.createSecureToken();
		secureToken.setUser(user);
		secureTokenRepository.save(secureToken);
		AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
		emailContext.init(user);
		emailContext.setToken(secureToken.getToken());
		emailContext.buildVerificationUrl(baseUrl, secureToken.getToken());
		try {
			emailService.sendMail(emailContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verifyUser(String token) throws InvalidTokenException {
		SecureToken secureToken = secureTokenService.findByToken(token);
		if(Objects.isNull(secureToken) || !StringUtils.equals(token, secureToken.getToken()) || secureToken.isExpired()){
			throw new InvalidTokenException("Token is not valid");
		}
		User user = userRepository.getOne(secureToken.getUser().getId());
		if(Objects.isNull(user)){
			return false;
		}
		user.setAccountVerified(true);
		userRepository.save(user); // let's same user details

		// we don't need invalid password now
		secureTokenService.removeToken(secureToken);
		return true;
	}

	public User getUserById(String id) throws UnkownIdentifierException {
		User user= userRepository.findByEmail(id);
		if(user == null || BooleanUtils.isFalse(user.isAccountVerified())){
			// we will ignore in case account is not verified or account does not exists
			throw new UnkownIdentifierException("unable to find account or account is not active");
		}
		return user;
	}
}
