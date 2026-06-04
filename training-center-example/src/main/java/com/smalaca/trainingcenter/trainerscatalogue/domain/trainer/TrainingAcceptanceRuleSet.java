package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.archetypes.annotations.ArchetypeRule;
import java.util.List;

@ArchetypeRule.RuleSet
class TrainingAcceptanceRuleSet {
    private final List<Object> rules;

    TrainingAcceptanceRuleSet(TrainerExpertiseRule expertiseRule, TrainerWorkloadRule workloadRule, TrainerCertificationRule certificationRule) {
        this.rules = List.of(expertiseRule, workloadRule, certificationRule);
    }

    boolean canAccept(TrainingContext context) {
        return rules.stream().allMatch(rule -> {
            if (rule instanceof TrainerExpertiseRule) {
                return ((TrainerExpertiseRule) rule).isSatisfiedBy(context);
            } else if (rule instanceof TrainerWorkloadRule) {
                return ((TrainerWorkloadRule) rule).isSatisfiedBy(context);
            } else if (rule instanceof TrainerCertificationRule) {
                return ((TrainerCertificationRule) rule).isSatisfiedBy(context);
            }
            return false;
        });
    }
}
