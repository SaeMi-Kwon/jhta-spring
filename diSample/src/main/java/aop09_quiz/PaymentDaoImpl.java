package aop09_quiz;

import di14_quiz.MyBoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDaoImpl implements PaymentDao{

    @Autowired
    private SqlSession sqlSession;
    private final String NAMESPACE="mapper.PaymentMapper";

    @Override
    public int insert(PaymentDTO dto) {
        int n=sqlSession.insert(NAMESPACE+".insert",dto);
        return n;
    }

    @Override
    public int update(PaymentDTO dto) {
        int n=sqlSession.update(NAMESPACE+".update",dto);
        return n;
    }

    @Override
    public int delete() {
        return sqlSession.delete(NAMESPACE+".delete");
    }

    @Override
    public PaymentDTO select(int pnum) {
        return sqlSession.selectOne(NAMESPACE+".select",pnum);
    }

    @Override
    public List<PaymentDTO> selectAll() {
        return sqlSession.selectList(NAMESPACE+".selectAll");
    }

}
