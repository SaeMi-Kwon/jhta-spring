package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
@Transactional
@Commit
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void save(){
        User user = new User(null,"hello","1234","MEMBER");
        userService.save(user);
    }

    @Test
    public void isMember(){
        boolean result = userService.isMember("hello","1234");
        if(result){
            System.out.println("일치");
        }else {
            System.out.println("불일치");
        }
    }

}
