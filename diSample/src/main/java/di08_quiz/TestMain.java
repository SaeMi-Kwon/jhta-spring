package di08_quiz;

import myusers.dto.MyusersDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        //myusers테이블과 연동해서 CRUD해보기(mybatis사용)
        ApplicationContext context=new ClassPathXmlApplicationContext("di01/mybatisQuiz.xml");
        MyusersDao dao=(MyusersDao) context.getBean("myusersDao");

//        int n=dao.insert(new MyusersDTO("aeee","1112","aeee@test.com",null));
//        System.out.println(n + "명의 회원 등록");

//        int n=dao.delete("rrr");
//        System.out.println(n + "명의 회원 삭제");

//        int n=dao.update(new MyusersDTO("ddddd","12345","ddddd@test.com",null));
//        System.out.println(n + "명의 회원 수정");

        MyusersDTO dto=dao.select("qqq");
        System.out.println(dto);
    }
}
