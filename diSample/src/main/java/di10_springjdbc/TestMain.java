package di10_springjdbc;

import members.dto.MembersDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
        JdbcDao dao=(JdbcDao)context.getBean("jdbcDao");

        //추가
//        int n=dao.insert(new MembersDto(90,"구십동","010-909-9090","부산",null));
//        int n=dao.insert(new MembersDto(91,"구일동","010-990-9090","제주",null));
//        System.out.println(n + "명의 회원등록");

        //삭제
//        int n=dao.delete(100);
//        System.out.println(n + "명의 회원삭제");

        //수정하기(이름/전화번호/주소)
//        int n=dao.update(new MembersDto(8,"팔기동","010-888-8888","광주",null));
//        System.out.println(n + "명의 회원 수정");

        //조회(한건)
//        MembersDto dto=dao.select(20);
//        if(dto==null){
//            System.out.println("조회된 회원이 없습니다");
//        }else {
//            System.out.println("<< 조회된 회원 >>");
//            System.out.println(dto);
//        }

        //조회(여러건)
        System.out.println("<< 전체 회원 정보 >>");
        dao.selectAll().forEach(m->{
            System.out.println(m);
        });



    }
}
