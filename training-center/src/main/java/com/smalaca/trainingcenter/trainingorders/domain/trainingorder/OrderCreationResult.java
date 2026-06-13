package com.smalaca.trainingcenter.trainingorders.domain.trainingorder;

import com.smalaca.trainingcenter.trainingorders.domain.trainingorder.events.TrainingOrderOpened;

public record OrderCreationResult(TrainingOrder trainingOrder, TrainingOrderOpened trainingOrderOpened) {
}
