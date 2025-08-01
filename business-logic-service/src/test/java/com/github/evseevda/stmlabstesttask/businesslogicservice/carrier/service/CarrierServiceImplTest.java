package com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.service;

import com.github.evseevda.stmlabstesttask.businesslogicservice.carrier.entity.Carrier;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.exception.entity.EntityNotFoundException;
import com.github.evseevda.stmlabstesttask.businesslogicservice.core.repository.CrudRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarrierServiceImplTest {

    private static final Long CARRIER_ID = 1L;
    private static final Carrier MOCK_CARRIER = Carrier.builder().id(CARRIER_ID).build();

    @Mock
    private CrudRepository<Carrier, Long> crudRepository;

    @InjectMocks
    private CarrierServiceImpl carrierService;

    @Test
    void givenCarrier_whenSaveNewIsCalled_thenCrudRepositorySaveNewIsCalledAndCarrierIsReturned() {
        // arrange
        when(crudRepository.saveNew(any(Carrier.class))).thenReturn(MOCK_CARRIER);

        // action
        carrierService.saveNew(MOCK_CARRIER);

        // assertion
        verify(crudRepository).saveNew(any(Carrier.class));
    }

    @Test
    void givenCarrier_whenUpdateIsCalled_andCarrierExists_thenCrudRepositoryUpdateIsCalledAndCarrierIsReturned() {
        // arrange
        when(crudRepository.existsById(CARRIER_ID)).thenReturn(true);
        when(crudRepository.update(any(Carrier.class))).thenReturn(MOCK_CARRIER);

        // action
        carrierService.update(MOCK_CARRIER);

        // assertion
        verify(crudRepository).existsById(CARRIER_ID);
        verify(crudRepository).update(any(Carrier.class));
    }

    @Test
    void givenCarrier_whenUpdateIsCalled_andCarrierDoesNotExist_thenEntityNotFoundExceptionIsThrown() {
        // arrange
        when(crudRepository.existsById(CARRIER_ID)).thenReturn(false);

        // action & assertion
        assertThatThrownBy(() -> carrierService.update(MOCK_CARRIER))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(Carrier.class.getSimpleName())
                .hasMessageContaining("id")
                .hasMessageContaining(String.valueOf(CARRIER_ID));
        verify(crudRepository).existsById(CARRIER_ID);
        verify(crudRepository, never()).update(any(Carrier.class));
    }

    @Test
    void givenCarrierId_whenDeleteIsCalled_thenCrudRepositoryDeleteIsCalled() {
        // arrange
        doNothing().when(crudRepository).delete(any(Long.class));

        // action
        carrierService.delete(CARRIER_ID);

        // assertion
        verify(crudRepository).delete(CARRIER_ID);
    }

    @Test
    void givenCarrierId_whenFindByIdIsCalled_andCarrierExists_thenCrudRepositoryFindByIdIsCalledAndCarrierIsReturned() {
        // arrange
        when(crudRepository.findById(CARRIER_ID)).thenReturn(Optional.of(MOCK_CARRIER));

        // action
        Carrier result = carrierService.findById(CARRIER_ID);

        // assertion
        verify(crudRepository).findById(CARRIER_ID);
        assertThat(result).isEqualTo(MOCK_CARRIER);
    }

    @Test
    void givenCarrierId_whenFindByIdIsCalled_andCarrierDoesNotExist_thenEntityNotFoundExceptionIsThrown() {
        // arrange
        when(crudRepository.findById(CARRIER_ID)).thenReturn(Optional.empty());

        // action & assertion
        assertThatThrownBy(() -> carrierService.findById(CARRIER_ID))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(Carrier.class.getSimpleName())
                .hasMessageContaining("id")
                .hasMessageContaining(String.valueOf(CARRIER_ID));
        verify(crudRepository).findById(CARRIER_ID);
    }

    @Test
    void givenCarrierId_whenExistsByIdIsCalled_thenCrudRepositoryExistsByIdIsCalledAndResultIsReturned() {
        // arrange
        when(crudRepository.existsById(CARRIER_ID)).thenReturn(true);

        // action
        boolean result = carrierService.existsById(CARRIER_ID);

        // assertion
        verify(crudRepository).existsById(CARRIER_ID);
        assertThat(result).isTrue();
    }

}