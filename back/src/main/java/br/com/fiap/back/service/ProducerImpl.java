package br.com.fiap.back.service;

import br.com.fiap.back.dto.DroneDTO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import br.com.fiap.back.config.RabbitMQConfiguration;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class ProducerImpl implements Producer {

    @Override
    public void sendData(DroneDTO droneDTO) throws InterruptedException {

        RabbitAdmin admin = new RabbitAdmin(RabbitMQConfiguration.getConnection());
        Queue queueDrone = new Queue("projeto.drone");

        final String exchange = "exchange.projeto";
        admin.declareQueue(queueDrone);

        DirectExchange exchangeProjeto = new DirectExchange(exchange);
        admin.declareExchange(exchangeProjeto);

        admin.declareBinding(BindingBuilder.bind(queueDrone).to(exchangeProjeto).with("info"));

        RabbitTemplate template = new RabbitTemplate(RabbitMQConfiguration.getConnection());

        String message = droneDTO.getIdDrone()+";"+droneDTO.getLatitude()+";"+droneDTO.getLongitude()+
                ";"+droneDTO.getTemperaturaAr()+";"+droneDTO.getUmidadeAr();

        template.convertAndSend(exchange, "info", message);
        while(true){

            int temperatura = temperaturaAleatoria();
            int umidade = umidadeAleatoria();
            message = droneDTO.getIdDrone()+";"+droneDTO.getLatitude()+";"+droneDTO.getLongitude()+
                    ";"+temperatura+";"+umidade;

            template.convertAndSend(exchange, "info", message);
            if((temperatura >= 35 || temperatura <= 0) || (umidade <= 15)){
               break;
            }
            TimeUnit.SECONDS.sleep(10);
        }
    }

    public int temperaturaAleatoria(){
        return ThreadLocalRandom.current().nextInt(-25, 40 + 1);
    }

    public int umidadeAleatoria(){
        return ThreadLocalRandom.current().nextInt(0, 100 + 1);
    }
}
