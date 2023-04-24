package org.company.decisionrules.model;

public record RegistryData(
        Integer companiesAgeInMonths,
        Integer managerNotChangedInMonths,
        boolean companyActive
) {
}
