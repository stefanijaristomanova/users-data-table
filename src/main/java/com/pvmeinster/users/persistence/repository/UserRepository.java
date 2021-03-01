package com.pvmeinster.users.persistence.repository;

import com.pvmeinster.users.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

}