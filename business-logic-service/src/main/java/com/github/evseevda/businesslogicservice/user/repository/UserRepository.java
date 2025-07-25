package com.github.evseevda.businesslogicservice.user.repository;


import com.github.evseevda.businesslogicservice.user.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    UserEntity saveNew(UserEntity user);
    Optional<UserEntity> findById(Long userId);

}
