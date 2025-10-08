package com.lms.services.QueueService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.lms.config.RabbitMQConfig;
import com.lms.dtos.EmailDto;

import jakarta.mail.MessagingException;

@Service
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;
    private final TemplateEngine templateEngine;

    public EmailProducer(RabbitTemplate rabbitTemplate, TemplateEngine templateEngine) {
        this.rabbitTemplate = rabbitTemplate;
        this.templateEngine = templateEngine;
    }

    public void sendEmailMessage(String message, String to, String subject) {
        EmailDto email = new EmailDto(to, subject, message);
        System.out.println("Sending email to queue: " + email);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                email
        );
    }

    public void sendWelcomeEmail(String name, String password, String email) throws MessagingException {
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("email", email);
        context.setVariable("password", password);

        String body = templateEngine.process("registration", context);
        sendEmailMessage(body, email, "Your Registration Credentials");
    }
}
