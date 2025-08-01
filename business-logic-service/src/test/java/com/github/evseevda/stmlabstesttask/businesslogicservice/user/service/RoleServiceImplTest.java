package com.github.evseevda.stmlabstesttask.businesslogicservice.user.service;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.entity.EntityNotFoundException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.entity.Role;
import com.github.evseevda.stmlabstesttask.businesslogicservice.user.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    private static final Integer ROLE_ID = 1;
    private static final String ROLE_NAME = "ROLE_USER";
    private static final Role MOCK_ROLE = Role.builder().id(ROLE_ID).name(ROLE_NAME).build();

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    void givenRoleId_whenFindRoleByIdIsCalled_andRoleExists_thenRoleRepositoryFindByIdIsCalledAndRoleIsReturned() {
        // arrange
        when(roleRepository.findRoleById(ROLE_ID)).thenReturn(Optional.of(MOCK_ROLE));

        // action
        Role result = roleService.findRoleById(ROLE_ID);

        // assertion
        verify(roleRepository).findRoleById(ROLE_ID);
        assertThat(result).isEqualTo(MOCK_ROLE);
    }

    @Test
    void givenRoleId_whenFindRoleByIdIsCalled_andRoleDoesNotExist_thenEntityNotFoundExceptionIsThrown() {
        // arrange
        when(roleRepository.findRoleById(ROLE_ID)).thenReturn(Optional.empty());

        // action & assertion
        assertThatThrownBy(() -> roleService.findRoleById(ROLE_ID))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(Role.class.getSimpleName())
                .hasMessageContaining("id")
                .hasMessageContaining(String.valueOf(ROLE_ID));
        verify(roleRepository).findRoleById(ROLE_ID);
    }

    @Test
    void givenRoleName_whenFindRoleByNameIsCalled_andRoleExists_thenRoleRepositoryFindByNameIsCalledAndRoleIsReturned() {
        // arrange
        when(roleRepository.findRoleByName(ROLE_NAME)).thenReturn(Optional.of(MOCK_ROLE));

        // action
        Role result = roleService.findRoleByName(ROLE_NAME);

        // assertion
        verify(roleRepository).findRoleByName(ROLE_NAME);
        assertThat(result).isEqualTo(MOCK_ROLE);
    }

    @Test
    void givenRoleName_whenFindRoleByNameIsCalled_andRoleDoesNotExist_thenEntityNotFoundExceptionIsThrown() {
        // arrange
        when(roleRepository.findRoleByName(ROLE_NAME)).thenReturn(Optional.empty());

        // action & assertion
        assertThatThrownBy(() -> roleService.findRoleByName(ROLE_NAME))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(Role.class.getSimpleName())
                .hasMessageContaining("name")
                .hasMessageContaining(ROLE_NAME);
        verify(roleRepository).findRoleByName(ROLE_NAME);
    }

}