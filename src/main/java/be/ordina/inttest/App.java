package be.ordina.inttest;

public class App {
	public static void main(String[] args) {
		// Recipient's email ID needs to be mentioned.
		final String to = "to@domain.tld";
		// Sender's email ID needs to be mentioned
		final String from = "from@domain.tld";

		final Mailer mailer=new Mailer("localhost", "2525");
		mailer.mail(to, from, "Hello World", "Insert some Chuck Norris facts !");
	}

}
