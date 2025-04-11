package di06_javaconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(MyAppConfig.class);
        //name을 지정하지않으면 메소드명으로 지정된다
        Person pp=(Person) context.getBean("person");
        System.out.println(pp.getName()+ "," + pp.getAge());

        //dao로 설정하고 객체 얻어와서 사용해 보세요.
        MembersDao dao=(MembersDao) context.getBean("dao");
        dao.insert("테스트");

        MembersController controller=(MembersController) context.getBean("controller");
        controller.service();
    }
}
