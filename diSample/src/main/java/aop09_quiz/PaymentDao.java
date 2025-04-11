package aop09_quiz;

import java.util.List;

public interface PaymentDao {
    int insert(PaymentDTO paymentDTO);
    int update(PaymentDTO paymentDTO);
    int delete();
    PaymentDTO select(int pnum);
    List<PaymentDTO> selectAll();
}
