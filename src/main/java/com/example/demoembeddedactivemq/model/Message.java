package com.example.demoembeddedactivemq.model;

public class Message {
    String id;

    String content;

    public Message() {

    }

    public Message(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", content='" + getContent() + "'" + "}";
    }

}