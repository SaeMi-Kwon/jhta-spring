package di08_quiz;

import myusers.dto.MyusersDTO;
import org.apache.ibatis.session.SqlSession;

public class MyusersDao {
    private SqlSession sqlSession;
    private final String NAMESPACE="mapper.MyusersMapper";

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession=sqlSession;
    }

    public int insert(MyusersDTO dto){
        int n=sqlSession.insert(NAMESPACE+".insert",dto);
        return n;
    }

    public int update(MyusersDTO dto){
        int n=sqlSession.update(NAMESPACE+".update",dto);
        return n;
    }

    public int delete(String id){
        int n=sqlSession.delete(NAMESPACE+".delete",id);
        return n;
    }

    public MyusersDTO select(String id){
        return sqlSession.selectOne(NAMESPACE+".select",id);
    }
}
