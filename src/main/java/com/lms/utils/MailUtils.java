package com.lms.utils;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.lms.services.QueueService.EmailProducer;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Component
public class MailUtils {

@Autowired
@Lazy
private EmailProducer emailProducer;


}
