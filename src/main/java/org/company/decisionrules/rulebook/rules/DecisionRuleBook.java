package org.company.decisionrules.rulebook.rules;

import com.deliveredtechnologies.rulebook.NameValueReferableTypeConvertibleMap;
import com.deliveredtechnologies.rulebook.lang.RuleBuilder;
import com.deliveredtechnologies.rulebook.model.rulechain.cor.CoRRuleBook;
import org.company.decisionrules.model.ApplicationData;
import org.company.decisionrules.model.RegistryData;

import java.math.BigDecimal;
import java.util.List;

import static org.company.decisionrules.easyrules.constants.FactNames.*;

public class DecisionRuleBook extends CoRRuleBook<List<String>> {
    @Override
    public void defineRules() {
        addRule(RuleBuilder.create().withResultType(List.class)
                .when(facts -> getApplicationData(facts).requestedAmount().compareTo(BigDecimal.valueOf(5000.0)) > 0)
                .then((facts, result) -> {
                    ((List) facts.getValue(VIOLATIONS)).add("APPLICATION_AMOUNT_EXCEEDED");
                    result.getValue().add("APPLICATION_AMOUNT_EXCEEDED");

                })
                .build());
        addRule(RuleBuilder.create().withResultType(List.class)
                .when(facts -> getApplicationData(facts).companyHasNegativeInfo())
                .then((facts, result) -> {
                    ((List) facts.getValue(VIOLATIONS)).add("COMPANY_HAS_NEGATIVE_INFO");
                    result.getValue().add("COMPANY_HAS_NEGATIVE_INFO");
                })
                .build());
        addRule(RuleBuilder.create().withResultType(List.class)
                .when(facts -> getApplicationData(facts).totalExpectedExposures().compareTo(BigDecimal.valueOf(50000.0)) > 0)
                .then((facts, result) -> {
                    ((List) facts.getValue(VIOLATIONS)).add("TOTAL_EXPECTED_EXPOSURES_EXCEEDED");
                    result.getValue().add("TOTAL_EXPECTED_EXPOSURES_EXCEEDED");
                })
                .build());
        addRule(RuleBuilder.create().withResultType(List.class)
                .when(facts -> getRegistryData(facts).companiesAgeInMonths() == null || getRegistryData(facts).companiesAgeInMonths().compareTo(12) < 0)
                .then((facts, result) -> {
                    ((List) facts.getValue(VIOLATIONS)).add("YOUNG_COMPANY");
                    result.getValue().add("YOUNG_COMPANY");
                })
                .build());
        addRule(RuleBuilder.create().withResultType(List.class)
                .when(facts -> getRegistryData(facts).companyActive() || !"CREDIT_LINE".equals(getApplicationData(facts).productType()))
                .then((facts, result) -> {
                    ((List) facts.getValue(VIOLATIONS)).add("PRODUCT_TYPE");
                    result.getValue().add("PRODUCT_TYPE");
                })
                .build());
    }

    private static ApplicationData getApplicationData(NameValueReferableTypeConvertibleMap<Object> facts) {
        return (ApplicationData) facts.getValue(APPLICATION_DATA);
    }

    private static RegistryData getRegistryData(NameValueReferableTypeConvertibleMap<Object> facts) {
        return (RegistryData) facts.getValue(REGISTRY_DATA);
    }
}
