> principles
---

# Spring Based Paved Road - Implementation Principles
---

## Principles

1. __Modular with single responsibility__
    1. Each module owns only non-functional features from one technical stack
    1. Each module defines its own dependencies
    1. Each module normally is independent unless there are common shared features
<br>

    > Benefits
    > - Avoids tight-coupling between applications
    > - Increases reusability
    > - Maintain agility, no unnecessary dependency on unused features

2. __Delegates dependency management to spring-boot-dependencies__
    1. DO NOT introduce new dependency unless there is no Spring Boot integrated solution available.
    1. DO NOT override versions of dependencies unless approved
<br>

    > Benefit
    > - Delegates huge effort and cost of maintaining dependency management to Spring Boot ecosystem
    > - Minimizes upgrade effort and cost

3. __Non-invasive and minimal customization__
    1. Engineers create POJOs and practice Object-oriented programming
    1. No restrains on programming paradigms: imperative, reactive, service, event driven, APIs are all supported
    1. Not requires extending any concrete class
    1. Normally not enforce rigid structure nor processing approaches(series of steps, other than well established patterns: MVC, JPA etc.)
<br>

    > Benefit
    > - Engineers will be able to code normally and easily
    > - Engineers have the power to use the more comfortable and appropriate styles to work in their business domains.

4. __Convention over configuration__
    1. Provides sensible defaults and reducing explicit code of non-functional features to minimal.
<br>

    > Benefit
    > - High productivity
    > - Knowledge sharing
    > - Reduces learning curve


5. __Standardizes non-functional feature, but no restriction of sensible choices__
    1. Standardizes naming conventions, project structure, configuration, error handling, alert contract etc.
    1. opinionated prescription of common non-functional features out-of-box
    1. Not block any advanced Spring Boot features
    1. Not block any new Spring Boot features added in future
        - Technical reviews and approvals may be require
        - Team may be required to create quality new feature implementation and merge it to paved road
<br>

    > Benefits
    > - Diminishes boilerplate code
    > - No reinvent the wheel
    > - Eliminates duplicate effort

6. __Provides working sample application__
    1. Each module is a working sample application
    1. Working samples cover all out-of-box features
    1. Working templates of recommended best practices
<br>

    > Benefits
    > - Reduces learning curve
    > - Better debugging and trouble-shooting capability

7. __Complete Development Environment with Unit Test, Integration Test support and CICD pipeline__
    1. Dev environment with Live Load support to provide immediate feedback
    1. Unit Test Support for local and build server
    1. Integration Test Support for local and build server
    1. CICD pipeline
<br>

    > Benefits
    > - High efficiency
    > - High productivity

8. __Working Application deployable to Cloud infrastructure with all working data store Instances from E1 to E3 from Day 1__
<br>

    > Benefits
    > - Reduce adoption risk
    > - best speed of delivery
    > - reduce cross team communication effort


---
