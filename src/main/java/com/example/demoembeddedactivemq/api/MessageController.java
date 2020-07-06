package com.example.demoembeddedactivemq.api;

import com.example.demoembeddedactivemq.business.MessageService;
import com.example.demoembeddedactivemq.model.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {
    @Autowired
    MessageService messageService;

    @PostMapping("/")
    public ResponseEntity<String> sendMessage(@RequestBody String content) {
        Message message = messageService.send(content);
        return new ResponseEntity<>("Message Sent with Id: " + message.getId(), HttpStatus.CREATED);
    }
}