package com.lms.services.QueueService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.lms.dtos.EmailDto;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailConsumer {
@Autowired
private JavaMailSender mailSender;
    @RabbitListener(queues = "Email_Queue")
    public void receiveMessage(
            EmailDto email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setTo(email.getTo());
        helper.setFrom("nathlaxman242@gmail.com");
        helper.setSubject(email.getSubject());
        helper.setText(email.getBody(), true);
        mailSender.send(message);
    }
}
