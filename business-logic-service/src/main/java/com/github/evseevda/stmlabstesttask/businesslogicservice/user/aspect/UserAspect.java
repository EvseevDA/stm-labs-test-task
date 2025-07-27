package com.github.evseevda.stmlabstesttask.businesslogicservice.user.aspect;


import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.exception.LoginAlreadyExistsException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class UserAspect {

    private final UserRepository userRepository;

    @Before(""" 
        execution(* com.github.evseevda.stmlabstesttask.businesslogicservice.user.service.UserServiceImpl.saveNew(user))
        && args(user)
    """)
    public void requireLoginNotExists(User user) {
        String login = user.getLogin();
        if (userRepository.existsByLogin(login)) {
            throw new LoginAlreadyExistsException(login);
        }
    }

}
