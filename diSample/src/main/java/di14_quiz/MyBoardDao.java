package di14_quiz;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyBoardDao {

    @Autowired
    private SqlSession sqlSession;
    private final String NAMESPACE="mapper.MyBoardMapper";

    public int insert(MyBoardDTO dto){
        int n=sqlSession.insert(NAMESPACE+".insert",dto);
        return n;
    }

    public int update(MyBoardDTO dto){
        int n=sqlSession.update(NAMESPACE+".update",dto);
        return n;
    }

    public int delete(int num){
        int n=sqlSession.delete(NAMESPACE+".delete",num);
        return n;
    }

    public MyBoardDTO getInfo(int num){
        return sqlSession.selectOne(NAMESPACE+".getInfo",num);
    }

    public List<MyBoardDTO> selectAll(){
        return sqlSession.selectList(NAMESPACE+".selectAll");
    }

}
