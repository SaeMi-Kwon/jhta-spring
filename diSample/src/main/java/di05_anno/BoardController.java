package di05_anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/*
    @Controller : 컨트롤러 객체 생성
    @Service : 서비스객체 생성 (트랜잭션 기능)
    @Repository : DAO 생성
    @Component : 위에 기능에 해당하지 않는 클래스
 */
@Controller(value="controller")  //스프링이 자동생성해줌
public class BoardController {

    @Autowired
    private CommonDao dao;

    public BoardController(){}

    public void service(){
        dao.insert("스프링di이해1");
        dao.update("스프링di이해2");
        dao.delete("스프링di이해3");
        dao.select("스프링di이해4");
    }

}
