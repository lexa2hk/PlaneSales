package com.example.backendcoursework.Repository;

import com.example.backendcoursework.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {

}
