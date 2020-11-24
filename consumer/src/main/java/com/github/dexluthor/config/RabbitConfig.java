package com.github.dexluthor.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitConfig {
    @Value("${phoneNumber}")
    private String queueName;

    @Bean
    @Autowired
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue personalUserQueue() {
        log.info("Defined queue {}", queueName);
        return new Queue(queueName);
    }

    @Bean
    public Queue queueForAllPayments() {
        log.info("Defined queue {}", queueName);
        return new Queue("all_payments");
    }

    @Bean
    public TopicExchange paymentExchange() {
        log.info("Defined topic exchange payment");
        return new TopicExchange("payment");
    }

    @Bean
    public Binding personalQueueToPaymentExchangeBinding() {
        return BindingBuilder.bind(personalUserQueue()).to(paymentExchange()).with("payment." + queueName);
    }

    @Bean
    public Binding allPaymentsQueueToPaymentExchangeBinding() {
        return BindingBuilder.bind(queueForAllPayments()).to(paymentExchange()).with("payment.*");
    }

    @Bean
    public DirectExchange chargingExchange() {
        log.info("Defined direct exchange charging");
        return new DirectExchange("charging");
    }

    @Bean
    public Binding personalQueueToChargingExchangeBinding() {
        return BindingBuilder.bind(personalUserQueue()).to(chargingExchange()).with("charging." + queueName);
    }
}