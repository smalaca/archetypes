package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import com.smalaca.archetypes.annotations.ArchetypeRule;
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
    private final TrainingAcceptanceRuleSet trainingAcceptanceRuleSet;
    private final List<UUID> acceptedTrainings = new ArrayList<>();

    Trainer(TrainerId trainerId, UserId userId, TrainerNumber trainerNumber, TrainingAcceptanceRuleSet trainingAcceptanceRuleSet) {
        this.trainerId = trainerId;
        this.userId = userId;
        this.trainerNumber = trainerNumber;
        this.trainingAcceptanceRuleSet = trainingAcceptanceRuleSet;
    }

    public void acceptTraining(TrainingContext context) {
        if (trainingAcceptanceRuleSet.canAccept(context)) {
            acceptedTrainings.add(context.trainingId());
        }
    }
}
