package com.github.dexluthor.producer;

import com.github.dexluthor.common.Utils;
import com.github.dexluthor.entity.Charging;
import com.github.dexluthor.entity.Payment;
import com.github.dexluthor.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@EnableScheduling
@Slf4j
public class Producer {
    private final RabbitTemplate rabbitTemplate;
    private final UserRepository userRepository;

    public Producer(final RabbitTemplate rabbitTemplate, final UserRepository userRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.userRepository = userRepository;
    }

    @Scheduled(fixedDelay = 3000)
    public void produce() {
        if (Math.random() >= 0.5) {
            charge();
        } else {
            pay();
        }
    }

    private void pay() {
        val userList = userRepository.findAll();

        if (userList.size() >= 2) {
            val randomBill = Utils.randomBill();
            val payer = userList.get((int) (Math.random() * userList.size()));
            payer.withdraw(randomBill);
            userRepository.save(payer);
            val recipient = userList.get((int) (Math.random() * userList.size())).getPhoneNumber();
            val payment = new Payment(payer.getPhoneNumber(), recipient, randomBill, Utils.randomGoal());
            val routingKey = "payment." + recipient;

            log.info(String.valueOf(payment));

            rabbitTemplate.convertAndSend("payment", routingKey, payment);
        }
    }

    private void charge() {
        val userList = userRepository.findAll();
        if (!userList.isEmpty()) {
            val recipient = userList.get((int) (Math.random() * userList.size())).getPhoneNumber();
            val routingKey = "charging." + recipient;
            val charging = new Charging(recipient, Utils.randomBill(), LocalDateTime.now(), Utils.randomLocation());

            log.info(String.valueOf(charging));

            rabbitTemplate.convertAndSend("charging", routingKey, charging);
        }
    }
}
