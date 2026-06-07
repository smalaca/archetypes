package com.smalaca.trainingcenter.companiescatalogue.domain.company;

import java.util.List;
import java.util.UUID;

public class CompanyTestFactory {
    public static Company withBusinessUnit(CompanyId companyId, BusinessUnitId businessUnitId) {
        Company company = new Company(companyId, List.of(), new CompanyName(UUID.randomUUID().toString()));
        company.add(new BusinessUnit(businessUnitId, UUID.randomUUID().toString()));
        return company;
    }
}
