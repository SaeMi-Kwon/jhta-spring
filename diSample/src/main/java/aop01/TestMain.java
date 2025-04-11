package aop01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext context= new ClassPathXmlApplicationContext("aop/aop01.xml");
        MembersDao dao=(MembersDao) context.getBean("membersDaoImpl");
        dao.insert("홍길동1");
        dao.update("홍길동2");
        dao.delete("홍길동3");
        dao.select("홍길동4");
    }
}
