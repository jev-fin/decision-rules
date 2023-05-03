package org.company.decisionrules.everete.rules;

import java.util.List;
import org.company.decisionrules.model.ApplicationData;
import org.company.decisionrules.model.RegistryData;
import org.evrete.api.RhsContext;
import org.evrete.dsl.annotation.Fact;
import org.evrete.dsl.annotation.Rule;
import org.evrete.dsl.annotation.RuleSet;
import org.evrete.dsl.annotation.Where;

@RuleSet
public class ProductRuleSet {

    @Rule
    @Where({
        "$ad.requestedAmount().doubleValue() > 5000",
        "!$v.contains(\"APPLICATION_AMOUNT_EXCEEDED\")"
    })
    public void applicationAmountExceeded(
        RhsContext ctx,
        ApplicationData $ad,
        @Fact("$v") List<String> violations) {
        violations.add("APPLICATION_AMOUNT_EXCEEDED");
        ctx.update(violations);
    }

    @Rule
    @Where({
        "$ad.companyHasNegativeInfo()",
        "!$v.contains(\"COMPANY_HAS_NEGATIVE_INFO\")"
    })
    public void companyHasNegativeInformation(
        RhsContext ctx,
        ApplicationData $ad,
        @Fact("$v") List<String> violations) {
        violations.add("COMPANY_HAS_NEGATIVE_INFO");
        ctx.update(violations);
    }

    @Rule
    @Where({
        "$ad.totalExpectedExposures().doubleValue() > 50000",
        "!$v.contains(\"TOTAL_EXPECTED_EXPOSURES_EXCEEDED\")"
    })
    public void totalExpectedExposureExceeded(
        RhsContext ctx,
        ApplicationData $ad,
        @Fact("$v") List<String> violations) {
        violations.add("TOTAL_EXPECTED_EXPOSURES_EXCEEDED");
        ctx.update(violations);
    }

    @Rule
    @Where({
        "$rd.companiesAgeInMonths() == null || $rd.companiesAgeInMonths().compareTo(12) < 0",
        "!$v.contains(\"YOUNG_COMPANY\")"
    })
    public void youngCompany(
        RhsContext ctx,
        RegistryData $rd,
        @Fact("$v") List<String> violations) {
        violations.add("YOUNG_COMPANY");
        ctx.update(violations);
    }

    @Rule
    @Where({
        "$rd.companyActive() || !\"CREDIT_LINE\".equals($ad.productType())",
        "!$v.contains(\"PRODUCT_TYPE\")"
    })
    public void productType(
        RhsContext ctx,
        ApplicationData $ad,
        RegistryData $rd,
        @Fact("$v") List<String> violations) {
        violations.add("PRODUCT_TYPE");
        ctx.update(violations);
    }
}
