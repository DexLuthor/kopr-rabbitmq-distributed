package com.github.dexluthor.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;

@Document(collection = "suspicious_payments")
@Data
@NoArgsConstructor
public class Payment implements MoneyOperation {
    @MongoId
    private String id;
    private String payer;
    private String recipient;
    private BigDecimal amount;
    private String goal;

    public Payment(final String payer, final String recipient, final BigDecimal amount, final String goal) {
        this.payer = payer;
        this.recipient = recipient;
        this.amount = amount;
        this.goal = goal;
    }

}
