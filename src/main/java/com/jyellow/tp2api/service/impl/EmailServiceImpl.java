package com.jyellow.tp2api.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender sender;
	
	@Override
	public boolean sendEmail(String to, String textMessage) throws javax.mail.MessagingException  {
		return sendEmailTool(textMessage, to);
	}
	
	private boolean sendEmailTool(String textMessage, String email) {
		boolean send = false;
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);		
		try {
			helper.setTo(email);
			helper.setText(textMessage, true);
			helper.setSubject("Recuperación de contraseña");
			sender.send(message);
			send = true;
		} catch (Exception e) {
			System.out.println("Error al enviar email");
		}
		return send;
	}	
}
