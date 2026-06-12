package com.smalaca.trainingcenter.trainingorders.domain.trainingorder;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import java.math.BigDecimal;

@DomainDrivenDesign.ValueObject
public record Money(BigDecimal amount, String currency) {
}
