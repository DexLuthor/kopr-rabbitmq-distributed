package com.github.dexluthor.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Charging implements MoneyOperation {
    private String recipient;
    private BigDecimal amount;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateTime;
    private String location;

    public Charging(final String recipient, final BigDecimal amount, final LocalDateTime dateTime, final String location) {
        this.recipient = recipient;
        this.amount = amount;
        this.dateTime = dateTime;
        this.location = location;
    }

}
