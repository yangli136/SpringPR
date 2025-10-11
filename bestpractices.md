> Bestpractices FILE

---

# Best Practices
---

## Dependency Management

1. if a new non-functional feature/functional is required and it is not available
in existing SpringPR components, Please check the pom.xml in spring-boot-dependencies
first, most likely you'll find a framework/library that provides the feature/function
needed and it is managed by spring-boot-dependencies

1. Use the version from spring-boot-dependencies if it is available

1. create an interface then wrap implementation over APIs of the new
framework/library unless an industry standard interface has already defined.
Business logic code should only depend on the interface

1. Review should be performed against new framework/library introduced

## Test

1. Coverage of Unit Tests must be over 70%, and over 90% for major services

1. Coverage of Integration Tests must be over 90% against dependent services

1. Coverage of Functional Tests must be over 90% against major application functionality

> **Steps to reach better coverage for Functional Tests**
> 1. Identify test data sets and formats
> 1. Identify required data customization, identify script or library to handle the customization
> 1. Identify mechanism to feed test data sets to application, considering framework such as JMeter, Selenium etc.
> 1. Health Checks of application integration. Validate results at high level): such as file created, data inserted into  tables etc.
> 1. Function Availability Tests. Test Data Set created to target specific function. Validate that the related result artifacts are created without checking details of the results.
> 1. Complete Functional Tests, enrich test data sets. Create application/script to validate result details.
> 1. Repeat the above steps to reach required coverage

1. Performance Tests for critical functionality if required
