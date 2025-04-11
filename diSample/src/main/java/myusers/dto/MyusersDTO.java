package myusers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyusersDTO {
    private String id;
    private String pwd;
    private String email;
    private Date regdate;
}
