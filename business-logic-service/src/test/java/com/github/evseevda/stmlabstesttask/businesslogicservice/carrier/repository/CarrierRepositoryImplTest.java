package com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.repository;

import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.entity.Carrier;
import com.github.evseevda.stmlabstesttask.businesslogicservice.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(TestConfig.class)
@Sql("/sql/carrier/carrier-test.sql")
class CarrierRepositoryImplTest {

    private static final long EXISTENT_ID = 27L;

    @Autowired
    private CarrierRepository carrierRepository;

    @Test
    void givenUnsavedCarrier_whenCarrierSaved_thenCarrierWithIdReturned() {
        // arrange
        Carrier carrier = Carrier.builder()
                .id(2L)
                .phone("test phone")
                .companyName("test company name")
                .build();

        // action
        Carrier savedCarrier = carrierRepository.saveNew(carrier);

        // assertion
        assertThat(savedCarrier.getId()).isNotNull();
    }

    @Test
    void givenNonExistentId_whenFindById_thenOptionalEmptyReturned() {
        // arrange
        Long id = -1L;

        // action
        Optional<Carrier> carrier = carrierRepository.findById(id);

        // assertion
        assertThat(carrier).isEmpty();
    }

    @Test
    void givenExistentId_whenFindById_thenNotEmptyOptionalIsReturned() {
        // arrange
        Long id = EXISTENT_ID;

        // action
        Optional<Carrier> carrier = carrierRepository.findById(id);

        // assertion
        assertThat(carrier).isNotEmpty();
    }

    @Test
    void givenUpdatingCarrier_whenUpdatedExecuted_thenCarrierUpdated() {
        // arrange
        String newPhone = "new phone";
        String newCompanyName = "new company name";
        Carrier carrier = Carrier.builder()
                .id(EXISTENT_ID)
                .companyName(newCompanyName)
                .phone(newPhone)
                .build();

        // action
        Carrier updatedCarrier = carrierRepository.update(carrier);

        // assertion
        assertThat(carrier).isEqualTo(updatedCarrier);
    }

    @Test
    void givenExistentId_whenExistsById_thenTrue() {
        // arrange
        Long id = EXISTENT_ID;

        //action
        boolean result = carrierRepository.existsById(id);

        // assertion
        assertThat(result).isTrue();
    }

    @Test
    void givenNonExistentId_whenExistsById_thenFalse() {
        // arrange
        Long id = -1L;

        // action
        boolean result = carrierRepository.existsById(id);

        // assertion
        assertThat(result).isFalse();
    }

    @Test
    void givenCarrierId_whenDeleteById_thenCarrierIsDeleted() {
        // arrange
        Long id = EXISTENT_ID;

        // action
        carrierRepository.delete(id);

        // assertion
        assertThat(carrierRepository.existsById(id)).isFalse();
    }


}