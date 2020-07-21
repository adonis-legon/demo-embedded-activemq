package com.example.demoembeddedactivemq.config;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties.AcknowledgeMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@EnableJms
@Configuration
public class JmsConfig {
    @Autowired
    QueueConfig queueConfig;

    @Bean
    public BrokerService broker() throws Exception {
        BrokerService broker = new BrokerService();

        // TODO: check why using a persistence adapter (kahadb), the messages are stored
        // but not sent to JSM listener even though ConcurrentStoreAndDispatchQueues is
        // set to true , and instead the msssages are processed if the app is restarted

        // PersistenceAdapter persistenceAdapter = new KahaDBPersistenceAdapter();
        // persistenceAdapter.setDirectory(new File(queueConfig.getStoragePath()));
        // ((KahaDBPersistenceAdapter)
        // persistenceAdapter).setJournalMaxFileLength(queueConfig.getStorageMaxSize());
        // ((KahaDBPersistenceAdapter)
        // persistenceAdapter).setConcurrentStoreAndDispatchQueues(true);
        // broker.setPersistenceAdapter(persistenceAdapter);
        // broker.setPersistent(true);

        broker.setPersistent(false);
        broker.setUseJmx(true);
        broker.addConnector(queueConfig.getBrokerUrl());

        return broker;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();

        template.setConnectionFactory(new PooledConnectionFactory(queueConfig.getBrokerUrl()));
        template.setMessageConverter(jacksonJmsMessageConverter());

        return template;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

        factory.setConnectionFactory(new PooledConnectionFactory(queueConfig.getBrokerUrl()));
        factory.setMessageConverter(jacksonJmsMessageConverter());
        factory.setConcurrency(String.valueOf(queueConfig.getListenerConcurrency()));
        factory.setSessionAcknowledgeMode(AcknowledgeMode.CLIENT.getMode());

        return factory;
    }

}