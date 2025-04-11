package di11_quiz;

import di11_quiz.MyusersDao;
import myusers.dto.MyusersDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/*
    Spring JDBC사용해서 MyUsers테이블과 연동해서 CRUD기능 구현하기
 */
public class QuizMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyusersConfig.class);
        MyusersDao dao= (MyusersDao) context.getBean("myusersDao");

        //등록
        int n=dao.insert(new MyusersDTO("popo","3335","popo@test.com",null));
        System.out.println(n + "명의 회원등록");

        //수정
        int n1=dao.update(new MyusersDTO("aaaa","1000","a1000@test.com",null));
        System.out.println(n1 + "명의 회원수정");

        //삭제
        int n2=dao.delete("eeee");
        System.out.println(n2 + "명의 회원삭제");

        //조회(1건)
        MyusersDTO dto=dao.select("zxy");
        if(dto==null){
            System.out.println("조회한 아이디 정보가 없습니다.");
        }else {
            System.out.println(dto);
        }

        //조회(여러건)
        System.out.println("<< 전체 회원정보 >>");
        dao.selectAll().forEach(u->{
            System.out.println(u);
        });

    }
}
