package com.company.repository;

import com.company.model.entity.Account;
import com.company.model.entity.Token;
import com.company.model.entity.Token.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface ITokenRepository extends JpaRepository<Token, Integer> {

    @Modifying
    void deleteByTypeAndAccount(Type type, Account account);

    Token findByKeyAndType(String key, Type type);

}
