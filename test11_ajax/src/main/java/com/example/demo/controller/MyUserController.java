package com.example.demo.controller;


import com.example.demo.dto.MyUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@Controller
@RestController  //@Controller + @ResponseBody 어노테이션을 쓴것과 동일하게 응답(ajax로 요청할때)
public class MyUserController {

//    @GetMapping("/users/{id}")  //http://localhost:8080/user/hello1
//    //@ResponseBody
//    public MyUser info(@PathVariable("id") String id){
//        if(id.equals("hello")){
//            return new MyUser("hello","1111","hello@");
//        }else{
//            return new MyUser("none","none","none");
//        }
//    }

    @GetMapping("/users/{id}")
    public ResponseEntity<MyUser> info(@PathVariable("id") String id){
        MyUser m=null;
        if(id.equals("hello")){
            m = new MyUser("hello","1111","hello@");
        }else{
            m = new MyUser("none","none","none");
        }
        //new ResponseEntity<>(body, statusCode) --> ResponseEntity<>(응답객체,상태코드)
        ResponseEntity<MyUser> responseEntity = new ResponseEntity<>(m, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/users")
    public ResponseEntity<List<MyUser>> getList(){
        MyUser m1=new MyUser("hello1","0000","hello1@tt");
        MyUser m2=new MyUser("hello2","1111","hello2@tt");
        MyUser m3=new MyUser("hello3","2222","hello3@tt");
        List<MyUser> list = new ArrayList<>();
        list.add(m1);
        list.add(m2);
        list.add(m3);
        ResponseEntity<List<MyUser>> resp=new ResponseEntity<List<MyUser>>(list,HttpStatus.OK);
        return resp;
    }

}
