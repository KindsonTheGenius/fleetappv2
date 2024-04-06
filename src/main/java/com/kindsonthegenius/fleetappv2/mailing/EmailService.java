package com.kindsonthegenius.fleetappv2.mailing;

public interface EmailService {
    void sendMail(final AbstractEmailContext email) throws javax.mail.MessagingException;
}
