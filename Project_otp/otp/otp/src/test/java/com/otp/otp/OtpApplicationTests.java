package com.otp.otp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.otp.dao.EmailRepository;
import com.otp.entities.Email;
import com.otp.sender.EmailSenderService;
import com.otp.service.EmailService;

@SpringBootTest
class OtpApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private EmailRepository repo;
	
	@Autowired
	private EmailService mailService;
	
	@Autowired
    private EmailSenderService services;
	
	@Test
	void getValidUser() {
		Email email = new Email("shivamyadav28081999@gmail.com");
		repo.save(email);
		List<Email> actualResult = mailService.getUsers();
		actualResult.contains(email);
		Boolean result = false;
		if(actualResult != null) {
			result = true;
		}
		assertThat(result).isTrue();
	}
	
	@Test
	void getInvalidUser() {
		Email email = null;
		List<Email> actualResult = mailService.getUsers();
		actualResult.contains(email);
		Boolean result = false;
		if(actualResult != null) {
			result = true;
		}
		assertThat(result).isFalse();
	}

}
