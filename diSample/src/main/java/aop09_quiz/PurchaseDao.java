package aop09_quiz;

import java.util.List;

public interface PurchaseDao {
    int insert(PurchaseDTO purchaseDTO);
    int update(PurchaseDTO purchaseDTO);
    int delete();
    PurchaseDTO select(int num);
    List<PurchaseDTO> selectAll();

}
