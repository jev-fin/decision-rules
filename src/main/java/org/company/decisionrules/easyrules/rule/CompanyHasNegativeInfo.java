package org.company.decisionrules.easyrules.rule;

import org.company.decisionrules.model.ApplicationData;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.List;

import static org.company.decisionrules.easyrules.constants.FactNames.APPLICATION_DATA;
import static org.company.decisionrules.easyrules.constants.FactNames.VIOLATIONS;

@Rule(name = "Company has negative info", description = "If company has negative info, then COMPANY_HAS_NEGATIVE_INFO")
public class CompanyHasNegativeInfo {
    @Condition
    public boolean condition(@Fact(APPLICATION_DATA) ApplicationData application) {
        return application.companyHasNegativeInfo();
    }

    @Action
    public void action(@Fact(VIOLATIONS) List<String> violation) {
        violation.add("COMPANY_HAS_NEGATIVE_INFO");
    }
}
