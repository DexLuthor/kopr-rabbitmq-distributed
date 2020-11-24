package com.github.dexluthor.listeners.handlers;

import com.github.dexluthor.entity.Payment;

public class SmsHandler extends AbstractHandler {
    public SmsHandler() { }

    public SmsHandler(final AbstractHandler abstractHandler) {
        super(abstractHandler);
    }

    public void handle(Payment payment) {
        // logic
        if (abstractHandler != null) {
            abstractHandler.handle(payment);
        }
    }
}
