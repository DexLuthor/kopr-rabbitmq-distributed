package com.github.dexluthor.listeners.handlers;

import com.github.dexluthor.entity.Payment;

public abstract class AbstractHandler {
    protected AbstractHandler abstractHandler;

    public AbstractHandler() { }

    public AbstractHandler(final AbstractHandler abstractHandler) {
        this.abstractHandler = abstractHandler;
    }

    public abstract void handle(Payment payment);
}
