package com.example.demo.repository;

import com.example.demo.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    // 멤버객체(new) -> | 영속성 컨텍스트(entity객체) | --(동기화)- > DB
    @PersistenceContext
    private EntityManager em;  //영속성 컨텍스트를 관리해주는 메소드

    public void save(Member m){
        em.persist(m);
    }

    public Member find(String id){
        Member m=em.find(Member.class,id);
        return m;
    }

    public Member update(Member m){
        Member mm=em.find(Member.class,m.getId());
        mm.setAge(m.getAge());
        mm.setPassword(m.getPassword());
        mm.setEmail(m.getEmail());
        return mm;
    }

    public void delete(String id){
        Member delmm=em.find(Member.class,id);
        em.remove(delmm);
    }

    /*
        JPQL 사용하기
        - 대소문자 구분 : 엔티티와 속성은 대소문자를 구분한다.
            SELECT, FROM, WHERE 같은 JPQL 키워드는 대소문자를 구분하지 않아도 된다.
        - 엔티티 이름 : JPQL에서 사용한 Member는 클래스 명이 아니라 엔티티 명이다.
            엔티티명은 @Entity(name="abc")로 지정할 수 있다. 엔티티 명을 지정하지 않으면 클래스 명을 기본값으로 사용한다
        - 별칭은 필수로 해야한다.
     */

    public Long count1(){
        Query query = em.createQuery("select count(m) from Member m");
        Long count=(Long) query.getSingleResult();   //반환타입이 Object이라서 형변환함
        return count;
    }

    public Long count2(){
        //native쿼리 실행하기
        Query query = em.createNativeQuery("select count(*) from members");
        Long count=(Long) query.getSingleResult();
        return count;
    }

    public List<Member> select(){
        Query query = em.createQuery("select m from Member m",Member.class); //반환타입이 Object이라서 형변환함
        List<Member> list = query.getResultList();
        return list;
    }
}

