package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import com.smalaca.archetypes.annotations.ArchetypeRule;

@ArchetypeParty.PartyRole
@ArchetypeParty.PartyRoleType
public class Trainer {
    private final TrainerId trainerId;
    private final UserId userId;
    private final TrainerNumber trainerNumber;
    @ArchetypeRule.RuleSet
    private final TrainingAcceptanceRuleSet trainingAcceptanceRuleSet;

    Trainer(TrainerId trainerId, UserId userId, TrainerNumber trainerNumber, TrainingAcceptanceRuleSet trainingAcceptanceRuleSet) {
        this.trainerId = trainerId;
        this.userId = userId;
        this.trainerNumber = trainerNumber;
        this.trainingAcceptanceRuleSet = trainingAcceptanceRuleSet;
    }

    public boolean acceptTraining(TrainingContext context) {
        return trainingAcceptanceRuleSet.canAccept(context);
    }
}
