package com.example.demo.dto;

import com.example.demo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/*
    참고(설명)
    Lombok이 @Builder를 사용해서 다음과 같은 static 메서드를 자동 생성
    public static MemberBuilder builder() {
        return new MemberBuilder();
    }

    builder()는 static 메서드로 만들어지는 거라서, 클래스 이름으로 직접 호출해야 해.
    Member m = Member.builder()
                .id(this.id)  //this생략가능
                .pwd(pwd)
                .email(email)
                .age(age)
                .regdate(regdate)
                .build();
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberDto {
    private String id;
    private String pwd;
    private String email;
    private int age;
    private Date regdate;

    //생성자(entity를 파라미터로 넘기면 값들이 dto에도 적용)
    public MemberDto(Member mem){
        this.id=mem.getId();
        this.pwd=mem.getPwd();
        this.email=mem.getEmail();
        this.age=mem.getAge();
        this.regdate=mem.getRegdate();
        
    }
    
    //MemberDto --> Member(entity)로 변환시킬때 메소드
    public Member toEntity(){
        //Member m = new Member(id,pwd,email,age,regdate);

        Member m = Member.builder()
                .id(this.id)  //this생략가능
                .pwd(pwd)
                .email(email)
                .age(age)
                .regdate(regdate)
                .build();
        return m;
    }


}
