package org.company.decisionrules.rulebook.rules;

import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;
import org.company.decisionrules.model.RegistryData;

import java.util.List;

import static org.company.decisionrules.easyrules.constants.FactNames.REGISTRY_DATA;
import static org.company.decisionrules.easyrules.constants.FactNames.VIOLATIONS;

@Rule
public class YoungCompany {

    @Given(REGISTRY_DATA)
    private RegistryData registryData;

    @Given(VIOLATIONS)
    private List<String> violations;

    @When
    public boolean when() {
        return registryData.companiesAgeInMonths() == null || registryData.companiesAgeInMonths() < 12;
//        return registryData.companiesAgeInMonths() < 12;
    }

    @Then
    public void then() {
        violations.add("YOUNG_COMPANY");
    }
}
