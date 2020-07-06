package com.example.demoembeddedactivemq.business;

import java.util.UUID;

import com.example.demoembeddedactivemq.messaging.MessageHandler;
import com.example.demoembeddedactivemq.model.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    MessageHandler messageHandler;

    public Message send(String content) {
        Message message = new Message(UUID.randomUUID().toString(), content);

        // send message to queue
        messageHandler.produce(message);

        return message;
    }
}