package com.utils;

import com.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class MailHelper {
    private static final String PORT_ADDRESS = "465";
    private static final String HOST_ADDRESS = "smtp.gmail.com";
    private static final String SUBJECT_TEXT = "Nightly Test Report";
    private static final String GREETING_TEXT = "Dear All,\nThe system is not working properly. To view errors in details please check the attachment.\n";
    private static final String ATTACHMENT_FILE_NAME = "Api_Test_Log_" + User.CURRENT_DATE + ".log";
    private static final String CONTACTS_INFO = "\n\nThank You,\nQA Automation\n24/7 services";
    private static Logger logger = LogManager.getLogger(MailHelper.class);

    public static void sendEmail() {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST_ADDRESS);
        props.put("mail.smtp.socketFactory.port", PORT_ADDRESS);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", PORT_ADDRESS);
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(User.FROM_EMAIL, User.EMAIL_PASSWORD);
                    }
                });
        try {
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setHeader("Content-Type", "text/html");
            messageBodyPart1.setText(GREETING_TEXT);

            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            messageBodyPart2.setHeader("Content-Type", "text/html");
            messageBodyPart2.setDataHandler(new DataHandler(new FileDataSource(User.LOG_FILE)));
            messageBodyPart2.setFileName(ATTACHMENT_FILE_NAME);

            MimeBodyPart messageBodyPart3 = new MimeBodyPart();
            messageBodyPart3.setHeader("Content-Type", "text/html");
            messageBodyPart3.setText(CONTACTS_INFO);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart2);
            multipart.addBodyPart(messageBodyPart3);

            Message message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/html");
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(User.TO_EMAIL));
            message.setSubject(SUBJECT_TEXT);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
            logger.info("Failed To Sent Email: " + User.TO_EMAIL);
            logger.error(e.getLocalizedMessage() + "\n");
            e.printStackTrace();
        }
    }
}
