package di14_quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyBoardDTO {
    private int num;
    private String writer;
    private String title;
    private String content;
    private Date regdate;
}
