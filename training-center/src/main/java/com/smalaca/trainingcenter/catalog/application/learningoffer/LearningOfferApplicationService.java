package com.smalaca.trainingcenter.catalog.application.learningoffer;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture;
import com.smalaca.trainingcenter.catalog.domain.learningoffer.LearningOffer;
import com.smalaca.trainingcenter.catalog.domain.learningoffer.LearningOfferId;
import com.smalaca.trainingcenter.catalog.domain.learningoffer.LearningOfferRepository;
import com.smalaca.trainingcenter.catalog.domain.learningoffer.SellableItemId;

import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toSet;

@DomainDrivenDesign.ApplicationLayer
@ArchetypeProduct.Catalog
public class LearningOfferApplicationService {
    private final LearningOfferRepository repository;

    public LearningOfferApplicationService(LearningOfferRepository repository) {
        this.repository = repository;
    }

    @PortsAndAdaptersArchitecture.DrivingPort
    public LearningOfferId createLearningOffer(CreateLearningOfferCommand command) {
        LearningOfferId id = new LearningOfferId(UUID.randomUUID());

        LearningOffer learningOffer = new LearningOffer(id, command.title(), command.description());

        repository.save(learningOffer);
        return id;
    }

    @PortsAndAdaptersArchitecture.DrivingPort
    public void addSellableItemToLearningOffer(UpdateLearningOfferCommand command) {
        LearningOfferId id = new LearningOfferId(command.learningOfferId());
        LearningOffer learningOffer = repository.findById(id);
        Set<SellableItemId> sellableItems = command.sellableItemIds().stream().map(SellableItemId::new).collect(toSet());

        learningOffer.add(sellableItems);

        repository.save(learningOffer);
    }

    @PortsAndAdaptersArchitecture.DrivingPort
    public void removeLearningOffer(RemoveLearningOfferCommand command) {
        LearningOfferId learningOfferId = new LearningOfferId(command.learningOfferId());

        repository.delete(learningOfferId);
    }
}
