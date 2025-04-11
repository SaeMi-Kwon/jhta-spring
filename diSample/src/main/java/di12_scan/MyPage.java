package di12_scan;

import org.springframework.stereotype.Component;

@Component
public class MyPage {
    private String data;

    public void printInfo(String data){
        System.out.println(data);
    }
}
