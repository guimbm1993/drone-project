package br.com.fiap.back.config;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailSender {

    public static void send(String [] droneInfo){

        System.out.println("Drone: " + droneInfo[0]+ "\r\n" + "Latitude: " + droneInfo[1]
        + "\r\n" + "Longitude: " + droneInfo[2] + "\r\n" + "Temperatura: " + droneInfo[3]
        + "\r\n" + "Umidade: " + droneInfo[4]);

    }

    public static void sendEmail(Session session, String toEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void send(String[] droneInfo) {
//        System.out.println("SimpleEmail Start");
//
//        String smtpHostServer = "smtp.example.com";
//        String emailID = "email_me@example.com";
//
//        Properties props = System.getProperties();
//
//        props.put("mail.smtp.host", smtpHostServer);
//
//        Session session = Session.getInstance(props, null);
//
//        String email = "Drone: " + droneInfo[0]+ "\r\n" + "Latitude: " + droneInfo[1]
//        + "\r\n" + "Longitude: " + droneInfo[2] + "\r\n" + "Temperatura: " + droneInfo[3]
//        + "\r\n" + "Umidade: " + droneInfo[4];
//
//        EmailSender.sendEmail(session, emailID, "Informações do drone", email);
//    }
}
