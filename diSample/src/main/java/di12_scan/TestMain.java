package di12_scan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(MybatisConfig.class);

        MembersController controller = (MembersController) context.getBean("membersController");
        controller.service();

        MyPage mp=(MyPage) context.getBean("myPage");
        mp.printInfo("테스트...");
    }
}
