import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Sender {

    private final String username;
    private final String password;
    private final Properties properties;

    public MailService() {
        // Load properties from file
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("mail.properties")) {
            if (input == null) {
                throw new FileNotFoundException("mail.properties file not found");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Extract username and password
        this.username = properties.getProperty("mail.smtp.user");
        this.password = properties.getProperty("mail.smtp.password");
    }

    public void sendEmail(String toAddress, String subject, String messageText) {
        // Create a session with the properties and authenticator
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(username));

            // Set To: header field
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(messageText);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        MailService mailService = new MailService();
        mailService.sendEmail("recipient@example.com", "Test Subject", "Test Message");
    }
}
