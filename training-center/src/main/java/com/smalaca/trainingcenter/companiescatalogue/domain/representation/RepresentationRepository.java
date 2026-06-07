package com.smalaca.trainingcenter.companiescatalogue.domain.representation;

import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture;
import com.smalaca.trainingcenter.companiescatalogue.domain.company.BusinessUnitId;
import com.smalaca.trainingcenter.companiescatalogue.domain.company.CompanyId;
import com.smalaca.trainingcenter.companiescatalogue.domain.representative.RepresentativeId;

@PortsAndAdaptersArchitecture.DrivenPort
public interface RepresentationRepository {
    boolean isRepresentingAnyOtherCompany(RepresentativeId representativeId, CompanyId companyId);
    int countBusinessUnitsRepresentedIn(RepresentativeId representativeId, CompanyId companyId);
    boolean isBusinessUnitAlreadyRepresented(BusinessUnitId businessUnitId);
}
