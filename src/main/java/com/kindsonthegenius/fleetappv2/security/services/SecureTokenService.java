package com.kindsonthegenius.fleetappv2.security.services;

import com.kindsonthegenius.fleetappv2.security.models.SecureToken;

public interface SecureTokenService {

    public SecureToken createSecureToken();


    public void saveSecureToken(SecureToken secureToken);

    public SecureToken findByToken(String token);

    public void removeToken(SecureToken token);
    public void removeTokenByToken(String token);
}
