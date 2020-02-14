package com.schizhande.usermanagementsystem.service;

import com.schizhande.usermanagementsystem.model.User;
import com.schizhande.usermanagementsystem.service.request.NewUserRequest;
import com.schizhande.usermanagementsystem.service.request.VerifyUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.context.request.WebRequest;

import java.util.Collection;

public interface UserService {
    User saveUser(NewUserRequest request,  WebRequest webRequest);

    User findById(Long userId);

    Page<User> findAll(Integer page, Integer size);

    User update(Long userId, NewUserRequest request);

    User deleteUser(Long userId);

    Page<User> findByRole(Long roleId, Integer page, Integer size);

    User enableUser(Long userId, boolean enable);

    User verifyUser(VerifyUserRequest request);

    Collection<User> findAll();
}
