package br.com.fiap.back.config;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailSender {

    public static void send(String[] droneInfo) {

        System.out.println("Drone: " + droneInfo[0] + "\r\n" + "Latitude: " + droneInfo[1]
                + "\r\n" + "Longitude: " + droneInfo[2] + "\r\n" + "Temperatura: " + droneInfo[3]
                + "\r\n" + "Umidade: " + droneInfo[4]);

    }

    public static void sendEmail(String[] droneInfo) {
        String to = "email do destinatário";
        String from = "integrations37scj2@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.fallback", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("integrations37scj2@gmail.com", "");
            }
        });

        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Informações sobre o Drone" + droneInfo[0]);
            message.setText("Informações sobre o drone que quebrou condições " +
                    "de temperatura ou umidade especificadas.\r\n"+ "Latitude: "+droneInfo[1]+"\r\n Longitude: "
            + droneInfo[2]+ "\r\n" + "Temperatura: " + droneInfo[3] +"\r\n" + "Umidade: "+ droneInfo[4]);
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }


}
