package com.lms.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Component
public class MailUtils {
@Autowired
private JavaMailSender mailSender;
@Autowired
private TemplateEngine templateEngine;
public void sendMail(String to,String from,String body,String subject) throws MessagingException {
	MimeMessage message=mailSender.createMimeMessage();
	MimeMessageHelper helper=new MimeMessageHelper(message,"UTF-8");
	helper.setTo(to);
	helper.setFrom(from);
	helper.setSubject(subject);
	helper.setText(body,true);
	mailSender.send(message);
}

public void sendWelcomeEmail(String name,String password,String email) throws MessagingException{
	Context context=new Context();
    context.setVariable("name", name);
    context.setVariable("email", email);
    context.setVariable("password", password);
    String body=templateEngine.process("registration", context);
    sendMail(email, "nathlaxman242@gmail.com", body, "Your Registration Credentials");
	
}
}
