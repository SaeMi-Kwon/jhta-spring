package transaction;

import dto.MembersDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.MembersService;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(JavaConfig.class);
        MembersService service =(MembersService)context.getBean("membersService");
        service.insert(new MembersDto(3,"삼길동","010-333-3333","구로",null));

    }
}
