package com.pvmeinster.users.persistence.repository;

import com.pvmeinster.users.persistence.entity.UserEntity;

import net.logstash.logback.encoder.org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RepositoryService {

    private UserRepository userRepository;

    @Autowired
    public RepositoryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveAllUsers(List<UserEntity> userEntities) {
        try {
            userRepository.saveAll(userEntities);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ExceptionUtils.getRootCauseMessage(exception.getCause()));
        }
    }

    public UserEntity findUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with id:" + id + " not found"));
    }

    public UserEntity saveUser(UserEntity userEntity) {
        try {
            return userRepository.save(userEntity);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ExceptionUtils.getRootCauseMessage(exception.getCause()));
        }
    }

    public List<UserEntity> findAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ExceptionUtils.getRootCauseMessage(exception.getCause()));
        }
    }


    public void deleteUser(UserEntity userEntity) {
        try {
            userRepository.delete(userEntity);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error while deleting data from database on record with ID: " + userEntity.getId());
        }
    }
}
