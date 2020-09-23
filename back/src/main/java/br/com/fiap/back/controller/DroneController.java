package br.com.fiap.back.controller;

import br.com.fiap.back.dto.DroneDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drones")
public class DroneController {


    @PostMapping
    public @ResponseBody
    ResponseEntity<?> addDrone(@RequestBody DroneDTO droneDTO){
        System.out.println(droneDTO.toString());
        return ResponseEntity.ok().body("chamada feita");
    }


}
