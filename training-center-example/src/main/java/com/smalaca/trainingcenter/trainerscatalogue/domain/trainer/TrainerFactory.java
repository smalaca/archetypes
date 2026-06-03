package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

public class TrainerFactory {
    private final TrainerExperienceSeniorityConstraint constraint;

    private TrainerFactory(TrainerExperienceSeniorityConstraint constraint) {
        this.constraint = constraint;
    }

    public static TrainerFactory trainerFactory(SeniorityService seniorityService) {
        return new TrainerFactory(new TrainerExperienceSeniorityConstraint(seniorityService));
    }

    public Trainer create(TrainerId trainerId, UserId userId, TrainerNumber trainerNumber) {
        if (constraint.isSatisfiedBy(userId)) {
            return new Trainer(trainerId, userId, trainerNumber);
        }

        throw new RuntimeException("Trainer constraints not satisfied");
    }
}
