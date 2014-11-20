package com.myl.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

public class IssueMail {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(IssueMail.class);

	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		LOGGER.info("Initializing e-mail");
		this.mailSender = mailSender;

	}

	public void sendMail(String from, String subject, String msg) {

		LOGGER.info("Sending e-mail");
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(getEmail());
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);

	}

	public void sendMailTo(String to, String subject, String msg) {
		LOGGER.info("Sending e-mail");
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(getEmail());
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);

	}

	public void sendMimeMailTo(String to, String subject, String msg) {
		LOGGER.info("Sending Mime e-mail");

		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
					false, "utf-8");
			helper.setFrom(getEmail());
			helper.setTo(to);
			helper.setSubject(subject);
			mimeMessage.setContent(msg, "text/html");

			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			LOGGER.error("Error al intentar enviar e-mail");
		}

	}
	
	public String getEmail(){
		String email="";
		Properties prop = new Properties();
		try {
			InputStream input = new FileInputStream("./src/main/resources/mail.properties");
			prop.load(input);
			email=prop.getProperty("mail.username");
			System.out.println(email);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return email;
	}

}