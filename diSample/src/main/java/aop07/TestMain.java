package aop07;

import members.dto.MembersDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(JavaConfig.class);
        MembersDao dao = (MembersDao) context.getBean("dao");
        Object dto = dao.select("홍길동");
        System.out.println(dto);
    }
}
