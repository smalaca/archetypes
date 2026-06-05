package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.archetypes.ArchetypeRule;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ArchetypeParty.PartyRole
@ArchetypeParty.PartyRoleType
public class Trainer {
    private final TrainerId trainerId;
    private final UserId userId;
    private final TrainerNumber trainerNumber;
    @ArchetypeRule.RuleSet
    private final TrainingAcceptancePolicy trainingAcceptancePolicy;
    private final List<UUID> acceptedTrainings = new ArrayList<>();

    Trainer(TrainerId trainerId, UserId userId, TrainerNumber trainerNumber, TrainingAcceptancePolicy trainingAcceptancePolicy) {
        this.trainerId = trainerId;
        this.userId = userId;
        this.trainerNumber = trainerNumber;
        this.trainingAcceptancePolicy = trainingAcceptancePolicy;
    }

    public void acceptTraining(TrainingContext context) {
        if (trainingAcceptancePolicy.canAccept(context)) {
            acceptedTrainings.add(context.trainingId());
        }
    }
}
