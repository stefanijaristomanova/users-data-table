package com.pvmeinster.users.service;

import com.pvmeinster.users.dto.User;
import com.pvmeinster.users.dto.request.AddUserRequest;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findUserById(Long id);

    User addUser(AddUserRequest addUserRequest);

    String removeUser(Long id);
}
