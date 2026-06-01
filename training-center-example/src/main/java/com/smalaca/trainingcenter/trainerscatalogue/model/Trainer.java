package com.smalaca.trainingcenter.trainerscatalogue.model;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRole
public class Trainer {
    private final TrainerId trainerId;
    private final UserId userId;
    private final TrainerNumber trainerNumber;

    public Trainer(TrainerId trainerId, UserId userId, TrainerNumber trainerNumber) {
        this.trainerId = trainerId;
        this.userId = userId;
        this.trainerNumber = trainerNumber;
    }
}
