package com.github.dexluthor.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;

@Document
@NoArgsConstructor
@Data
@Slf4j
public class User {
    private BigDecimal credit = BigDecimal.valueOf(10_000L);
    private String phoneNumber;
    @MongoId
    private String id;

    public User(BigDecimal credit, String phoneNumber) {
        log.info("Credit for {} is {}", phoneNumber, credit);
        this.credit = credit;
        this.phoneNumber = phoneNumber;
    }

    public void charge(BigDecimal amount) {
        credit = credit.add(amount);
    }

    private void withdraw(BigDecimal amount) {
        if (credit.compareTo(amount) <= -1) {
            log.error("Not enough money. Actual credit: {}, amount to withdraw: {}", credit, amount);
            return;
        }
        credit = credit.subtract(amount);
    }
}
