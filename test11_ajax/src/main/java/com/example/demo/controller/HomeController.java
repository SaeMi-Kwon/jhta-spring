package com.example.demo.controller;

import com.example.demo.dto.MyUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

//    @GetMapping("/")
//    @ResponseBody   //리턴된 응답한 값으로 응답하겠다
//    public String home(){
//        return "안녕하세요";
//    }

    @GetMapping("/member")
    public String member(){
        return "member/main";
    }

    @GetMapping("/user")
    @ResponseBody   //자바 객체를 응답(Response)의 Body(json)로 변환
    public MyUser user(){
        MyUser mu=new MyUser("hello","1234","hello@tt.com");
        return mu;
    }

}
