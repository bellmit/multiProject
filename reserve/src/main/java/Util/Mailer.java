package Util;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mailer {

    @Resource(name = "java:jboss/mail/gmail")
    private Session session;

    public String send(String addresses, String topic, String textMessage) {
        try {

            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
            message.setSubject(topic);
            message.setText(textMessage);

            Transport.send(message);

        } catch (MessagingException e) {
            Logger.getLogger(Mailer.class.getName()).log(Level.WARNING, "Cannot send mail", e);
        }
        return "succes";
    }


}
