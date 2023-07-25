# Backend
  
## Language - Java 1.8

## Spring Boot - ver (2.7.13)

- Boot 3.0.0 이상부터는 Java 17이 필수적이기에 2.7.13 버전으로 개발하였다.

## IDE - IntelliJ

## DataBase - PostgreSQL

## SQL Mapper - Mybatis

## project - Gradle (groovy)

- 빌드 관리 도구를 Gradle로 선택한 이유
  
  ```txt
  1. 이전에는 Gradle보다 Maven이 지원하는 Scope를 지원하지 않았고 성능면에서
     큰 차이가 없었지만 현재는 지원부분과 성능부분 모두 좋아지게 되었다.
     (이제는 Maven을 완전 지원한다고 봐도 무방하다.)
  2. Maven으로 dependency를 적용하면 익숙하지만 가독성 부분의 이점에서 Gradle이
     좋다.
  3. 빌드 속도가 Gradle이 성능 상으로 Maven에 비해 10배 이상 빠르다.
  4. Gradle은 캐시를 사용하므로 테스트 반복 시 실행 결과 시간의 차이가 더 커진다.
  ```

## Version Control Revision Control - Git, GitHub


