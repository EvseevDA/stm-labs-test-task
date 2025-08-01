package com.github.evseevda.stmlabstesttask.businesslogicservice.user.service;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.entity.EntityNotFoundException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.exception.UserAlreadyExistsException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final Long USER_ID = 1L;
    private static final String USER_LOGIN = "testuser";
    private static final User MOCK_USER = User.builder().id(USER_ID).login(USER_LOGIN).build();

    @Mock
    private UserRepository userRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void givenUser_whenSaveNewIsCalled_thenUserRepositorySaveNewIsCalled() {
        // arrange
        when(userRepository.saveNew(any(User.class))).thenReturn(MOCK_USER);

        // action
        userService.saveNew(MOCK_USER);

        // assertion
        verify(userRepository).saveNew(any(User.class));
    }

    @Test
    void givenUser_whenSaveNewIsCalled_andUserAlreadyExists_thenUserAlreadyExistsExceptionIsThrown() {
        // arrange
        when(userRepository.saveNew(any(User.class))).thenThrow(new UserAlreadyExistsException("User already exists"));

        // action & assertion
        assertThatThrownBy(() -> userService.saveNew(MOCK_USER))
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessageContaining("User already exists");
        verify(userRepository).saveNew(any(User.class));
    }

    @Test
    void givenUserId_whenFindByIdIsCalled_andUserExists_thenUserRepositoryFindByIdIsCalledAndUserIsReturned() {
        // arrange
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(MOCK_USER));

        // action
        User result = userService.findById(USER_ID);

        // assertion
        verify(userRepository).findById(USER_ID);
        assertThat(result).isEqualTo(MOCK_USER);
    }

    @Test
    void givenUserId_whenFindByIdIsCalled_andUserDoesNotExist_thenEntityNotFoundExceptionIsThrown() {
        // arrange
        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());

        // action & assertion
        assertThatThrownBy(() -> userService.findById(USER_ID))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(User.class.getSimpleName())
                .hasMessageContaining("id")
                .hasMessageContaining(String.valueOf(USER_ID));
        verify(userRepository).findById(USER_ID);
    }

    @Test
    void givenLogin_whenLoadUserByUsernameIsCalled_andUserExists_thenUserRepositoryFindByLoginIsCalledAndUserIsReturned() {
        // arrange
        when(userRepository.findByLogin(USER_LOGIN)).thenReturn(Optional.of(MOCK_USER));

        // action
        User result = userService.loadUserByUsername(USER_LOGIN);

        // assertion
        verify(userRepository).findByLogin(USER_LOGIN);
        assertThat(result).isEqualTo(MOCK_USER);
    }

    @Test
    void givenLogin_whenLoadUserByUsernameIsCalled_andUserDoesNotExist_thenEntityNotFoundExceptionIsThrown() {
        // arrange
        when(userRepository.findByLogin(USER_LOGIN)).thenReturn(Optional.empty());

        // action & assertion
        assertThatThrownBy(() -> userService.loadUserByUsername(USER_LOGIN))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(User.class.getSimpleName())
                .hasMessageContaining("login")
                .hasMessageContaining(USER_LOGIN);
        verify(userRepository).findByLogin(USER_LOGIN);
    }

    @Test
    void givenAuthenticatedUser_whenCurrentUserIsCalled_thenLoadUserByUsernameIsCalledAndUserIsReturned() {
        // arrange
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(USER_LOGIN);
        when(userRepository.findByLogin(USER_LOGIN)).thenReturn(Optional.of(MOCK_USER));
        SecurityContextHolder.setContext(securityContext);

        // action
        User result = userService.currentUser();

        // assertion
        verify(userRepository).findByLogin(USER_LOGIN);
        assertThat(result).isEqualTo(MOCK_USER);
    }

    @Test
    void givenAuthenticatedUser_whenCurrentUserIsCalled_andUserDoesNotExist_thenEntityNotFoundExceptionIsThrown() {
        // arrange
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(USER_LOGIN);
        when(userRepository.findByLogin(USER_LOGIN)).thenReturn(Optional.empty());
        SecurityContextHolder.setContext(securityContext);

        // action & assertion
        assertThatThrownBy(() -> userService.currentUser())
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(User.class.getSimpleName())
                .hasMessageContaining("login")
                .hasMessageContaining(USER_LOGIN);
        verify(userRepository).findByLogin(USER_LOGIN);
    }

    @Test
    void givenUserId_whenExistsByIdIsCalled_thenUserRepositoryExistsByIdIsCalledAndResultIsReturned() {
        // arrange
        when(userRepository.existsById(USER_ID)).thenReturn(true);

        // action
        boolean result = userService.existsById(USER_ID);

        // assertion
        verify(userRepository).existsById(USER_ID);
        assertThat(result).isTrue();
    }

}