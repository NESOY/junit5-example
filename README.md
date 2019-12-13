## JUnit5 Tutorial

## Requirements
- Java8

## [Architecture](https://github.com/junit-team/junit5-workshop/blob/master/slides/05_modularization/modularization.md)
- Platform
    - 테스트를 실행해주는 런처 제공. TestEngine API 제공.
    - Maven / Gradle Plugins, JUnit4 Runner
- Jupiter
    - JUnit 5를 지원하는 TestEngine API 구현체
- Vintage 
    - JUnit 4와 3을 지원하는 TestEngine API 구현체

## Support JUnit 5 in SpringBoot
- [SpringBoot 2.2에서는 JUnit 5 지원](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.2-Release-Notes#junit-5)

## [Index](https://github.com/NESOY/junit5-example/tree/master/src/test/java)
- [Standard](https://github.com/NESOY/junit5-example/tree/master/src/test/java/standard)
- [Assertions](https://github.com/NESOY/junit5-example/tree/master/src/test/java/assertions)
- [assumptions](https://github.com/NESOY/junit5-example/tree/master/src/test/java/assumptions)
- [meta](https://github.com/NESOY/junit5-example/tree/master/src/test/java/meta)
- [disabled](https://github.com/NESOY/junit5-example/tree/master/src/test/java/disabled)
- [displayname](https://github.com/NESOY/junit5-example/tree/master/src/test/java/displayname)
- [timeout](https://github.com/NESOY/junit5-example/tree/master/src/test/java/timeout)
- [order](https://github.com/NESOY/junit5-example/tree/master/src/test/java/order)
- [repeat](https://github.com/NESOY/junit5-example/tree/master/src/test/java/repeat)
- [test_info](https://github.com/NESOY/junit5-example/tree/master/src/test/java/test_info)
- [test_interface](https://github.com/NESOY/junit5-example/tree/master/src/test/java/test_interface)
- [parameterized](https://github.com/NESOY/junit5-example/tree/master/src/test/java/parameterized)
- [conditional](https://github.com/NESOY/junit5-example/tree/master/src/test/java/conditional)
- [extension_model](https://github.com/NESOY/junit5-example/tree/master/src/test/java/extension_model)
- [dynamic_test](https://github.com/NESOY/junit5-example/tree/master/src/test/java/dynamic_test)
- [parallel](https://github.com/NESOY/junit5-example/tree/master/src/test/java/parallel)

## 추가해야할 점
- dynamic Test
- extention Model
- TestTemplate
- Parrallel
- 4 / 5 동시에 돌릴 수 있을까?
- JUnit4 -> 5 Migration Guide
- Junit4 Naming Test

## 궁금
- Converter
- ArgumentsAggregator
- ArgumentsProvider
- 차이점.

### Reference
- <https://junit.org/junit5/docs/current/user-guide/>
- <https://brunch.co.kr/@springboot/77>
- [assertThat Dependency 없음(Hamcrest, AssertJ)](https://github.com/junit-team/junit5/issues/147)