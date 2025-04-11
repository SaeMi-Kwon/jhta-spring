package di14_quiz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(MybatisConfig.class);

        MyBoardController controller= (MyBoardController)context.getBean("boardController");

        controller.insert();
        controller.update();
        controller.delete();
        controller.getInfo();
        controller.selectAll();

    }
}
