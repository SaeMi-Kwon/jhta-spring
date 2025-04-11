package di12_scan;

import members.dto.MembersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MembersController {

    @Autowired   //없으면 NullPointerException 예외발생하므로 빼지않도록 주의한다.
    private MembersService service;

    public void service(){
        //service.insert(new MembersDto(110,"일십","010-101-1010","종로",null));
        MembersDto dto = service.select(110);
        System.out.println(dto);
    }
}
