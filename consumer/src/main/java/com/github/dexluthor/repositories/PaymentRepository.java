package com.github.dexluthor.repositories;

import com.github.dexluthor.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
}