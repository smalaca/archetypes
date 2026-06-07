package com.smalaca.trainingcenter.companiescatalogue.domain.representation;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.companiescatalogue.domain.company.BusinessUnitId;
import com.smalaca.trainingcenter.companiescatalogue.domain.company.CompanyId;
import com.smalaca.trainingcenter.companiescatalogue.domain.representative.RepresentativeId;

import java.util.List;
import java.util.Optional;

@DomainDrivenDesign.Factory
public class RepresentationFactory {
    private final List<RepresentationConstraint> constraints;

    public RepresentationFactory(List<RepresentationConstraint> constraints) {
        this.constraints = constraints;
    }

    public Optional<Representation> representation(RepresentativeId representativeId, CompanyId companyId, BusinessUnitId businessUnitId) {
        if (canRepresent(representativeId, companyId, businessUnitId)) {
            return Optional.of(Representation.representation(representativeId, businessUnitId));
        }

        return Optional.empty();
    }

    private boolean canRepresent(RepresentativeId representativeId, CompanyId companyId, BusinessUnitId businessUnitId) {
        return constraints.stream().allMatch(constraint -> constraint.canRepresent(representativeId, companyId, businessUnitId));
    }
}
