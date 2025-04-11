package di07_mybatis;

import members.dto.MembersDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("di01/mybatisApp.xml");
        MembersDao dao=(MembersDao) context.getBean("membersDao");
//        int n=dao.insert(new MembersDto(9,"구길동","010-212-5533","서울",null));
//        System.out.println(n + "명의 회원등록");

//        int n=dao.delete(90);
//        System.out.println(n + "명의 회원 삭제");

//        int n=dao.update(new MembersDto(2,"이길동","010-222-2222","평창",null));
//        System.out.println(n + "명의 회원 수정");

        MembersDto dto=dao.select(1);
        System.out.println(dto);
    }
}
