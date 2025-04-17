package com.example.demo.repository;

import com.example.demo.entity.Member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,String> {

    //limit : 몇개 추출 , offset : 몇페이지에서부터
    //JPQL쿼리에서는 limit :a,:b (사용불가) --> 콤마를 사용할수 없다
    @Query("select m from Member m order by m.id limit :b offset :a")
    List<Member> list1(@Param("a")int a,@Param("b")int b); //a=0, b=10 -->1페이지에서 10개 추출


    @Query(value = "select * from member order by id limit :a,:b",nativeQuery = true)
    List<Member> list2(@Param("a")int a,@Param("b")int b);

    //페이징 처리
    //public Page<Member> findAll(Pageable pageable);

    @Modifying  //DML작업할때는 반드시 어노테이션을 설정해야 함
    @Query("update Member m set m.age=m.age+10 where m.id=:id")
    public int update(@Param("id")String id);
}
