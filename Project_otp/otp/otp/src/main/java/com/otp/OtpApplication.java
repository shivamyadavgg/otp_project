package com.otp;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import com.otp.dao.EmailRepository;
import com.otp.entities.Email;
import com.otp.sender.EmailSenderService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class OtpApplication {
	


	Email email = new Email();

	public static void main(String[] args) {
		SpringApplication.run(OtpApplication.class, args);
	}
	

}
