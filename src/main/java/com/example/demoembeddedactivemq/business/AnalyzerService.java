package com.example.demoembeddedactivemq.business;

import java.util.Random;

import com.example.demoembeddedactivemq.model.Message;

import org.springframework.stereotype.Service;

@Service
public class AnalyzerService implements MessageConsumer {
    @Override
    public void process(Message message) {
        int count = message.getContent().split(" ").length;

        // simulate resouce intensive operation
        try {
            Thread.sleep((1 + new Random().nextInt(5)) * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("Content for message %s has %s word(s)", message.getId(), count));
    }
}