# Decision rules comparison

### Description

Business required simple solution to apply business rules. Project is created to compare different rule engines and
present results in a simple way.

## Rule engines

### Easy Rules

Link to description: https://www.baeldung.com/java-rule-engines#easy-rules

GitHub: https://github.com/j-easy/easy-rules
Maven repository: https://mvnrepository.com/artifact/org.jeasy/easy-rules-core

#### Cons

* **Library is in maintenance mode. No new features will be added.**
  As of December 2020, Easy Rules is in maintenance mode. This means only bug fixes will be addressed from now on.
  Version
  4.1.x is the only supported version. Please consider upgrading to this version at your earliest convenience.
* this framework doesn’t implement the JSR94 standard
* last version released 2020 December

#### Pros

* Lightweight library and easy to learn API
* POJO based development with an annotation programming model
* Useful abstractions to define business rules and apply them easily with Java
* The ability to create composite rules from primitive ones
* The ability to define rules using an Expression Language (Like MVEL, SpEL and JEXL)

### RuleBook

References:

* https://www.baeldung.com/java-rule-engines
* https://dzone.com/articles/rulebook-a-simple-rules-engine-that-leverages-java

Git repository: https://github.com/deliveredtechnologies/rulebook
Maven repository: https://mvnrepository.com/artifact/com.deliveredtechnologies/rulebook-core

#### Con

* Quite a lot of boilerplate code when rules are defined in java code
* Unable parse result of rule
* There are no declarative rules
* Last version released 2020

#### Pros

* Could write rules in POJO and boilerplate code reduced
* Good readability of rules in POJO implementation

### Evrete

References:

* https://www.baeldung.com/java-evrete-rule-engine
* https://www.evrete.org

Git repository: https://github.com/evrete/evrete/
Maven repository: https://mvnrepository.com/artifact/org.evrete/evrete-core

#### Cons

* not readable inline implementation for many rules

#### Pros

* Possibility collect rules in sets
* Rules written in ruleset easy to read and modify
* Active support latest commit 2023
* Last version released 2023 april