package xyz.hnnknk.deneb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    JavaMailSender javaMailSender;


    public void send(String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("ur_shak@mail.ru");
        message.setSubject("Successful authorization");
        message.setText("User " + name + " successful created" );
        javaMailSender.send(message);
    }
}
