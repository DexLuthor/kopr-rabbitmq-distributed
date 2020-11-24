package com.github.dexluthor.listeners.handlers;

import com.github.dexluthor.entity.Payment;
import com.github.dexluthor.repositories.PaymentRepository;

public class MongoHandler extends AbstractHandler {
    private final PaymentRepository paymentRepository;

    public MongoHandler(final PaymentRepository paymentRepository) { this.paymentRepository = paymentRepository;}

    public MongoHandler(final PaymentRepository paymentRepository, final AbstractHandler abstractHandler) {
        super(abstractHandler);
        this.paymentRepository = paymentRepository;
    }

    public void handle(Payment payment) {
        paymentRepository.save(payment);
        if (abstractHandler != null) {
            abstractHandler.handle(payment);
        }
    }
}