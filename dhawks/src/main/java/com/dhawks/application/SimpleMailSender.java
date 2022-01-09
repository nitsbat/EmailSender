package com.dhawks.application;

import com.dhawks.application.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SimpleMailSender {
	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(SimpleMailSender.class, args);
	}


	@EventListener(ApplicationReadyEvent.class)
	public void sendEmail(){
		emailSenderService.sendEmail("3498georgian@gmail.com","Try"," Wassup nitin bisht");
	}

}
