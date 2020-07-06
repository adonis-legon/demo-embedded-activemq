package com.example.demoembeddedactivemq.business;

import com.example.demoembeddedactivemq.model.Message;

public interface MessageConsumer {
    void process(Message message);
}