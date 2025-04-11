package di13_scan;

import members.dto.MembersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@Controller
@Controller(value="controller")  //이름부여
public class JdbcController {

    @Autowired
    private JdbcService service;

    public void service(){
        MembersDto dto=service.select(1);
        System.out.println(dto);
    }
}
