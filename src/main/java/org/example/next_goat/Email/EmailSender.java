package org.example.next_goat.Email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    public static void sendVerificationCode(String recipientEmail, String verificationCode) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        final String username = "youarenextgoat@gmail.com";
        final String password = "lzkv xvnd ybaa qasp";

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("youarenextgoat@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Código de Verificación");
            message.setText("Tu código de verificación es: " + verificationCode);

            Transport.send(message);

            System.out.println("¡Correo electrónico enviado exitosamente!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
