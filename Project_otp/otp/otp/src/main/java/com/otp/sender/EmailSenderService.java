package com.otp.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendSimpleMail(String toEmail,String body,String subject) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shivamyadav28081999@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		
		mailSender.send(message);
	}
	
	public void sendSuccessMail(String toEmail,String body,String subject) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shivamyadav28081999@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		
		mailSender.send(message);
	}



}
