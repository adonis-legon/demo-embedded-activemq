package com.example.demoembeddedactivemq.messaging;

import com.example.demoembeddedactivemq.business.MessageConsumer;
import com.example.demoembeddedactivemq.config.QueueConfig;
import com.example.demoembeddedactivemq.model.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    QueueConfig queueConfig;

    @Autowired
    MessageConsumer messageConsumer;

    public void produce(Message message) {
        jmsTemplate.convertAndSend(queueConfig.getName(), message);
        System.out.println(String.format("Message %s sent to queue %s", message.getId(), queueConfig.getName()));
    }

    @JmsListener(destination = "${app.queue.name}")
    public void consume(Message message) {
        System.out.println(String.format("Message %s received from queue %s, with content %s", message.getId(),
                queueConfig.getName(), message.getContent()));

        // process message
        messageConsumer.process(message);
    }
}