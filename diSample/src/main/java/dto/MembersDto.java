package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data //setter / getter / toString 메소드 오버라이딩
public class MembersDto {
    private int num;
    private String name;
    private String phone;
    private String addr;
    private Date regdate;
}
