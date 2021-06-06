package com.jyellow.tp2api.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.service.EmailService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender sender;
	
	@Override
	public boolean sendEmail(String to, String textMessage) throws javax.mail.MessagingException  {
		log.info("EmailServiceImpl: method sendEmail");
		return sendEmailTool(textMessage, to);
	}
	
	@Override
	public boolean sendEmailSession(String to, String textMessage, String subject) throws javax.mail.MessagingException  {
		log.info("EmailServiceImpl: method sendEmailSession");
		return sendEmailToolSession(textMessage, to, subject);
	}
	
	private boolean sendEmailTool(String textMessage, String email) {
		log.info("EmailServiceImpl: method sendEmailTool");
		boolean send = false;
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);		
		try {
			helper.setTo(email);
			helper.setText(textMessage, false);
			helper.setSubject("Recuperación de contraseña");
			sender.send(message);
			send = true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return send;
	}
	
	private boolean sendEmailToolSession(String textMessage, String email, String subject) {
		log.info("EmailServiceImpl: method sendEmailToolSession");
		boolean send = false;
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);		
		try {
			helper.setTo(email);
			helper.setText(textMessage, false);
			helper.setSubject(subject);
			sender.send(message);
			send = true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return send;
	}	
}
