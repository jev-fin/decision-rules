# Decision rules comparison

### Description

Business required simple solution to apply business rules. Project is created to compare different rule engines and
present results in a simple way.

## Rule engines

### Easy Rules

Link to description: https://www.baeldung.com/java-rule-engines#easy-rules
GitHub: https://github.com/j-easy/easy-rules

#### Cons
* **Library is in maintenance mode. No new features will be added.**
As of December 2020, Easy Rules is in maintenance mode. This means only bug fixes will be addressed from now on. Version
4.1.x is the only supported version. Please consider upgrading to this version at your earliest convenience.
* this framework doesn’t implement the JSR94 standard

#### Pros
* Lightweight library and easy to learn API
* POJO based development with an annotation programming model
* Useful abstractions to define business rules and apply them easily with Java
* The ability to create composite rules from primitive ones
* The ability to define rules using an Expression Language (Like MVEL, SpEL and JEXL)