package net.raghavendra.sbrabbitmqtutorial.publisher;

import net.raghavendra.sbrabbitmqtutorial.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqJsonProducer {
    @Value("${rabbitmq.queue.exchange}")
    private String exchange;
    @Value("${rabbitmq.queue.routing.json.key}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqJsonProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public RabbitMqJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){
      LOGGER.info(String.format("json msg sent -> %s",user
              .toString()));
      rabbitTemplate.convertAndSend(exchange,routingJsonKey,user);
    }
}
