package com.smalaca.trainingcenter.trainingorders.application.trainingorder;

import java.math.BigDecimal;
import java.util.UUID;

public record TrainingOrderLineDto(UUID sellableItemId, BigDecimal quantity, BigDecimal priceAmount, String currency) {
}
