package di13_scan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        //component scan를 사용해서 스프링 빈을 생성하고 JdbcController를 사용해 보세요.
        ApplicationContext context= new AnnotationConfigApplicationContext(JdbcConfig.class);

        //JdbcController controller = (JdbcController) context.getBean("jdbcController");
        JdbcController controller = (JdbcController) context.getBean("controller");
        controller.service();

    }
}
