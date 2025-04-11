package di14_quiz;

import lombok.AllArgsConstructor;
import members.dto.MembersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller(value = "boardController")
public class MyBoardController {

    @Autowired
    private MyBoardService service;

    public void insert(){
        int n=service.insert(new MyBoardDTO(300,"글쓴이","spring","mybatis사용",null));
        System.out.println( n + "개의 글 등록");
    }

    public void update(){
        int n=service.update(new MyBoardDTO(201,"testUpdate","spring수정","mybatis수정",null));
        System.out.println( n + "개의 글 수정");
    }

    public void delete(){
        int n=service.delete(150);
        System.out.println( n + "개의 글 삭제");
    }

    public void getInfo(){
        MyBoardDTO dto =service.getInfo(160);
        System.out.println("조회: " + dto);
    }

    public void selectAll(){
        List<MyBoardDTO> dto =service.selectAll();
        System.out.println(dto);
    }

}
