package org.company.decisionrules.model;

import java.math.BigDecimal;

public record ApplicationData(
        BigDecimal requestedAmount,
        BigDecimal totalExpectedExposures,
        Boolean companyHasNegativeInfo,
        String productType
) {
}
