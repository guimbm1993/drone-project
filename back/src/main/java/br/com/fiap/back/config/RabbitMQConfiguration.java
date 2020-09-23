package br.com.fiap.back.config;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfiguration {

    private static CachingConnectionFactory connectionFactory;

    public static CachingConnectionFactory getConnection(){

        if(connectionFactory == null){
            connectionFactory = new CachingConnectionFactory("grouse-01.rmq.cloudamqp.com");//TODO add hostname
            connectionFactory.setUsername("crivosoe");//TODO add username
            connectionFactory.setPassword("GZ63c4B0rhMWmv0t_AuOGjJnZhZ4KVVb");//TODO add password
            connectionFactory.setVirtualHost("crivosoe");//TODO add virtualhost

            //Recommended settings
            connectionFactory.setRequestedHeartBeat(30);
            connectionFactory.setConnectionTimeout(30000);
        }

        return connectionFactory;
    }

}
