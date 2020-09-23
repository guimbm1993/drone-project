package br.com.fiap.back.service;

import br.com.fiap.back.dto.DroneDTO;

public interface Producer {

    void sendData(DroneDTO droneDTO);

}
