package com.github.evseevda.stmlabstesttask.businesslogicservice.route.service;

import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.entity.EntityNotFoundException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.repository.CrudRepository;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.entity.Route;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class RouteServiceImplTest {

    private static final Long ROUTE_ID = 1L;
    private static final Route MOCK_ROUTE = Route.builder().id(ROUTE_ID).build();

    @Mock
    private CrudRepository<Route, Long> crudRepository;

    @InjectMocks
    private RouteServiceImpl routeService;

    @Test
    void givenRoute_whenSaveNewIsCalled_thenCrudRepositorySaveNewIsCalledAndRouteIsReturned() {
        // arrange
        when(crudRepository.saveNew(any(Route.class))).thenReturn(MOCK_ROUTE);

        // action
        routeService.saveNew(MOCK_ROUTE);

        // assertion
        verify(crudRepository).saveNew(any(Route.class));
    }

    @Test
    void givenRoute_whenUpdateIsCalled_andRouteExists_thenCrudRepositoryUpdateIsCalledAndRouteIsReturned() {
        // arrange
        when(crudRepository.existsById(ROUTE_ID)).thenReturn(true);
        when(crudRepository.update(any(Route.class))).thenReturn(MOCK_ROUTE);

        // action
        routeService.update(MOCK_ROUTE);

        // assertion
        verify(crudRepository).existsById(ROUTE_ID);
        verify(crudRepository).update(any(Route.class));
    }

    @Test
    void givenRoute_whenUpdateIsCalled_andRouteDoesNotExist_thenEntityNotFoundExceptionIsThrown() {
        // arrange
        when(crudRepository.existsById(ROUTE_ID)).thenReturn(false);

        // action & assertion
        assertThatThrownBy(() -> routeService.update(MOCK_ROUTE))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(Route.class.getSimpleName())
                .hasMessageContaining("id")
                .hasMessageContaining(String.valueOf(ROUTE_ID));
        verify(crudRepository).existsById(ROUTE_ID);
        verify(crudRepository, never()).update(any(Route.class));
    }

    @Test
    void givenRouteId_whenDeleteIsCalled_thenCrudRepositoryDeleteIsCalled() {
        // arrange
        doNothing().when(crudRepository).delete(any(Long.class));

        // action
        routeService.delete(ROUTE_ID);

        // assertion
        verify(crudRepository).delete(ROUTE_ID);
    }

    @Test
    void givenRouteId_whenFindByIdIsCalled_andRouteExists_thenCrudRepositoryFindByIdIsCalledAndRouteIsReturned() {
        // arrange
        when(crudRepository.findById(ROUTE_ID)).thenReturn(Optional.of(MOCK_ROUTE));

        // action
        Route result = routeService.findById(ROUTE_ID);

        // assertion
        verify(crudRepository).findById(ROUTE_ID);
        assertThat(result).isEqualTo(MOCK_ROUTE);
    }

    @Test
    void givenRouteId_whenFindByIdIsCalled_andRouteDoesNotExist_thenEntityNotFoundExceptionIsThrown() {
        // arrange
        when(crudRepository.findById(ROUTE_ID)).thenReturn(Optional.empty());

        // action & assertion
        assertThatThrownBy(() -> routeService.findById(ROUTE_ID))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(Route.class.getSimpleName())
                .hasMessageContaining("id")
                .hasMessageContaining(String.valueOf(ROUTE_ID));
        verify(crudRepository).findById(ROUTE_ID);
    }

    @Test
    void givenRouteId_whenExistsByIdIsCalled_thenCrudRepositoryExistsByIdIsCalledAndResultIsReturned() {
        // arrange
        when(crudRepository.existsById(ROUTE_ID)).thenReturn(true);

        // action
        boolean result = routeService.existsById(ROUTE_ID);

        // assertion
        verify(crudRepository).existsById(ROUTE_ID);
        assertThat(result).isTrue();
    }

}