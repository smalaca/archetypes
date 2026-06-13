package com.smalaca.trainingcenter.trainingorders.domain.trainingorder.commands;

import java.util.List;

public record CreateTrainingOrderCommand(List<OrderParticipantDto> participants, List<TrainingOrderLineDto> orderLines) {
}
