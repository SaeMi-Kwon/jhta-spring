package di03;

import di01.MembersController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("di01/di03.xml");
        BoardController controller= (BoardController)context.getBean("controller");
        controller.service();

    }
}
