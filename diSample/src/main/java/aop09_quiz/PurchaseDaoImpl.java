package aop09_quiz;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseDaoImpl implements PurchaseDao{

    @Autowired
    private SqlSession sqlSession;
    private final String NAMESPACE="mapper.PurchaseMapper";

    @Override
    public int insert(PurchaseDTO dto) {
        return sqlSession.insert(NAMESPACE+".insert",dto);
    }

    @Override
    public int update(PurchaseDTO dto) {
        return sqlSession.update(NAMESPACE+".update",dto);
    }

    @Override
    public int delete() {
        return sqlSession.delete(NAMESPACE+".delete");
    }

    @Override
    public PurchaseDTO select(int num) {
        return sqlSession.selectOne(NAMESPACE+".select",num);
    }

    @Override
    public List<PurchaseDTO> selectAll() {
        return sqlSession.selectList(NAMESPACE+".selectAll");
    }
}
