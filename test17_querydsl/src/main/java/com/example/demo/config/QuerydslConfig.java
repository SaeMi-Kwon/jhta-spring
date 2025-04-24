package com.example.demo.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuerydslConfig {

    //영속성컨테이너
    @PersistenceContext
    private EntityManager entityManager;  //JDBC할때 사용한적있음

    @Bean
    public JPAQueryFactory queryFactory(){
        return new JPAQueryFactory(entityManager);
    }

}
