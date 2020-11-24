package com.github.dexluthor.repositories;

import com.github.dexluthor.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}

