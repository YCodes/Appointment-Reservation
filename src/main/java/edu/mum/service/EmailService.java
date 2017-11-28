package edu.mum.service;

import edu.mum.domain.Session;

public interface EmailService {
	public void doSendEmail(String customer_email, String counseler_email, Session session, String emailMessage);

}
