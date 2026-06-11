package com.smalaca.trainingcenter.inventory.application.trainingavailability;

import com.smalaca.trainingcenter.inventory.domain.reservation.ReservationRepository;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.TrainingAvailability;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.TrainingAvailabilityId;
import com.smalaca.trainingcenter.inventory.domain.traininginventory.TrainingAvailabilityRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class TrainingAvailabilityApplicationServiceTest {
    private final TrainingAvailabilityRepository trainingAvailabilityRepository = mock(TrainingAvailabilityRepository.class);
    private final ReservationRepository reservationRepository = mock(ReservationRepository.class);
    private final TrainingAvailabilityApplicationService service = TrainingAvailabilityApplicationService.create(trainingAvailabilityRepository, reservationRepository);

    @Test
    void shouldReturnIdOfCreatedTrainingAvailability() {
        UUID trainingSessionId = UUID.randomUUID();
        CreateTrainingAvailabilityCommand command = new CreateTrainingAvailabilityCommand(trainingSessionId, 20, 5);

        TrainingAvailabilityId actual = service.createTrainingAvailability(command);

        assertThat(actual).isNotNull();
        assertThat(actual.id()).isNotNull();
    }

    @Test
    void shouldCreateTrainingAvailability() {
        UUID trainingSessionId = UUID.randomUUID();
        CreateTrainingAvailabilityCommand command = new CreateTrainingAvailabilityCommand(trainingSessionId, 20, 5);

        TrainingAvailabilityId id = service.createTrainingAvailability(command);

        TrainingAvailability actual = thenSavedTrainingAvailability();
        assertThat(actual).extracting("trainingAvailabilityId").isEqualTo(id);
        assertThat(actual).extracting("trainingSessionId").extracting("id").isEqualTo(trainingSessionId);
        assertThat(actual).extracting("capacity").extracting("value").isEqualTo(20);
        assertThat(actual).extracting("reservedSeats").extracting("value").isEqualTo(0);
    }

    private TrainingAvailability thenSavedTrainingAvailability() {
        ArgumentCaptor<TrainingAvailability> captor = ArgumentCaptor.forClass(TrainingAvailability.class);
        then(trainingAvailabilityRepository).should().save(captor.capture());
        return captor.getValue();
    }

    @Test
    void shouldRemoveTrainingAvailability() {
        UUID trainingAvailabilityId = UUID.randomUUID();
        RemoveTrainingAvailabilityCommand command = new RemoveTrainingAvailabilityCommand(trainingAvailabilityId);

        service.removeTrainingAvailability(command);

        then(trainingAvailabilityRepository).should().delete(new TrainingAvailabilityId(trainingAvailabilityId));
    }
}
