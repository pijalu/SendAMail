package be.ordina.inttest;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendAMail {
	
	public static void main(String[] args) {		
	  // Recipient's email ID needs to be mentioned.
      String to = "to@domain.tld";

      // Sender's email ID needs to be mentioned
      String from = "from@domain.tld";

      // Assuming you are sending email from localhost
      String host = "localhost";
      String port = "2525";
      String localhost = "localhost";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);
      properties.setProperty("mail.smtp.port", port);
      properties.put("mail.smtp.localhost", localhost);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("Hello World!");

         // Now set the actual message
         message.setText("Insert some Chuck Norris facts !");

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
	}
}
