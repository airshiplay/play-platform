package com.airlenet.play.mail;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSenderImpl javaMailSender;

	@Value("${mail.smtpFromMail?:mail@whenling.com}")
	private String smtpFromMail;

	@Value("${mail.smtpHost?:smtp.exmail.qq.com}")
	private String smtpHost;

	@Value("${mail.smtpPort?:25}")
	private Integer smtpPort;

	@Value("${mail.smtpUsername?:mail@whenling.com}")
	private String smtpUsername;

	@Value("${mail.smtpPassword?:Mail1234}")
	private String smtpPassword;

	private ThreadPoolExecutor pool = new ThreadPoolExecutor(2, Integer.MAX_VALUE, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>());

	private void addSendTask(final MimeMessage mimeMessage) {
		try {
			pool.execute(new Runnable() {
				public void run() {
					javaMailSender.send(mimeMessage);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void send(String smtpFromMail, String smtpHost, Integer smtpPort, String smtpUsername, String smtpPassword, String toMail, String subject, String content,
			Map<String, Object> model, boolean async) {
		javaMailSender.setHost(smtpHost);
		javaMailSender.setPort(smtpPort);
		javaMailSender.setUsername(smtpUsername);
		javaMailSender.setPassword(smtpPassword);
		javaMailSender.createMimeMessage();
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			mimeMessageHelper.setFrom(" <" + smtpFromMail + ">");
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setTo(toMail);
			mimeMessageHelper.setText(content, true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		if (async) {
			addSendTask(mimeMessage);
		} else {
			javaMailSender.send(mimeMessage);
		}
	}

	@Override
	public void send(String toMail, String subject, String content, Map<String, Object> model, boolean async) {
		send(smtpFromMail, smtpHost, smtpPort, smtpUsername, smtpPassword, toMail, subject, content, model, async);
	}

	@Override
	public void send(String toMail, String subject, String content, Map<String, Object> model) {
		send(smtpFromMail, smtpHost, smtpPort, smtpUsername, smtpPassword, toMail, subject, content, model, true);
	}

	@Override
	public void send(String toMail, String subject, String content) {
		send(smtpFromMail, smtpHost, smtpPort, smtpUsername, smtpPassword, toMail, subject, content, null, true);
	}

}
