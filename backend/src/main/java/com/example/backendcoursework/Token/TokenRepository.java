package com.example.backendcoursework.Token;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, Integer> {

    @Query(value = """
      select t from Token t inner join User u\s
      on t.user.idUser = u.idUser\s
      where u.idUser = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(Integer id);



//    List<Token> findAllByUser_IdUser(int idUser);
    Optional<Token> findByToken(String token);
}
