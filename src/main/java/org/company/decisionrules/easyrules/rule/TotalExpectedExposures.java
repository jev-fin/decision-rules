package org.company.decisionrules.easyrules.rule;

import org.company.decisionrules.model.ApplicationData;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.List;

import static org.company.decisionrules.easyrules.constants.FactNames.APPLICATION_DATA;
import static org.company.decisionrules.easyrules.constants.FactNames.VIOLATIONS;

@Rule(name = "Total expected exposures", description = "If total expected exposures is greater than 1000, then TOTAL_EXPECTED_EXPOSURES_EXCEEDED")
public class TotalExpectedExposures {
    @Condition
    public boolean condition(@Fact(APPLICATION_DATA) ApplicationData application) {
        return application.totalExpectedExposures().doubleValue() > 50000;
    }

    @Action
    public void action(@Fact(VIOLATIONS) List<String> violation) {
        violation.add("TOTAL_EXPECTED_EXPOSURES_EXCEEDED");
    }
}
