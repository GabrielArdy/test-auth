package com.ecommerce.desktop.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.desktop.Model.User;

public interface UserRepository extends MongoRepository<User, String> {

  public User findByEmail(String email);

}
