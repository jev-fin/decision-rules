package org.company.decisionrules.rulebook;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import com.deliveredtechnologies.rulebook.model.runner.RuleBookRunner;
import org.company.decisionrules.model.ApplicationData;
import org.company.decisionrules.model.RegistryData;
import org.company.decisionrules.rulebook.rules.DecisionRuleBook;

import java.util.ArrayList;
import java.util.List;

import static org.company.decisionrules.easyrules.constants.FactNames.*;

public class RuleBookDecision {
    public List<String> decisionJava(ApplicationData applicationData, RegistryData registryData, List<String> violations) {
        NameValueReferableMap facts = new FactMap();
        facts.setValue(APPLICATION_DATA, applicationData);
        facts.setValue(REGISTRY_DATA, registryData);
        facts.setValue(VIOLATIONS, violations);

        RuleBook decisionRuleBook = RuleBookBuilder.create(DecisionRuleBook.class)
//                .withResultType(List.class)
//                .withDefaultResult(new ArrayList<>())
                .build();
        decisionRuleBook.run(facts);
        decisionRuleBook.getResult();
        return violations;
    }

    public List<String> decisionPOJO(ApplicationData applicationData, RegistryData registryData, List<String> violations) {
        NameValueReferableMap facts = new FactMap();
        facts.setValue(APPLICATION_DATA, applicationData);
        facts.setValue(REGISTRY_DATA, registryData);
        facts.setValue(VIOLATIONS, violations);

        RuleBookRunner decisionRuleBook = new RuleBookRunner("org.company.decisionrules.rulebook.rules");
//        decisionRuleBook.setDefaultResult(List.class);
        decisionRuleBook.run(facts);
//        decisionRuleBook.getResult();
        return violations;
    }
}
