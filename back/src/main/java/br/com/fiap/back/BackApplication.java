package br.com.fiap.back;

import br.com.fiap.back.config.EmailSender;
import br.com.fiap.back.config.RabbitMQConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void consumer() {

		RabbitTemplate template = new RabbitTemplate(RabbitMQConfiguration.getConnection());

		while(true){
			try {
				byte[] body = template.receive("projeto.drone").getBody();
				String message = new String(body);
				String info [] = message.split(";");
				Integer temperatura = Integer.parseInt(info[3]);
				Integer umidade = Integer.parseInt(info[4]);
				if(temperatura != null && umidade != null){
					if((temperatura >= 35 || temperatura <= 0) || (umidade <= 15)){
						EmailSender.sendEmail(info);
					}
				}
			} catch (NullPointerException ex){
				System.out.println("fila vazia!");
				continue;
			}
		}
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry corsRegistry) {
				corsRegistry.addMapping("/**")
						.allowedMethods("*")
						.allowedOrigins("*");
			}
		};
	}

}
