> README FILE

---

# Proposal of Spring Based Paved Road
---
---

## Problem Statement
---

Historically in GMST, applications have been developed in silos without a common design or centralized strategy. Wide range of technology stacks were casually chosen (e.g., IBM Mainframe, Salesforce, Aavaya, Salelytics, J2EE, Spring Boot, .Net, React, ePaaS, etc.).

### Challenges

- Rigid and Fragile (costly and lengthy effort to modify, unpredictable consequences from changes)
- Rapidly growing maintenance and testing cost
- Inconsistent quality, security controls, and various levels of maturity
- Duplicative and inconsistent implementations of common non-functional features (Observability, CICD, etc.)
- Low development efficiency and productivity
- Plagued with technical debts

### Solutions

> Starting from 2020, The **Amex Way** was originated at Technology enterprise level to transform how the applications were built. The Amex way is shared set of principles, > > > patterns, and practices that forms how we do software engineering at American Express.

> **Paved Roads** are prefabricated solutions of the Amex Way that enable engineers to work consistently, leverage well-understood architectural and operational patterns.

> Common non-functional features such as: high availability, high scalability, high resiliency, high performance, standard security practices, observability, error-handling are > available to engineers out-of-box.

## Major Technical issues without Paved Roads
---

**Shared principles, patterns, guidelines, and best practices are critical but not enough to warrant quality and speedy delivery.**

**Best Technology Stacks themselves do not guarantee high quality and high performance delivery. Less-controlled and less-constrained nature of distributed systems leads to much higher risk of serious technical issues that may generate catastrophic consequences from poor implementations.**

Engineers are facing the following technical obstacles:

### Steep learning curve
- **Breadth**
    - Large number of frameworks are available, Java, Spring Boot, Cassandra, Kafka, Couchbase, etc. and numerous front-end frameworks.
- **Depth**
    - Mature frameworks provide extensive rich set of enterprise-grade complex features
    - They also use many latest, advanced programming features (For java only: Generic, Functional Interfaces, AOP, Code instrumentation Asynchronous programming etc.)
    - The complexity of composition of modules, many layers above from scratch.

### Symptoms
- Programming from scratch without using frameworks at all, or only use few simplest features
- Excessive Repetition
- Re-invent wheels (same non-functional features)
- No consistency, no knowledge sharing (same feature, multiple implementations)
- Poor quality (very few engineers are highly skilled in all areas)
- Difficult to troubleshooting (Complexity and convention-based natures of modern frameworks make the troubleshooting not straightforward anymore)
- Difficult to test, to maintain
- Difficult to do integration (No standard convention)
- Difficult to upgrade

## Major Benefits of Paved Road Approach
---

### Significantly increase speed of delivery
(30 days to production, better than targeting goal of Technology)
- confident budget estimation, project planning, task scheduling.
- Reducing coding (enable to focus on mainly adding business logics)
- Reducing testing (no need to test non-functional features)
- Preassembled best practices, all Amex Way mandated features (Obseverbility, Reinforced Security, AvailabilityScalabilityResilency+Performance) from day one (more than just a set of rules)
- Fully equipped development environment, promoting productivity (dependencies management, code formatting, unit testing with TestContainers, integration testing, CICD process)
- Best balance between feature rich and troubleshooting difficulty (now troubleshooting over a working Paved Road instead of working at basic framework level), and advantages of knowledge sharing (internal and external forums over shared solutions)

### A set of live, working, focused tutorials
for learning most important, latest, advanced features of tech stacks (Java, Spring Boot, Cassandra, Kafka etc.) and enterprise-grade solutions based on them
- Bring the skills of the team up to a new level quickly. (See working and feature rich code live)
- Better utilization of internal talents (focusing on quality implementation on critical technical problems, no wasting time on low level repetition)
- Promoting culture of quality over shortcuts

### Based on industry standards
battle-proven and enterprise-grade open-source offerings with minimal proprietary customization
- Free enhancements of rich new features in future (enterprise features from global top tier developers)
- Easy to do integration with (work out of box by being compliant to industry standards)
- Easy and low cost to migrate to new versions (done by open-source communities, not in house)
- Remove the burden of testing and maintaining dependencies. (done by open-source communities, not in house)
- Avoid potential pitfalls when adopting new technologies (battle-proven solutions from open source communities)

### Open and non-intrusive with minimal restrictions
- Focus the quality implementation (based on standards with minimal customization) and remove majority of repeat effort. (No WET (write everything twice or more))
- No golden hammer, one size fits all solution is imposed

## Need for Spring Boot based Paved Road (SpringPR)
---

Amex Way - JVM Paved Road is not ready:
- Implementation is based on Kotlin. Kotlin is a promising new language, however its adoption, maturity, and community support have raised concerns
- It is an internal proprietary solution
- It lacks of high quality, rich set of enterprise grade features

Spring is the world’s most popular Java framework. It is an eco-system that is composed of huge numbers of enterprise-grade, production-ready, battle-proven frameworks, and libraries.

- Spring started as an open source initiated solution that has defeated J2EE technology that was a set of Java enterprise industry standards backed by almost all the top corporations around the world because of its technical superiorities and significantly better developer productivity
- Spring eco-system provides non-comparable high quality, rich sets of features and integration solutions over other competing eco-systems. It covers all enterprise computing domains
- Spring eco-system has been consistently putting forward the fastest and most comprehensive adoption of almost all the major new enterprise technologies since its inception

## SpringPR is Non-invasive
---

- SpringPR will keep its openness and avoid any non-necessary restrictions to adopting other technical stacks by following non-invasive programming style vigorously.
    - Spring framework is non-invasive, it doesn't force a programmer to extend or implement their class from any predefined class or interface given by Spring API.
- SpringPR facilitates easy integration with other technology stacks.
    - Spring boot by far is the most integration friendly eco-system that has almost all important technology stacks been integrated within
    - Spring boot provides mature enterprise grade integrations, reduces the risk of falling into pitfalls while adopting new technologies
- SpringPR has fundamentals ready out-of-box, but open to all sorts of programming paradigms and styles
- SpringPR expedites better governance by greatly limiting code review scope

## Conclusion
---

- Spring Boot based Paved Road (SpringPR) may help our team to realize significant better speed of delivery than target goal set by Technology.
- SpringPR makes large set of non-functional enterprise features available from day one.
- SpringPR facilitates consistent high-quality deliveries.
- SpringPR may significantly elevate skills of engineers in a short time.
