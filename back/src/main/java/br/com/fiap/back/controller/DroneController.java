package br.com.fiap.back.controller;

import br.com.fiap.back.dto.DroneDTO;
import br.com.fiap.back.service.Producer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drones")
public class DroneController {

    private Producer producer;

    public DroneController(Producer producer){
        this.producer = producer;
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<?> addDrone(@RequestBody DroneDTO droneDTO) throws InterruptedException {
        producer.sendData(droneDTO);
        return ResponseEntity.ok().body("chamada feita");
    }


}
