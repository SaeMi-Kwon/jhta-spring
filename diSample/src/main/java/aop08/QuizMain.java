package aop08;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class QuizMain {
    public static void main(String[] args) {
        //메소드의 수행시간을 출력하는 기능을 aop를 이용해서 구현해 보세요. -> 어노테이션 방식으로 설정하기
        ApplicationContext context=new AnnotationConfigApplicationContext(QuizConfig.class);
        MembersDao dao=(MembersDao)context.getBean("daoImpl");
        dao.insert("테스트1");
        dao.update("테스트2");
        dao.delete("테스트3");
        dao.select("테스트4");
    }
}
