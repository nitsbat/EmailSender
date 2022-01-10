package com.dhawks.application;

import com.dhawks.application.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@SpringBootApplication
public class SimpleMailSender {

    @Value("${mail.location.subject.text}")
    private String subjectFileLocation;

    @Value("${mail.location.body.text}")
    private String bodyFileLocation;

    @Autowired
    private EmailSenderService emailSenderService;

    public static void main(String[] args) {
        SpringApplication.run(SimpleMailSender.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void sendEmail() {
        emailSenderService.sendEmail("3498georgian@gmail.com",
                initMessageString(subjectFileLocation), initMessageString(bodyFileLocation));
    }

    private String initMessageString(String fileLocation) {
        Path path = Paths.get(fileLocation);
        StringBuilder builder = new StringBuilder();
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> builder.append(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

}
