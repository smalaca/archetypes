package com.smalaca.trainingcenter.catalog.application.learningoffer;

import com.smalaca.trainingcenter.catalog.domain.learningoffer.LearningOffer;
import com.smalaca.trainingcenter.catalog.domain.learningoffer.LearningOfferId;
import com.smalaca.trainingcenter.catalog.domain.learningoffer.LearningOfferRepository;
import com.smalaca.trainingcenter.catalog.domain.learningoffer.SellableItemId;
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
    void shouldAddSellableItemToLearningOfferWhenNoSellableItemIsPresent() {
        UUID sellableItemId = UUID.randomUUID();
        UUID learningOfferId = existingLearningOffer();
        UpdateLearningOfferCommand command = new UpdateLearningOfferCommand(learningOfferId, List.of(sellableItemId));

        service.addSellableItemToLearningOffer(command);

        assertThat(thenLearningOfferSaved())
                .extracting("sellableItemIds").asInstanceOf(InstanceOfAssertFactories.SET)
                .containsExactly(new SellableItemId(sellableItemId));
    }

    @Test
    void shouldAddSellableItemToLearningOfferWhenSellableItemIsPresent() {
        UUID existingSellableItemId = UUID.randomUUID();
        UUID learningOfferId = existingLearningOfferWithSellableItems(Set.of(existingSellableItemId));

        UUID newSellableItemUuid = UUID.randomUUID();
        UpdateLearningOfferCommand command = new UpdateLearningOfferCommand(learningOfferId, List.of(newSellableItemUuid));

        service.addSellableItemToLearningOffer(command);

        assertThat(thenLearningOfferSaved())
                .extracting("sellableItemIds").asInstanceOf(InstanceOfAssertFactories.SET)
                .containsExactlyInAnyOrder(
                        new SellableItemId(existingSellableItemId),
                        new SellableItemId(newSellableItemUuid));
    }

    @Test
    void shouldAddSellableItemToLearningOfferWhenSellableItemIsPresentAndAddingTheSameAgain() {
        UUID existingSellableItemId = UUID.randomUUID();
        UUID learningOfferId = existingLearningOfferWithSellableItems(Set.of(existingSellableItemId));
        UpdateLearningOfferCommand command = new UpdateLearningOfferCommand(learningOfferId, List.of(existingSellableItemId));

        service.addSellableItemToLearningOffer(command);

        assertThat(thenLearningOfferSaved())
                .extracting("sellableItemIds").asInstanceOf(InstanceOfAssertFactories.SET)
                .containsExactly(new SellableItemId(existingSellableItemId));
    }

    @Test
    void shouldRemoveLearningOffer() {
        UUID learningOfferId = UUID.randomUUID();
        RemoveLearningOfferCommand command = new RemoveLearningOfferCommand(learningOfferId);

        service.removeLearningOffer(command);

        then(repository).should().delete(new LearningOfferId(learningOfferId));
    }

    private UUID existingLearningOfferWithSellableItems(Set<UUID> sellableItemIds) {
        LearningOfferId learningOfferId = new LearningOfferId(UUID.randomUUID());
        LearningOffer learningOffer = new LearningOffer(learningOfferId, "Title", "Description");
        Set<SellableItemId> sellableItems = sellableItemIds.stream()
                .map(SellableItemId::new)
                .collect(toSet());
        learningOffer.add(sellableItems);

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
