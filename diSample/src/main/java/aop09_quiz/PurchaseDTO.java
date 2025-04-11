package aop09_quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaseDTO {
    private int num;
    private String id;
    private String item;
    private int price;
    private int amount;
    private Date buydate;

}
