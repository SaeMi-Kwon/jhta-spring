package di09_mybatis;

import di07_mybatis.MembersDao;
import di08_quiz.MyusersDao;
import members.dto.MembersDto;
import myusers.dto.MyusersDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        //Members
//        ApplicationContext context = new AnnotationConfigApplicationContext(MybatisConfig.class);
//        MembersDao dao = (MembersDao) context.getBean("membersDao");
//
//        int n= dao.insert(new MembersDto(100,"아리랑","010-554-6622","강남",null));
//        System.out.println(n + "명의 등록");


        //Myusers
        ApplicationContext context1 = new AnnotationConfigApplicationContext(MyusersConfig.class);
        MyusersDao myusersDao= (MyusersDao) context1.getBean("myusersDao");

        int n1=myusersDao.insert(new MyusersDTO("oooi","9090","ooi@test.com",null));
        System.out.println(n1 + "명의 등록");
    }
}
