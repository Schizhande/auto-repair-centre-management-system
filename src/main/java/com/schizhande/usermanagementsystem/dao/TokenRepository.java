package com.schizhande.usermanagementsystem.dao;

import com.schizhande.usermanagementsystem.dao.jpa.BaseRepository;
import com.schizhande.usermanagementsystem.model.Token;
import com.schizhande.usermanagementsystem.model.User;

import java.util.Optional;

public interface TokenRepository extends BaseRepository<Token> {

    Optional<Token> findByToken(String token);

    Optional<Token> findByUser(User user);
}
