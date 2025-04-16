package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
    Auditing설정 : 자동으로 등록날짜 / 수정날짜가 적용된다.
    - 스프링부트 실행 클래스에 @EnableJpaAuditing 설정
    - 날짜정보를 갖는 엔티티클래스에 @EntityListeners(AuditingEntityListener.class) 설정
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "myuser")
@EntityListeners(AuditingEntityListener.class)
public class MyUser {  //카멜표기법
    @Id
    private int num;
    private String name;
    private String phone;
    private String addr;

    //등록일
    @CreatedDate
    private LocalDateTime createdDate;

    //수정일
    @LastModifiedDate
    private LocalDateTime updatedDate;
}
