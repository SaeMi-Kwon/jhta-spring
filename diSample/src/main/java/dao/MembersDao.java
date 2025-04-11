package dao;

import dto.MembersDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MembersDao {

    @Autowired
    private SqlSession sqlSession;
    private final String NAMESPACE="mapper.MembersMapper";

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
