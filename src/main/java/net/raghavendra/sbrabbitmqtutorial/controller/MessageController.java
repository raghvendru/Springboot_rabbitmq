package net.raghavendra.sbrabbitmqtutorial.controller;

import net.raghavendra.sbrabbitmqtutorial.publisher.RabbitMqProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
     private RabbitMqProducer rabbitMqProducer;

    public MessageController(RabbitMqProducer rabbitMqProducer) {
        this.rabbitMqProducer = rabbitMqProducer;
    }

    //http://localhost:8080/api/v1/publish?message=hello
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        rabbitMqProducer.sendMessage(message);
        return ResponseEntity.ok("message sent to rabbitMq");

    }
}
