package aop09_quiz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


/*
    1.
    << 구매테이블 >>
    구매번호(PK),구매자아이디,상품명,가격,수량,구매일
    << 결제테이블 >>
    결제번호(PK) 구매번호(FK) 결제금액 결제수단
    위의 정보를 갖는 테이블을 만들고 구매기능을 구현해 보세요.

    [실행결과]
    1. 제품구매  2.구매정보수정 3.구매정보조회  4.구매취소
    ..

    제품구매:구매자아이디,상품명,가격,수량,결제수단 입력 받아서 저장
    구매정보조회:전체구매정보 조회
    구매정보수정:상품명,가격,수량,결제금액,결제수단을 변경합니다.
    구매취소:구매정보와 결제정보가 모두 삭제됩니다.

    트랜잭션 처리가 가능하도록 만들어 보세요
 */
public class QuizMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(QuizConfig.class);
        QuizService service = (QuizService) context.getBean("service");

        while (true) {
            System.out.println("1. 제품구매  2. 구매정보수정  3. 구매정보조회  4. 구매취소  5. 종료");
            System.out.print("번호선택: ");
            Scanner scan = new Scanner(System.in);
            int choice=scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("구매자아이디를 입력하세요: ");
                    String id=scan.next();
                    System.out.println("상품명을 입력하세요: ");
                    String item=scan.next();
                    System.out.println("가격을 입력하세요: ");
                    int price=scan.nextInt();
                    System.out.println("수량을 입력하세요: ");
                    int amount=scan.nextInt();
                    System.out.println("결제수단을 입력하세요(카드, 현금..): ");
                    String pmethod=scan.next();

                    service.insert(new PurchaseDTO(0, id, item, price, amount, null), pmethod);
                    break;

                case 2:
                    System.out.println("수정할 구매번호를 입력하세요: ");
                    int upNum=scan.nextInt();
                    System.out.println("상품명을 변경하세요: ");
                    String upItem=scan.next();
                    System.out.println("가격을 변경하세요: ");
                    int upPrice=scan.nextInt();
                    System.out.println("수량을 변경하세요: ");
                    int upAmount=scan.nextInt();
                    System.out.println("결제수단을 변경하세요(카드, 현금..): ");
                    String upPmethod=scan.next();
                    service.update(new PurchaseDTO(upNum,null,upItem,upPrice,upAmount,null),upPmethod);
                    break;

                case 3:
                    System.out.println("<< 전체 구매 이력 >>");
                    service.selectAll();
                    break;

                case 4:
                    service.delete();
                    System.out.println("전부 취소되었습니다");
                    break;

                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    return;

                default:
                    System.out.println("잘못된 입력하셨습니다.");
            }
        }
    }

}
