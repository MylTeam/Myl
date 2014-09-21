package com.myl.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class IssueMail {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IssueMail.class);
	
	
	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		LOGGER.info("Initializing e-mail");
		this.mailSender = mailSender;
	}

	public void sendMail(String from, String subject, String msg) {
		LOGGER.info("Sending e-mail");
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo("mylzupport@gmail.com");
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
		
	}
}