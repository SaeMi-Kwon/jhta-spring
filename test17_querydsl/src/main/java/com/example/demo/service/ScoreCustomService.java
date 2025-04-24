package com.example.demo.service;

import com.example.demo.model.QScore;
import com.example.demo.model.QStudent;
import com.example.demo.model.Student;
import com.example.demo.model.StudentScoreResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScoreCustomService {

    private final JPAQueryFactory queryFactory;
    private final QStudent st = QStudent.student;
    private final QScore sc = QScore.score1;

    public Student getInfo1(Long studentId){
        //select * from student where studentId = 1;
        Student s = queryFactory.selectFrom(st)
                        .where(st.studentId.eq(studentId))
                        //fetch() : 결과 데이터가 여러개일경우 사용
                        .fetchOne(); //결과 데이터가 하나일 경우
        return s;
    }

    public List<Student> getInfo2(String name){
        return queryFactory
                    .selectFrom(st)
                    .where(st.name.eq(name))
                    .fetch();
    }

    public List<Student> getInfo3(int age){
        return queryFactory
                    .selectFrom(st)
                    .where(st.age.gt(age))  //보다 크다
                    .fetch();
    }

    //이름으로 조회하거나 아이디로 조회하거나 동적으로 쿼리
    public List<Student> getInfo4(String name,Long studentId){
        BooleanBuilder predicate = new BooleanBuilder();
        if (name!=null){
            predicate.or(st.name.eq(name));
        }
        if(studentId!=null){
            predicate.or(st.studentId.eq(studentId));
        }
        return queryFactory.selectFrom(st).where(predicate).fetch();
    }


    public List<StudentScoreResponse> getJoin1(){
        //Tuple : map과 비슷한 형식 | Tuple 객체는 여러 값을 담을 수 있다
        List<Tuple> list = queryFactory.select(st.studentId,st.name,st.age,sc.scoreId,sc.subject,sc.score)
                    .from(st)
                    .join(sc)
                    .on(st.studentId.eq(sc.studentId))
                    .fetch();

        // fetch메소드는 조인한 결과를 List<Tuple>타입으로 반환한다 -> map()을 이용해서 원하는 DTO타입으로 변경한다.
        List<StudentScoreResponse> result = list.stream().map(t-> {
            return StudentScoreResponse.builder()
                    .studentId(t.get(st.studentId))
                    .name(t.get(st.name))
                    .age(t.get(st.age))
                    .scoreId(t.get(sc.scoreId))
                    .subject(t.get(sc.subject))
                    .score(t.get(sc.score))
                    .build();
        }).toList();

        return result;
    }


    public List<StudentScoreResponse> getJoin2(){
        //Projections : 쿼리 결과를 원하는 객체나 값으로 변화해 주는 기능을 하는 클래스
        List<StudentScoreResponse> list =
                queryFactory.select(Projections.bean(
                        StudentScoreResponse.class,
                                st.studentId,
                                st.name,
                                st.age,
                                sc.scoreId,
                                sc.subject,
                                sc.score
                        ))
                        .from(st)
                        .leftJoin(sc)
                        .on(st.studentId.eq(sc.studentId))
                        .orderBy(sc.score.desc())
                        .fetch();

        return list;
    }


    public List<StudentScoreResponse> getJoin3(String name, Long studentId) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (name != null) {
            predicate.or(st.name.eq(name));
        }

        if (studentId != null) {
            predicate.or(st.studentId.eq(studentId));
        }

        List<StudentScoreResponse> list = queryFactory
                .select(Projections.bean(
                        StudentScoreResponse.class,
                        st.studentId,
                        st.name,
                        st.age,
                        sc.scoreId,
                        sc.subject,
                        sc.score
                ))
                .from(st)
                .leftJoin(sc).on(st.studentId.eq(sc.studentId))
                .where(predicate) // <- 여기 조건 추가됨!
                .orderBy(sc.score.desc())
                .fetch();

        return list;
    }

}
