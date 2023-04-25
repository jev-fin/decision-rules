package org.company.decisionrules.rulebook.rules;


import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;
import org.company.decisionrules.model.ApplicationData;

import java.math.BigDecimal;
import java.util.List;

import static org.company.decisionrules.easyrules.constants.FactNames.APPLICATION_DATA;
import static org.company.decisionrules.easyrules.constants.FactNames.VIOLATIONS;

@Rule
public class ApplicationAmountExceeded {
    @Given(APPLICATION_DATA)
    private ApplicationData applicationData;

    @Given(VIOLATIONS)
    private List<String> violations;

    @When
    public boolean when() {
        return applicationData.requestedAmount().compareTo(BigDecimal.valueOf(50000.00)) > 0;
    }

    @Then
    public void then() {
        violations.add("TOTAL_EXPECTED_EXPOSURES_EXCEEDED");
    }
}
