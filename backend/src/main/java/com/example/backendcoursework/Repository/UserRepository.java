package com.example.backendcoursework.Repository;

import com.example.backendcoursework.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> { //or jpa
    Optional<User> findByEmail(String email);

}
