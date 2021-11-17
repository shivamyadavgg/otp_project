package com.otp.service;

import java.time.LocalTime;
import java.util.List;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.otp.dao.EmailRepository;
import com.otp.entities.Email;
import com.otp.sender.EmailSenderService;

@Service
public class EmailServiceImpl implements EmailService {
	
	int maxAttempts = 3;

	@Autowired
	private EmailRepository repo;
	
	@Autowired
    private EmailSenderService services;
	
	@Override
	public List<Email> getUsers() {
		// TODO Auto-generated method stub
		return (List<Email>) repo.findAll();
	}

	@Override
	public void addUser(Email email) {
		// TODO Auto-generated method stub
		repo.save(email);
		email.setStartDate(LocalTime.now());
		email.setEndDate(email.getStartDate().plusMinutes(2));
		int randomPin   =(int) (Math.random()*900000)+100000;
        String otp1  = String.valueOf(randomPin);
		int otp = Integer.parseInt(otp1);
		email.setOtp(otp);
		repo.save(email);
//		sendSimpleMail(email);
	}

	@Override
	public void resendMail(Email email) {

		// TODO Auto-generated method stub
		LocalTime t1 = LocalTime.now();
		Optional<Email> mail = repo.findById(email.getEmail());
		
		Email mail1 = mail.get();

        System.out.println(mail1.getStartDate() + " " + mail1.getEndDate());
		if((t1.getMinute() - mail1.getStartDate().getMinute()) >= 1 && maxAttempts >= 1) {
			System.out.println("inside the program");
			repo.save(email);
			email.setStartDate(LocalTime.now());
			email.setEndDate(email.getStartDate().plusMinutes(5));
			int randomPin   =(int) (Math.random()*900000)+100000;
	        String otp1  = String.valueOf(randomPin);
			int otp = Integer.parseInt(otp1);
			email.setOtp(otp);
			email.setStartDate(t1);
			email.setEndDate(email.getStartDate().plusMinutes(5));
			repo.save(email);
	        System.out.println(email.getStartDate() + " " + email.getEndDate() + " " + maxAttempts);
	     	services.sendSimpleMail(email.getEmail(),"Get Your OTP :: "+ email.getOtp() + " ","This is the system Regenerated OTP :: Validation is for 2 Minutes ");
	     	maxAttempts--;
		}
		else if(maxAttempts < 1){

			System.out.println("Max Attempts");
			services.sendSimpleMail(email.getEmail(),"Oops OTP cannot be Send." + " ","Max Attempts Reached");
	     	
		}
		
	}
	

}
