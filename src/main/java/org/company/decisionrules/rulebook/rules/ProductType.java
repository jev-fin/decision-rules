package org.company.decisionrules.rulebook.rules;

import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;
import org.company.decisionrules.model.ApplicationData;
import org.company.decisionrules.model.RegistryData;

import java.util.List;

import static org.company.decisionrules.easyrules.constants.FactNames.*;

@Rule
public class ProductType {
    @Given(APPLICATION_DATA)
    private ApplicationData applicationData;
    @Given(REGISTRY_DATA)
    private RegistryData registryData;

    @Given(VIOLATIONS)
    private List<String> violations;

    @When
    public boolean when() {
        return !registryData.companyActive() && !"CREDIT_LINE".equals(applicationData.productType());
    }

    @Then
    public void then() {
        violations.add("PRODUCT_TYPE");
    }
}
