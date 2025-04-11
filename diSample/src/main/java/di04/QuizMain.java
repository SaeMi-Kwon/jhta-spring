package di04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *                                              Board - 인터페이스
 *                                                |
 *    BoardController <----(생성자로 주입)     BoardImplDao - 글등록/삭제/수정/조회
 *
 *    컨트롤러를 메인메소드에서 사용해 보세요.
 */
public class QuizMain {
    public static void main(String[] args) {
        ApplicationContext context= new ClassPathXmlApplicationContext("di01/di04.xml");
        BoardController controller =(BoardController) context.getBean("controller");
        controller.service();
    }
}
