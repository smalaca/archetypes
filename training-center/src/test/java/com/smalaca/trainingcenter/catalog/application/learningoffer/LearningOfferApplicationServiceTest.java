package com.smalaca.trainingcenter.catalog.application.learningoffer;

import com.smalaca.trainingcenter.catalog.domain.learningoffer.LearningOffer;
import com.smalaca.trainingcenter.catalog.domain.learningoffer.LearningOfferId;
import com.smalaca.trainingcenter.catalog.domain.learningoffer.LearningOfferRepository;
import com.smalaca.trainingcenter.catalog.domain.learningoffer.TrainingOfferId;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class LearningOfferApplicationServiceTest {
    private final LearningOfferRepository repository = mock(LearningOfferRepository.class);
    private final LearningOfferApplicationService service = new LearningOfferApplicationService(repository);

    @Test
    void shouldReturnIdOfCreatedLearningOffer() {
        CreateLearningOfferCommand command = new CreateLearningOfferCommand("New Course", "Description");

        LearningOfferId actual = service.createLearningOffer(command);

        assertThat(actual).isNotNull();
        assertThat(actual.id()).isNotNull();
    }

    @Test
    void shouldCreateLearningOffer() {
        CreateLearningOfferCommand command = new CreateLearningOfferCommand("New Course", "Description");

        LearningOfferId id = service.createLearningOffer(command);

        LearningOffer actual = thenLearningOfferSaved();
        assertThat(actual).extracting("learningOfferId").isEqualTo(id);
        assertThat(actual).extracting("title").isEqualTo("New Course");
        assertThat(actual).extracting("description").isEqualTo("Description");
    }

    @Test
    void shouldAddTrainingOfferToLearningOfferWhenNoTrainingOfferIsPresent() {
        UUID trainingOfferId = UUID.randomUUID();
        UUID learningOfferId = existingLearningOffer();
        UpdateLearningOfferCommand command = new UpdateLearningOfferCommand(learningOfferId, List.of(trainingOfferId));

        service.addTrainingOfferToLearningOffer(command);

        assertThat(thenLearningOfferSaved())
                .extracting("trainingOfferIds").asInstanceOf(InstanceOfAssertFactories.SET)
                .containsExactly(new TrainingOfferId(trainingOfferId));
    }

    @Test
    void shouldAddTrainingOfferToLearningOfferWhenTrainingOfferIsPresent() {
        UUID existingTrainingOfferId = UUID.randomUUID();
        UUID learningOfferId = existingLearningOfferWithTrainingOffers(Set.of(existingTrainingOfferId));

        UUID newTrainingOfferUuid = UUID.randomUUID();
        UpdateLearningOfferCommand command = new UpdateLearningOfferCommand(learningOfferId, List.of(newTrainingOfferUuid));

        service.addTrainingOfferToLearningOffer(command);

        assertThat(thenLearningOfferSaved())
                .extracting("trainingOfferIds").asInstanceOf(InstanceOfAssertFactories.SET)
                .containsExactlyInAnyOrder(
                        new TrainingOfferId(existingTrainingOfferId),
                        new TrainingOfferId(newTrainingOfferUuid));
    }

    @Test
    void shouldAddTrainingOfferToLearningOfferWhenTrainingOfferIsPresentAndAddingTheSameAgain() {
        UUID existingTrainingOfferId = UUID.randomUUID();
        UUID learningOfferId = existingLearningOfferWithTrainingOffers(Set.of(existingTrainingOfferId));
        UpdateLearningOfferCommand command = new UpdateLearningOfferCommand(learningOfferId, List.of(existingTrainingOfferId));

        service.addTrainingOfferToLearningOffer(command);

        assertThat(thenLearningOfferSaved())
                .extracting("trainingOfferIds").asInstanceOf(InstanceOfAssertFactories.SET)
                .containsExactly(new TrainingOfferId(existingTrainingOfferId));
    }

    @Test
    void shouldRemoveLearningOffer() {
        UUID learningOfferId = UUID.randomUUID();
        RemoveLearningOfferCommand command = new RemoveLearningOfferCommand(learningOfferId);

        service.removeLearningOffer(command);

        then(repository).should().delete(new LearningOfferId(learningOfferId));
    }

    private UUID existingLearningOfferWithTrainingOffers(Set<UUID> trainingOfferIds) {
        LearningOfferId learningOfferId = new LearningOfferId(UUID.randomUUID());
        LearningOffer learningOffer = new LearningOffer(learningOfferId, "Title", "Description");
        Set<TrainingOfferId> trainingOffers = trainingOfferIds.stream()
                .map(TrainingOfferId::new)
                .collect(toSet());
        learningOffer.add(trainingOffers);

        given(repository.findById(learningOfferId)).willReturn(learningOffer);
        return learningOfferId.id();
    }

    private UUID existingLearningOffer() {
        LearningOfferId learningOfferId = new LearningOfferId(UUID.randomUUID());
        LearningOffer learningOffer = new LearningOffer(learningOfferId, "Title", "Description");
        given(repository.findById(learningOfferId)).willReturn(learningOffer);
        return learningOfferId.id();
    }

    private LearningOffer thenLearningOfferSaved() {
        ArgumentCaptor<LearningOffer> captor = ArgumentCaptor.forClass(LearningOffer.class);
        verify(repository).save(captor.capture());
        return captor.getValue();
    }
}
