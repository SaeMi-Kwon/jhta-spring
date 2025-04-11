package aop08;

import org.springframework.stereotype.Repository;

@Repository(value = "daoImpl")
public class MembersDaoImpl implements MembersDao {

    @Override
    public int insert(Object data) {
        //aop03.TimeAdvice 시간 늘리기위한 테스트
        for(long i=1;i<10000000000L;i++){

        }
        System.out.println(data + "추가");
        return 1;
    }

    @Override
    public int update(Object data) {
        for(long i=1;i<8000000000L;i++){

        }
        System.out.println(data + "수정");
        return 1;
    }

    @Override
    public int delete(Object data) {
        for(long i=1;i<30000000000L;i++){

        }
        System.out.println(data + "삭제");
        return 1;
    }

    @Override
    public Object select(Object data) {
        for(long i=1;i<50000000000L;i++){

        }
        System.out.println(data + "조회");
        return data;
    }

}
