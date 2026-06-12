package com.smalaca.trainingcenter.trainingorders.application.trainingorder;

import java.util.List;

public record CreateTrainingOrderCommand(List<OrderParticipantDto> participants, List<TrainingOrderLineDto> orderLines) {
}
