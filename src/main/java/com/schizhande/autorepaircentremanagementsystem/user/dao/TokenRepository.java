package com.schizhande.autorepaircentremanagementsystem.user.dao;

import com.schizhande.autorepaircentremanagementsystem.commons.jpa.BaseRepository;
import com.schizhande.autorepaircentremanagementsystem.user.model.Token;
import com.schizhande.autorepaircentremanagementsystem.user.model.User;

import java.util.Optional;

public interface TokenRepository extends BaseRepository<Token> {

    Optional<Token> findByToken(String token);

    Optional<Token> findByUser(User user);
}
