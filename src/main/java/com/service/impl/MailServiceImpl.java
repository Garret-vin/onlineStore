package com.service.impl;

import com.model.Order;
import com.service.MailService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    private static final Logger logger = Logger.getLogger(MailServiceImpl.class);

    @Override
    public void sendConfirmCode(Order order) {
        final String username = "batononlineshop@gmail.com";
        final String password = "123qwe-=";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(order.getEmail())
            );
            message.setSubject("Confirm password \"Online shop\"");
            message.setText(order.getCode().getValue());

            Transport.send(message);
            logger.info("Message send succesfull to " + order.getUser());
        } catch (MessagingException e) {
            logger.error("An error occurred while sending message to " + order.getUser(), e);
        }
    }
}
