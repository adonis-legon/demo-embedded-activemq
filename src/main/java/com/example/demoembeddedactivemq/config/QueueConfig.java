package com.example.demoembeddedactivemq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.queue")
public class QueueConfig {
    String brokerUrl;

    String name;

    int listenerConcurrency;

    String storagePath;

    int storageMaxSize;

    public String getBrokerUrl() {
        return this.brokerUrl;
    }

    public void setBrokerUrl(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getListenerConcurrency() {
        return this.listenerConcurrency;
    }

    public void setListenerConcurrency(int listenerConcurrency) {
        this.listenerConcurrency = listenerConcurrency;
    }

    public String getStoragePath() {
        return this.storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public int getStorageMaxSize() {
        return this.storageMaxSize;
    }

    public void setStorageMaxSize(int storageMaxSize) {
        this.storageMaxSize = storageMaxSize;
    }

}