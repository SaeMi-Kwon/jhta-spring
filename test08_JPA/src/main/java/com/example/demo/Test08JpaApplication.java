package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
    Auditing설정 : 자동으로 등록날짜 / 수정날짜가 적용된다.
    - 스프링부트 실행 클래스에 @EnableJpaAuditing 설정
    - 날짜정보를 갖는 엔티티클래스에 @EntityListeners(AuditingEntityListener.class) 설정
 */

@SpringBootApplication
@EnableJpaAuditing
public class Test08JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Test08JpaApplication.class, args);
    }

}
