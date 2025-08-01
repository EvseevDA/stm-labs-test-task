package com.github.evseevda.stmlabstesttask.businesslogicservice.route.repository;

import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.entity.Carrier;
import com.github.evseevda.stmlabstesttask.businesslogicservice.config.TestConfig;
import com.github.evseevda.stmlabstesttask.businesslogicservice.route.entity.Route;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJdbcTest
@Import(TestConfig.class)
@Sql("/sql/route/route-test.sql")
class RouteRepositoryImplTest {

    private static final long EXISTENT_ROUTE_ID = 27L;
    private static final long EXISTENT_CARRIER_ID = 27L;
    private static final long EXISTENT_CARRIER_ID_1 = 28L;

    @Autowired
    private RouteRepository routeRepository;

    @Test
    void givenUnsavedRoute_whenRouteSaved_thenRouteWithIdReturned() {
        // arrange
        Carrier carrier = Carrier.builder().id(EXISTENT_CARRIER_ID).build();
        Route route = Route.builder()
                .startPoint("Start City")
                .destinationPoint("End City")
                .carrier(carrier)
                .durationInMinutes(120L)
                .build();

        // action
        Route savedRoute = routeRepository.saveNew(route);

        // assertion
        assertThat(savedRoute.getId()).isNotNull();
    }

    @Test
    void givenNonExistentId_whenFindById_thenOptionalEmptyReturned() {
        // arrange
        Long id = -1L;

        // action
        Optional<Route> route = routeRepository.findById(id);

        // assertion
        assertThat(route).isEmpty();
    }

    @Test
    void givenExistentId_whenFindById_thenNotEmptyOptionalIsReturned() {
        // arrange
        Long id = EXISTENT_ROUTE_ID;

        // action
        Optional<Route> route = routeRepository.findById(id);

        // assertion
        assertThat(route).isNotEmpty();
    }

    @Test
    void givenUpdatingRoute_whenUpdateExecuted_thenRouteUpdated() {
        // arrange
        String newStartPoint = "New Start City";
        String newDestinationPoint = "New End City";
        Carrier newCarrier = Carrier.builder().id(EXISTENT_CARRIER_ID_1).build();
        long newDurationInMinutes = 180L;
        Route route = Route.builder()
                .id(EXISTENT_ROUTE_ID)
                .startPoint(newStartPoint)
                .destinationPoint(newDestinationPoint)
                .carrier(newCarrier)
                .durationInMinutes(newDurationInMinutes)
                .build();

        // action
        Route updatedRoute = routeRepository.update(route);

        // assertion
        assertThat(updatedRoute.getStartPoint()).isEqualTo(newStartPoint);
        assertThat(updatedRoute.getDestinationPoint()).isEqualTo(newDestinationPoint);
        assertThat(updatedRoute.getCarrier().getId()).isEqualTo(newCarrier.getId());
        assertThat(updatedRoute.getDurationInMinutes()).isEqualTo(newDurationInMinutes);
    }

    @Test
    void givenExistentId_whenExistsById_thenTrue() {
        // arrange
        Long id = EXISTENT_ROUTE_ID;

        // action
        boolean result = routeRepository.existsById(id);

        // assertion
        assertThat(result).isTrue();
    }

    @Test
    void givenNonExistentId_whenExistsById_thenFalse() {
        // arrange
        Long id = -1L;

        // action
        boolean result = routeRepository.existsById(id);

        // assertion
        assertThat(result).isFalse();
    }

    @Test
    void givenRouteId_whenDeleteById_thenRouteIsDeleted() {
        // arrange
        Long id = EXISTENT_ROUTE_ID;

        // action
        routeRepository.delete(id);

        // assertion
        assertThat(routeRepository.existsById(id)).isFalse();
    }

}