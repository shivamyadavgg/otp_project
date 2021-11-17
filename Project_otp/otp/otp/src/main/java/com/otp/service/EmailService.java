package com.otp.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.otp.entities.Email;

public interface EmailService {
	
	public List<Email> getUsers();
	public void addUser( Email email);
	public void resendMail(Email email);

}
