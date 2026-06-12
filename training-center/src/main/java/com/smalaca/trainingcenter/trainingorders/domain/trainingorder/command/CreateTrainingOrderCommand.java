package com.smalaca.trainingcenter.trainingorders.domain.trainingorder.command;

import java.util.List;

public record CreateTrainingOrderCommand(List<OrderParticipantDto> participants, List<TrainingOrderLineDto> orderLines) {
}
