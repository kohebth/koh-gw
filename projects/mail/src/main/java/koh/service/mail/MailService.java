package koh.service.mail;

import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class MailService {
    private static final Properties MAIL_PROPERTIES;

    static {
        MAIL_PROPERTIES = new Properties();
        try (InputStream input = ClassLoader.getSystemClassLoader().getResourceAsStream("mail.properties")) {
            MAIL_PROPERTIES.load(input);
        } catch (IOException e) {
            log.error("", e);
            System.exit(-1);
        }
    }

    public MailService() {

    }

    public void sendEmail(String toAddress, String subject, String messageText)
            throws Exception {
        Session session = Session.getInstance(MAIL_PROPERTIES, new MailAuthenticator(MAIL_PROPERTIES));

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(MAIL_PROPERTIES.getProperty("mail.smtp.user")));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
        message.setSubject(subject);
        message.setText(messageText);

        Transport.send(message);
        log.info("Email has been sent: {}", message);
    }

    public static void main(String[] args) {
        try {
            MailService mailService = new MailService();
            mailService.sendEmail("duy.nc164814@sis.hust.edu.vn", "Test Subject", "Test Message");
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
