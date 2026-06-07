package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.List;

@DomainDrivenDesign.Policy
@ArchetypeRule.RuleSet
class TrainingAcceptancePolicy {
    private final List<TrainingRule> rules;

    TrainingAcceptancePolicy(List<TrainingRule> rules) {
        this.rules = rules;
    }

    TrainingAcceptanceResult canAccept(TrainingContext context) {
        TrainingAcceptanceResult finalResult = TrainingAcceptanceResult.accepted();

        for (TrainingRule rule : rules) {
            TrainingAcceptanceResult result = rule.isSatisfiedBy(context);

            if (result.isRejected()) {
                return result;
            }

            if (result.requiresManualIntervention()) {
                finalResult = result;
            }
        }

        return finalResult;
    }
}
