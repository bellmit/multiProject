package util;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class Mailer {

    private static final String USERNAME = "nextleveldining@gmail.com";
    private static final String PASSWORD = "Nextlevelpassword!";

    public void send(String receiver) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(receiver)
            );
            message.setSubject("Reservation NLD");
            message.setText("Dear Sir or Madam,"
                    + "\n\n We'd like to remember you that your reservation will start in three hours!\n\n" +msg);

            Transport.send(message);
        } catch (MessagingException e) {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }


}
