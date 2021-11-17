package com.otp.controller;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.otp.dao.EmailRepository;
import com.otp.entities.Email;
import com.otp.entities.Validate;
import com.otp.sender.EmailSenderService;
import com.otp.service.EmailService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OtpController {

	@Autowired
    private EmailSenderService services;
	
	@Autowired
	private EmailService service;
	
	@Autowired
	private EmailRepository repo;
	
    @GetMapping("/emails")
    public List<Email> getUsers() {
        return this.service.getUsers();
    }

    @PostMapping("/emails")
    public void addUser(@RequestBody Email email) {
        this.service.addUser(email);
        
     	services.sendSimpleMail(email.getEmail(),"Get Your OTP :: "+ email.getOtp() + " ","This is the system generated OTP");
    	   
    }
    
    @PostMapping("/validate")
    public String validateUser(@RequestBody Validate validate) {
    	
    	String mail = null;
    	int otp = 0;
    	LocalTime start = null;
    	LocalTime end = null;
    	
    	List<Email> l = repo.findAll();
    	for(int i = 0;i<l.size();i++) {
    		Email h = l.get(i);
    		if(h.getEmail().equals(validate.getEmail())) {
    			mail = h.getEmail();
    			otp = h.getOtp();
    			start = h.getStartDate();
    			end = h.getEndDate();
    			
    		}
    	}
    	
    	LocalTime now = LocalTime.now();
    	if(validate.getEmail().equals(mail) && Integer.parseInt(validate.getOtp()) == otp && now.isBefore(end) && now.isAfter(start)) {
    		System.out.println("True");
    		services.sendSuccessMail(validate.getEmail(), "OTP Is Verified", "This is a system generated success mail.");
        	
    	}
    	else {
    		System.out.println("False");
    	}
		return "Validated";
    }
    
    @PostMapping("/resend")
    public void resendMail(@RequestBody Email email) {
    	this.service.resendMail(email);
    }

}
