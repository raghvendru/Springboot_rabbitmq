package net.raghavendra.sbrabbitmqtutorial.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.queue.name}")
   private String queue;

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;

    @Value("${rabbitmq.queue.exchange}")
    private String exchange;

    @Value("${rabbitmq.queue.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.queue.routing.json.key}")
    private String routingJsonKey;

    @Bean
    //spring bean for rabbitmq config
    public Queue queue(){
        return new Queue(queue);
    }

    //spring bean fos quwue to store json msgs
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }

    @Bean
    //spring bean rmq exchange
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    //binding betw queue and exchange using routing_key
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    //binding betw json  queue and exchange using routing_key
    @Bean
    public Binding jsonBinding(){
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(routingJsonKey);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
         RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
         rabbitTemplate.setMessageConverter(messageConverter());
         return rabbitTemplate;
    }

    //connectionFactory
    //RabbitTemplate
    //RabbitAdmin
}
