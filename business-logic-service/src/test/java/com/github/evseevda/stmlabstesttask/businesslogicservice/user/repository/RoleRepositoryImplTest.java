package com.github.evseevda.stmlabstesttask.businesslogicservice.user.repository;

import com.github.evseevda.stmlabstesttask.businesslogicservice.config.TestConfig;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.Role;
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
@Sql("/sql/role/role-test.sql")
class RoleRepositoryImplTest {

    private static final int EXISTENT_ID = 27;
    private static final String EXISTENT_NAME = "TEST_ROLE";

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void givenNonExistentId_whenFindRoleById_thenOptionalEmptyReturned() {
        // arrange
        Integer id = -1;

        // action
        Optional<Role> role = roleRepository.findRoleById(id);

        // assertion
        assertThat(role).isEmpty();
    }

    @Test
    void givenExistentId_whenFindRoleById_thenNotEmptyOptionalIsReturned() {
        // arrange
        Integer id = EXISTENT_ID;

        // action
        Optional<Role> role = roleRepository.findRoleById(id);

        // assertion
        assertThat(role).isNotEmpty();
    }

    @Test
    void givenNonExistentName_whenFindRoleByName_thenOptionalEmptyReturned() {
        // arrange
        String name = "NON_EXISTENT_ROLE";

        // action
        Optional<Role> role = roleRepository.findRoleByName(name);

        // assertion
        assertThat(role).isEmpty();
    }

    @Test
    void givenExistentName_whenFindRoleByName_thenNotEmptyOptionalIsReturned() {
        // arrange
        String name = EXISTENT_NAME;

        // action
        Optional<Role> role = roleRepository.findRoleByName(name);

        // assertion
        assertThat(role).isNotEmpty();
    }

}