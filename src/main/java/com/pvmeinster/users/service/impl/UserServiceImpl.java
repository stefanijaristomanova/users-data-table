package com.pvmeinster.users.service.impl;

import com.pvmeinster.users.dto.User;
import com.pvmeinster.users.dto.request.AddUserRequest;
import com.pvmeinster.users.persistence.entity.UserEntity;
import com.pvmeinster.users.persistence.repository.RepositoryService;
import com.pvmeinster.users.persistence.repository.UserRepository;
import com.pvmeinster.users.service.UserService;
import com.pvmeinster.users.utils.ConvertUtils;
import com.pvmeinster.users.utils.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final RepositoryService repositoryService;
    private final SequenceGenerator sequenceGenerator;
    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(RepositoryService repositoryService, SequenceGenerator sequenceGenerator, UserRepository userRepository) {
        this.repositoryService = repositoryService;
        this.sequenceGenerator = sequenceGenerator;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(ConvertUtils::convertToUsers)
                .collect(Collectors.toList());
    }

    @Override
    public User findUserById(Long id) {
        return ConvertUtils.convertToUsers(repositoryService.findUserById(id));
    }


    @Override
    public User addUser(AddUserRequest addUserRequest) {
        UserEntity userEntity = UserEntity.builder()
                .dateCreated(LocalDateTime.now())
                .deleted(Integer.valueOf(0))
                .firstname(addUserRequest.getFirstname())
                .lastname(addUserRequest.getLastname())
                .email(addUserRequest.getEmail())
                .build();


        return ConvertUtils.convertToUsers(repositoryService.saveUser(userEntity));
    }

    @Override
    public String removeUser(Long id) {
        UserEntity userEntity = repositoryService.findUserById(id);
        repositoryService.deleteUser(userEntity);
        return "Successfully deleted user with id:" + id;
    }

    private Long generateTemplateId() {
        return sequenceGenerator.nextId();
    }
}
