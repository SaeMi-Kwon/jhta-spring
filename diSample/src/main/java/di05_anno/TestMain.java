package di05_anno;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext context= new ClassPathXmlApplicationContext("di01/di05.xml");
        //name(value)를 지정하지않으면 자동으로 class이름으로(앞글자는 소문자) 지정된다
        //BoardController controller= (BoardController) context.getBean("boardController");

        //@Controller(value="controller") name 지정함
        BoardController controller= (BoardController) context.getBean("controller");
        controller.service();
    }
}
