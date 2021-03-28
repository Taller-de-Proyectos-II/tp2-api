package com.jyellow.tp2api.service;

import javax.mail.MessagingException;

public interface EmailService {
	boolean sendEmail(String to, String textMessage) throws MessagingException;
}
