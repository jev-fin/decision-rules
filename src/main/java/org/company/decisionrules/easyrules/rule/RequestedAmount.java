package org.company.decisionrules.easyrules.rule;

import org.company.decisionrules.model.ApplicationData;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.List;

import static org.company.decisionrules.easyrules.constants.FactNames.APPLICATION_DATA;
import static org.company.decisionrules.easyrules.constants.FactNames.VIOLATIONS;

@Rule(name = "Requested amount", description = "If requested amount is more than 5000 EUR, then APPLICATION_AMOUNT_EXCEEDED")
public class RequestedAmount {
    @Condition
    public boolean condition(@Fact(APPLICATION_DATA) ApplicationData application) {
        return application.requestedAmount().doubleValue() > 5000;
    }

    @Action
    public void action(@Fact(VIOLATIONS) List<String> violations) {
        violations.add("APPLICATION_AMOUNT_EXCEEDED");
    }
}
