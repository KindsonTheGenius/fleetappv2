package com.kindsonthegenius.fleetappv2.security.services;

import com.kindsonthegenius.fleetappv2.exception.InvalidTokenException;
import com.kindsonthegenius.fleetappv2.exception.UnkownIdentifierException;

public interface UserAccountService {

    void forgottenPassword(final String userName) throws UnkownIdentifierException;
    void updatePassword(final String password, final String token) throws InvalidTokenException, UnkownIdentifierException;
}
