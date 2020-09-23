package br.com.fiap.back.service;

import br.com.fiap.back.dto.DroneDTO;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import br.com.fiap.back.config.RabbitMQConfiguration;

@Service
public class ProducerImpl implements  Producer{

    @Override
    public void sendData(DroneDTO droneDTO) {

        RabbitAdmin admin = new RabbitAdmin(RabbitMQConfiguration.getConnection());
        Queue queueSPFC = new Queue("torcedor.spfc");

        final String exchange = "exchange.torcedor";
        admin.declareQueue(queueSPFC);

        DirectExchange exchangeTorcedor = new DirectExchange(exchange);
        admin.declareExchange(exchangeTorcedor);

        admin.declareBinding(BindingBuilder.bind(queueSPFC).to(exchangeTorcedor).with("spfc"));

        RabbitTemplate template = new RabbitTemplate(RabbitMQConfiguration.getConnection());

        template.convertAndSend(exchange, "spfc", "Julio Cesar");
    }
}
