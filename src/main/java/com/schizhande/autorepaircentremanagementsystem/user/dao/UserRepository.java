package com.schizhande.autorepaircentremanagementsystem.user.dao;

import com.schizhande.autorepaircentremanagementsystem.commons.jpa.BaseRepository;
import com.schizhande.autorepaircentremanagementsystem.user.model.Role;
import com.schizhande.autorepaircentremanagementsystem.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUsername(String username);

    Page<User> findByRole(Role role, Pageable pageable);

    Optional<User> findByEmail(String email);
}
