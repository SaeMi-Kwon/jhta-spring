package com.example.demo.controller;

import com.example.demo.dto.Member;
import com.example.demo.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class ListController {

    @GetMapping("/list1")
    public String list1(Model model){
        List<String> list=new ArrayList<>();
        list.add("개나리");
        list.add("진달래");
        list.add("무궁화");
        model.addAttribute("flowers",list);

        List<User> users=new ArrayList<>();
        users.add(new User("hello","1234"));
        users.add(new User("test","0000"));
        users.add(new User("admin","1111"));
        model.addAttribute("users",users);

        List<Member> mem=new ArrayList<>();
        mem.add(new Member("aaaa","a123","aaaa@test.com",false));
        mem.add(new Member("bbbb","b222","bbbb@test.com",true));
        mem.add(new Member("cccc","c333","cccc@test.com",true));
        mem.add(new Member("ffff","f444","ffff@test.com",false));
        mem.add(new Member("qwer","0987","qwer@test.com",true));
        model.addAttribute("mem",mem);

        Map<String,Object> map=new HashMap<>();
        map.put("name","홍길동");
        map.put("email","hong@test.com");
        model.addAttribute("map",map);

        Map<String,User> map1=new HashMap<>();
        map1.put("user1",new User("test","1234"));
        map1.put("user2",new User("test1","2221"));
        map1.put("user3",new User("test2","3332"));
        model.addAttribute("map1",map1);

        return "showList";
    }




}
