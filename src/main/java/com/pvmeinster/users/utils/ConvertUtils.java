package com.pvmeinster.users.utils;

import com.pvmeinster.users.dto.User;
import com.pvmeinster.users.persistence.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;


public class ConvertUtils {

    public static User convertToUsers(UserEntity userEntity) {
        ModelMapperUtils modelMapper = new ModelMapperUtils();

        return modelMapper.map(userEntity, User.class);
    }
}
