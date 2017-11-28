package edu.mum.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import edu.mum.domain.Session;
import edu.mum.service.EmailService;

@Service
@EnableAsync
public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender mailSender;

	final String[] sci_principle = { "The nature of life is to grow.", "Enjoy greater efficiency and accomplish more.",
			"The field of all possibilities is the source of all solutions.",
			"Thought leads to action, action leads to achievement, and achievement leads to fulfillment.",
			"Harmony exists in diversity. ", "The Whole is greater than the sum of the parts." };

	@Async
	public void doSendEmail(String customer_email, String counseler_email, Session session, String emailMessage) {

		Random random = new Random();
		int index = random.nextInt(sci_principle.length);

		// takes input from e-mail form
		String recipientAddress = customer_email;
		String subject = "TM Appointment";

		String message = emailMessage;
		message = message + "Appointment Details: \n";
		message = message + "Time: " + session.getStartTime() + " \n";
		message = message + "Date: " + session.getDate() + " \n";
		message = message + "Location: " + session.getLocation();
		message = message + "\n\n" + sci_principle[index];

		// prints debug info
		System.out.println("To: " + recipientAddress + " & " + counseler_email);
		System.out.println("Subject: " + subject);
		System.out.println("Message: " + message);

		// creates a simple e-mail object
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setCc(counseler_email);
		email.setSubject(subject);
		email.setText(message);

		// sends the e-mail
		mailSender.send(email);
	}

}
