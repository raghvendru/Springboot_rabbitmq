package net.raghavendra.sbrabbitmqtutorial.controller;

import net.raghavendra.sbrabbitmqtutorial.dto.User;
import net.raghavendra.sbrabbitmqtutorial.publisher.RabbitMqJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {

    private RabbitMqJsonProducer rabbitMqJsonProducer;

    public MessageJsonController(RabbitMqJsonProducer rabbitMqJsonProducer) {
        this.rabbitMqJsonProducer = rabbitMqJsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
        rabbitMqJsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("json msg sent to RAbbitmq");
    }
}
