package br.com.fiap.back.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class DroneDTO {


    private Long idDrone;
    private String latitude;
    private String longitude;
    private String temperaturaAr;
    private String umidadeAr;

}
