package di07_mybatis;

import members.dto.MembersDto;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

//스프링에서는 SqlSession의 트랜잭션 처리,
//라이프사이클(openSession,close,commit)을 스프링이 관리한다.
public class MembersDao {
    private SqlSession sqlSession;
    private final String NAMESPACE="mapper.MembersMapper";

    //스프링이 생성해준 객체를 주입받아야 하므로 setter 또는 생성자(둘중 하나)가 필요한다
    public void setSqlSession(SqlSession sqlSession){
        this.sqlSession=sqlSession;
    }

    public int insert(MembersDto dto){
        int n=sqlSession.insert(NAMESPACE+ ".insert",dto);
        return n;
    }

    public int delete(int num){
        int n=sqlSession.delete(NAMESPACE+".delete", num);
        return n;
    }

    public int update(MembersDto dto){
        int n=sqlSession.update(NAMESPACE+".update",dto);
        return n;
    }

    public MembersDto select(int num){
        return sqlSession.selectOne(NAMESPACE+".getinfo",num);
    }
}
