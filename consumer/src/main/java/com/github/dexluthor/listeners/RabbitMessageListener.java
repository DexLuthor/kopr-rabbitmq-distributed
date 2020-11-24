package com.github.dexluthor.listeners;

import com.github.dexluthor.entity.Charging;
import com.github.dexluthor.entity.MoneyOperation;
import com.github.dexluthor.entity.Payment;
import com.github.dexluthor.entity.User;
import com.github.dexluthor.listeners.handlers.AbstractHandler;
import com.github.dexluthor.listeners.handlers.MongoHandler;
import com.github.dexluthor.repositories.PaymentRepository;
import com.github.dexluthor.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class RabbitMessageListener {
    private final AbstractHandler handler;
    private final UserRepository userRepository;

    public RabbitMessageListener(final PaymentRepository paymentRepository, final UserRepository userRepository) {
        this.handler = new MongoHandler(paymentRepository);
        this.userRepository = userRepository;
    }

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            exchange = @Exchange("payment"),
                            value = @Queue("${phoneNumber}"),
                            key = "payment." + "${phoneNumber}"
                    ),
                    @QueueBinding(
                            exchange = @Exchange("charging"),
                            value = @Queue("${phoneNumber}"),
                            key = "charging." + "${phoneNumber}"
                    )
            }
    )
    public void listenForMessages(MoneyOperation moneyOperation) {
        val user = userRepository.findByPhoneNumber(moneyOperation.getRecipient())
                .orElseGet(() -> new User(new BigDecimal(0), moneyOperation.getRecipient()));
        user.charge(moneyOperation.getAmount());
        userRepository.save(user);
        if (moneyOperation instanceof Payment) {
            log.info("Received {} Actual credit: {}", moneyOperation.getAmount(), user.getCredit());
        } else if (moneyOperation instanceof Charging) {
            log.info("Charged {} Actual credit: {}", moneyOperation.getAmount(), user.getCredit());
        }
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange("payment"),
                    value = @Queue("all_payments"),
                    key = "payment.*"
            )
    )
    public void checkForSuspiciousPayment(Payment payment) {
        if (payment.getAmount().compareTo(new BigDecimal(5000)) >= 0) {
            handler.handle(payment);
            log.info("Suspicious {} payment", payment.getAmount());
        }
    }

}
