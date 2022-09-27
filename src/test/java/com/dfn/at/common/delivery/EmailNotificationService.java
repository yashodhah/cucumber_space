package com.dfn.at.common.delivery;

import com.dfn.at.core.constants.EnvironmentConstants;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;

public class EmailNotificationService {
    public void sendEmail(String msg, List<String> attachmentFiles) {
        try {
            // TODO: [YD] Remove the credentials from code
            Session session = getNewSession("AKIA3IDEMAPL2VUS2YNG", "BE3sUN8amBQtL5/UfdTBTRhrK5WKuPHTomOxWdA1j1qU");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EnvironmentConstants.FROM_MAIL));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(EnvironmentConstants.TO_MAIL_LIST));
            message.setSubject(EnvironmentConstants.MAIL_SUBJECT);

            Multipart multipart = new MimeMultipart();

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart);

            if (attachmentFiles != null) {
                for (String fileName : attachmentFiles) {
                    addAttachment(multipart, fileName);
                }
            }

            message.setContent(multipart);

            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addAttachment(Multipart multipart, String filename) {
        try {
            DataSource source = new FileDataSource(filename);
            BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
        } catch (Exception e) {

        }
    }

    private Properties getMailConfig() {
        Properties prop = new Properties();

        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.host", EnvironmentConstants.MAIL_HOST);
        prop.put("mail.smtp.port", EnvironmentConstants.MAIL_HOST_PORT);

        return prop;
    }


    private Session getNewSession(String username, String password) {
        Properties prop = getMailConfig();

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        return session;
    }
}
