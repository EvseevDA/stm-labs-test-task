package com.github.evseevda.stmlabstesttask.businesslogicservice.user.service;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.entity.EntityNotFoundException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.exception.UserAlreadyExistsException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveNew(User user) throws UserAlreadyExistsException {
        return userRepository.saveNew(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", String.valueOf(userId)));
    }

    @Override
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository
                .findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "login", login));
    }

    @Override
    public User currentUser() {
        String currentUserLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        return loadUserByUsername(currentUserLogin);
    }
}
