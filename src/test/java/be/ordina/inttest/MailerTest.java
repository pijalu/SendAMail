package be.ordina.inttest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;


public class MailerTest {
	private static final String HOST = "127.0.0.1";
	private static final String MESSAGE = "MESSAGE";
	private static final String SUBJECT = "SUBJECT";
	private static final String FROM = "FROM";
	private static final String TO = "TO";
	private GreenMail mailServer;

	@Before
	public void setUp() {
		mailServer = new GreenMail(ServerSetupTest.SMTP);
		mailServer.start();
	}

	@After
	public void tearDown() {
		mailServer.stop();
	}

	@Test
	public void testMailer() throws Exception {
		final Mailer mailer=new Mailer(HOST,
				Integer.toString(mailServer.getSmtp().getPort()));

		// Send mail
		mailer.mail(TO, FROM, SUBJECT, MESSAGE);

		// Validate mail
		final MimeMessage[] receivedMessages = mailServer.getReceivedMessages();
		assertEquals(1, receivedMessages.length);
		final MimeMessage message = receivedMessages[0];
		assertNotNull(message);
		assertEquals(TO, message.getRecipients(Message.RecipientType.TO)[0].toString());
		assertEquals(FROM, message.getFrom()[0].toString());
		assertEquals(SUBJECT, message.getSubject());
		assertTrue(String.valueOf(message.getContent()).contains(MESSAGE));
	}
}
