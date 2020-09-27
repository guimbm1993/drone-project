package br.com.fiap.back.config;

public class EmailSender {

    public static void send(String [] droneInfo){

        System.out.println("Drone: " + droneInfo[0]+ "\r\n" + "Latitude: " + droneInfo[1]
        + "\r\n" + "Longitude: " + droneInfo[2] + "\r\n" + "Temperatura: " + droneInfo[3]
        + "\r\n" + "Umidade: " + droneInfo[4]);

    }
}
