package com.kindsonthegenius.fleetappv2.security.services;

import com.kindsonthegenius.fleetappv2.security.models.SecureToken;

public interface SecureTokenService {

    SecureToken createSecureToken();

    void saveSecureToken(SecureToken secureToken);

    SecureToken findByToken(String token);

    void removeToken(SecureToken token);
}
