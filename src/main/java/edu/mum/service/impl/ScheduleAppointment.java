package edu.mum.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.mum.domain.Appointment;
import edu.mum.domain.Person;
import edu.mum.domain.Session;
import edu.mum.service.AppointmentService;
import edu.mum.service.EmailService;
import edu.mum.service.PersonService;

public class ScheduleAppointment {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private PersonService personService;

	@Autowired
	private EmailService emailService;

	public void sendReminderEmail() {

		List<Appointment> appointments = appointmentService.findAll();

		for (Appointment appointment : appointments) {
			Session session = appointment.getSession();
			Date date = session.getDate();
			Date time = session.getStartTime();

			@SuppressWarnings("deprecation")
			Date myDate = new Date(date.getYear(), date.getMonth(), date.getDate(), time.getHours(), time.getMinutes());

			long millisIn36Hours = 1000 * 60 * 60 * 36;
			Date hoursago = new Date(myDate.getTime() - millisIn36Hours);

			long millisIn5Minutes = 1000 * 60 * 5;
			Date minutesago = new Date(myDate.getTime() - millisIn5Minutes);

//			System.out.println(new Date());
//			System.out.println(minutesago);
//
//			System.out.println(
//					new Date().getYear() == minutesago.getYear() && new Date().getMinutes() == minutesago.getMinutes());

			if (new Date().getYear() == minutesago.getYear() && new Date().getMinutes() == minutesago.getMinutes()) {
				Person customer = personService.findById(appointment.getCustomer().getId());
				Person counseler = personService.findById(session.getPerson().getId());
				String customer_email = customer.getEmailAddress();
				String counseler_email = counseler.getEmailAddress();

				String message = "You Appointment for Transcendental Meditation is coming. \n\n";

				System.out.println(customer_email);
				System.out.println(counseler_email);

				emailService.doSendEmail(customer_email, counseler_email, session, message);

				System.out.println("send mail");
			}

			if (new Date().getYear() == hoursago.getYear() && new Date().getMinutes() == hoursago.getMinutes()) {
				Person customer = personService.findById(appointment.getCustomer().getId());
				Person counseler = personService.findById(session.getPerson().getId());
				String customer_email = customer.getEmailAddress();
				String counseler_email = counseler.getEmailAddress();

				String message = "You Appointment for Transcendental Meditation is coming. \n\n";

				System.out.println(customer_email);
				System.out.println(counseler_email);

				emailService.doSendEmail(customer_email, counseler_email, session, message);

				System.out.println("send mail");
			}
		}

//		System.out.println(new Date() + " This runs in a cron schedule");
	}
}
