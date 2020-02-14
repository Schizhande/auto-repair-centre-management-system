package com.schizhande.usermanagementsystem.dao;

import com.schizhande.usermanagementsystem.dao.jpa.BaseRepository;
import com.schizhande.usermanagementsystem.model.Role;
import com.schizhande.usermanagementsystem.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUsername(String username);

    Page<User> findByRole(Role role, Pageable pageable);

    Optional<User> findByEmail(String email);
}
