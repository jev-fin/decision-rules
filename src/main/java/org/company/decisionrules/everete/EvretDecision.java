package org.company.decisionrules.everete;

import java.io.IOException;
import java.util.List;
import org.company.decisionrules.everete.rules.ProductRuleSet;
import org.company.decisionrules.model.ApplicationData;
import org.company.decisionrules.model.RegistryData;
import org.evrete.KnowledgeService;
import org.evrete.api.Knowledge;
import org.evrete.api.StatelessSession;

public class EvretDecision {
    public List<String> decisionAnnotated(
        ApplicationData applicationData,
        RegistryData registryData,
        List<String> violations
    ) throws IOException {
        KnowledgeService service = new KnowledgeService();
        Knowledge knowledge = service.newKnowledge("JAVA-CLASS", ProductRuleSet.class);

        knowledge.newStatelessSession().insertAndFire(applicationData, registryData, violations);

        return violations;
    }

    public List<String> decision(ApplicationData applicationData, RegistryData registryData, List<String> violations
    ) {
        KnowledgeService service = new KnowledgeService();

        Knowledge knowledge = service
            .newKnowledge()
            .newRule("APPLICATION_AMOUNT_EXCEEDED")
            .forEach(
                "$ad", ApplicationData.class,
                "$v", List.class
            )
            .where("$ad.requestedAmount().doubleValue() > 5000")
            .where("!$v.contains(\"APPLICATION_AMOUNT_EXCEEDED\")")
            .execute(context -> {
                List<String> $v = context.get("$v");
                $v.add("APPLICATION_AMOUNT_EXCEEDED");
                context.update($v);
            })
            .newRule("COMPANY_HAS_NEGATIVE_INFO")
            .forEach(
                "$ad", ApplicationData.class,
                "$v", List.class
            )
            .where("$ad.companyHasNegativeInfo()")
            .where("!$v.contains(\"COMPANY_HAS_NEGATIVE_INFO\")")
            .execute(context -> {
                List<String> $v = context.get("$v");
                $v.add("COMPANY_HAS_NEGATIVE_INFO");
                context.update($v);
            })
            .newRule("TOTAL_EXPECTED_EXPOSURES_EXCEEDED")
            .forEach(
                "$ad", ApplicationData.class,
                "$v", List.class
            )
            .where("$ad.totalExpectedExposures().doubleValue() > 50000")
            .where("!$v.contains(\"TOTAL_EXPECTED_EXPOSURES_EXCEEDED\")")
            .execute(context -> {
                List<String> $v = context.get("$v");
                $v.add("TOTAL_EXPECTED_EXPOSURES_EXCEEDED");
                context.update($v);
            })
            .newRule("YOUNG_COMPANY")
            .forEach(
                "$rd", RegistryData.class,
                "$v", List.class
            )
            .where("$rd.companiesAgeInMonths() == null || $rd.companiesAgeInMonths().compareTo(12) < 0")
            .where("!$v.contains(\"YOUNG_COMPANY\")")
            .execute(context -> {
                List<String> $v = context.get("$v");
                $v.add("YOUNG_COMPANY");
                context.update($v);
            })
            .newRule("PRODUCT_TYPE")
            .forEach(
                "$ad", ApplicationData.class,
                "$rd", RegistryData.class,
                "$v", List.class
            )
            .where("$rd.companyActive() || !\"CREDIT_LINE\".equals($ad.productType())")
            .where("!$v.contains(\"PRODUCT_TYPE\")")
            .execute(context -> {
                List<String> $v = context.get("$v");
                $v.add("PRODUCT_TYPE");
                context.update($v);
            });

        StatelessSession session = knowledge.newStatelessSession();
        session.insertAndFire(applicationData, registryData, violations);

        service.shutdown();

        return violations;
    }
}
