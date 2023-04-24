package org.company.decisionrules.easyrules;

import org.company.decisionrules.easyrules.rule.CompanyHasNegativeInfo;
import org.company.decisionrules.easyrules.rule.RequestedAmount;
import org.company.decisionrules.easyrules.rule.TotalExpectedExposures;
import org.company.decisionrules.model.ApplicationData;
import org.company.decisionrules.model.RegistryData;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.reader.JsonRuleDefinitionReader;
import org.jeasy.rules.support.reader.RuleDefinitionReader;
import org.jeasy.rules.support.reader.YamlRuleDefinitionReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.company.decisionrules.easyrules.constants.FactNames.*;

public class EasyRulesDecision {
    public List<String> decisionJava(ApplicationData applicationData, RegistryData registryData, List<String> violations) {
        Rules rules = new Rules();
        rules.register(new RequestedAmount());
        rules.register(new CompanyHasNegativeInfo());
        rules.register(new TotalExpectedExposures());

        Facts facts = getFacts(applicationData, registryData, violations);

        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
        return violations;
    }

    public List<String> decisionDeclarativeYaml(ApplicationData applicationData, RegistryData registryData, List<String> violations) throws Exception {
        Rules rules = getRules("/easyrules/rules.yml", new YamlRuleDefinitionReader());

        Facts facts = getFacts(applicationData, registryData, violations);

        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
        return violations;
    }

    public List<String> decisionDeclarativeJSON(ApplicationData applicationData, RegistryData registryData, List<String> violations) throws Exception {
        Rules rules = getRules("/easyrules/rules.json", new JsonRuleDefinitionReader());

        Facts facts = getFacts(applicationData, registryData, violations);

        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
        return violations;
    }

    private Rules getRules(String name, RuleDefinitionReader ruleDefinitionReader) throws Exception {
        InputStream inputStream = getClass().getResourceAsStream(name);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        MVELRuleFactory ruleFactory = new MVELRuleFactory(ruleDefinitionReader);
        return ruleFactory.createRules(streamReader);
    }

    private static Facts getFacts(ApplicationData applicationData, RegistryData registryData, List<String> violations) {
        Facts facts = new Facts();
        facts.put(APPLICATION_DATA, applicationData);
        facts.put(REGISTRY_DATA, registryData);
        facts.put(VIOLATIONS, violations);
        return facts;
    }
}
