package com.smalaca.trainingcenter.trainingorders.domain.trainingorder.commands;

import java.math.BigDecimal;
import java.util.UUID;

public record TrainingOrderLineDto(UUID sellableItemId, BigDecimal quantity, BigDecimal priceAmount, String currency) {
}
