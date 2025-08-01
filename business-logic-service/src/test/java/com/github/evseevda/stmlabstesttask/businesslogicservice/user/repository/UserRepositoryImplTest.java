package com.github.evseevda.stmlabstesttask.businesslogicservice.user.repository;

import com.github.evseevda.stmlabstesttask.businesslogicservice.config.TestConfig;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.Role;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@DataJdbcTest
@Import(TestConfig.class)
@Sql("/sql/user/user-test.sql")
class UserRepositoryImplTest {

    private static final long EXISTENT_ID = 27L;
    private static final String EXISTENT_LOGIN = "testuser";

    @Autowired
    private UserRepository userRepository;

    @Test
    void givenUnsavedUser_whenUserSaved_thenUserWithIdReturned() {
        // arrange
        Role role = Role.builder().id(1).build();
        User user = User.builder()
                .login("newuser")
                .password("newpassword")
                .fullName("New User")
                .role(role)
                .build();

        // action
        User savedUser = userRepository.saveNew(user);

        // assertion
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    void givenNonExistentId_whenFindById_thenOptionalEmptyReturned() {
        // arrange
        Long id = -1L;

        // action
        Optional<User> user = userRepository.findById(id);

        // assertion
        assertThat(user).isEmpty();
    }

    @Test
    void givenExistentId_whenFindById_thenNotEmptyOptionalIsReturned() {
        // arrange
        Long id = EXISTENT_ID;

        // action
        Optional<User> user = userRepository.findById(id);

        // assertion
        assertThat(user).isNotEmpty();
    }

    @Test
    void givenNonExistentLogin_whenFindByLogin_thenOptionalEmptyReturned() {
        // arrange
        String login = "nonexistent";

        // action
        Optional<User> user = userRepository.findByLogin(login);

        // assertion
        assertThat(user).isEmpty();
    }

    @Test
    void givenExistentLogin_whenFindByLogin_thenNotEmptyOptionalIsReturned() {
        // arrange
        String login = EXISTENT_LOGIN;

        // action
        Optional<User> user = userRepository.findByLogin(login);

        // assertion
        assertThat(user).isNotEmpty();
    }

    @Test
    void givenExistentLogin_whenExistsByLogin_thenTrue() {
        // arrange
        String login = EXISTENT_LOGIN;

        // action
        boolean result = userRepository.existsByLogin(login);

        // assertion
        assertThat(result).isTrue();
    }

    @Test
    void givenNonExistentLogin_whenExistsByLogin_thenFalse() {
        // arrange
        String login = "nonexistent";

        // action
        boolean result = userRepository.existsByLogin(login);

        // assertion
        assertThat(result).isFalse();
    }

    @Test
    void givenExistentId_whenExistsById_thenTrue() {
        // arrange
        Long id = EXISTENT_ID;

        // action
        boolean result = userRepository.existsById(id);

        // assertion
        assertThat(result).isTrue();
    }

    @Test
    void givenNonExistentId_whenExistsById_thenFalse() {
        // arrange
        Long id = -1L;

        // action
        boolean result = userRepository.existsById(id);

        // assertion
        assertThat(result).isFalse();
    }

}