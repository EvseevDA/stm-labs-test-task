package com.github.evseevda.stmlabstesttask.businesslogicservice.user.repository;


import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;

import java.util.Optional;

public interface UserRepository {

    User saveNew(User user);
    Optional<User> findById(Long userId);
    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsById(Long id);

}
