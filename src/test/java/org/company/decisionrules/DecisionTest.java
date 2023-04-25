package org.company.decisionrules;

import org.company.decisionrules.model.ApplicationData;
import org.company.decisionrules.model.RegistryData;
import org.company.decisionrules.easyrules.EasyRulesDecision;
import org.company.decisionrules.rulebook.RuleBookDecision;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecisionTest {
    ApplicationData applicationData;
    RegistryData registryData;
    List<String> violations;

    @BeforeEach
    void setUp() {
        applicationData = new ApplicationData(BigDecimal.valueOf(7000.0), BigDecimal.valueOf(70000.0), true, "CREDIT_CARD");
        registryData = new RegistryData(null, 5, false);
        violations = new ArrayList<>();
    }

    @Test
    void EasyRulesDecisionTest() {
        var decisionEngine = new EasyRulesDecision();
        assertEquals(3, decisionEngine.decisionJava(applicationData, registryData, violations).size());
    }

    @Test
    void EasyRulesDecisionDeclarativeYAMLTest() throws Exception {
        var decisionEngine = new EasyRulesDecision();
        assertEquals(7, decisionEngine.decisionDeclarativeYaml(applicationData, registryData, violations).size());
    }

    @Test
    void EasyRulesDecisionDeclarativeJSONTest() throws Exception {
        var decisionEngine = new EasyRulesDecision();
        assertEquals(3, decisionEngine.decisionDeclarativeJSON(applicationData, registryData, violations).size());
    }

    @Test
    void RuleBookDecisionTest() {
        var decisionEngine = new RuleBookDecision();
        assertEquals(5, decisionEngine.decisionJava(applicationData, registryData, violations).size());
    }
    @Test
    void RuleBookDecisionPOJOTest() {
        var decisionEngine = new RuleBookDecision();
        assertEquals(3, decisionEngine.decisionPOJO(applicationData, registryData, violations).size());
    }
}
