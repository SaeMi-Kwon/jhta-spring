package aop09_quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "service")
@Transactional
public class QuizService {

    @Autowired
    private PurchaseDao purchaseDao;
    @Autowired
    private PaymentDao paymentDao;

    public void insert(PurchaseDTO dto,String pmethod){
        //구매 테이블에 추가
        purchaseDao.insert(dto);
        //결제 테이블에 추가
        paymentDao.insert(new PaymentDTO(0, dto.getNum(), dto.getPrice()*dto.getAmount(), pmethod));
    }

    public void update(PurchaseDTO dto,String pmethod){
        //구매 테이블에 수정
        purchaseDao.update(dto);
        //구매 테이블에 수정
        paymentDao.update(new PaymentDTO(0, dto.getNum(), dto.getPrice()*dto.getAmount(), pmethod));
    }

    public void delete(){
        //결제 테이블에 삭제
        paymentDao.delete();
        //구매 테이블에 삭제
        purchaseDao.delete();
    }

    public void selectAll(){
        //전체 구매이력 조회
        purchaseDao.selectAll().forEach((p)->{
            System.out.println(p);
        });
    }

}
