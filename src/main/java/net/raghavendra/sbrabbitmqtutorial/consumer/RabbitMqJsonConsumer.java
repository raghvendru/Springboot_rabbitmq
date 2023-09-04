package net.raghavendra.sbrabbitmqtutorial.consumer;

import net.raghavendra.sbrabbitmqtutorial.dto.User;
import net.raghavendra.sbrabbitmqtutorial.publisher.RabbitMqJsonProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqJsonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqJsonConsumer.class);
    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(User user){
        LOGGER.info(String.format("recvd json message -> %s",user.toString()));


    }


}
