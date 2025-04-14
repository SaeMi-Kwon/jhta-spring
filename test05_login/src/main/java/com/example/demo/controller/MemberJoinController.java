package com.example.demo.controller;

import ch.qos.logback.core.util.StringUtil;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MemberJoinController {

    @Autowired
    private MemberService service;

    @GetMapping("/member/join")
    public String insertForm(@ModelAttribute MemberDTO memberDTO){
        return "member/insertForm";
    }

    @PostMapping("/member/join")
    //BindingResult를 사용하려면 @ModelAttribute MemberDTO가 있어야한다.
    public String insertOK(@ModelAttribute MemberDTO memberDTO, BindingResult bindingResult, Model model){
        if(!StringUtils.hasText(memberDTO.getId())){  //id 텍스트가 비어있으면
            //bindingResult에 에러담기 new FieldError("객체명","필드명","에러메시지");
            bindingResult.addError(new FieldError("memberDTO","id","아이디를 입력하세요"));
        }

        if(!StringUtils.hasText(memberDTO.getPwd())){  //pwd 텍스트가 비어있으면
            //bindingResult에 에러담기 new FieldError("객체명","필드명","에러메시지");
            bindingResult.addError(new FieldError("memberDTO","pwd","비밀번호를 입력하세요"));
        }
        
        //이메일에 @가 포함되어 있는지 검사하기
        String email=memberDTO.getEmail();
        if(email.indexOf("@") == -1){  //email.contains("@") :true/false 반환
            bindingResult.addError(new FieldError("memberDTO","email","이메일 형식이 아닙니다."));
        }
        
        //나이가 0에서 150사이인지 검사하기
        if(memberDTO.getAge() > 150 || memberDTO.getAge() < 0){
            bindingResult.addError(new FieldError("memberDTO","age","나이를 잘못입력했습니다."));
        }

        if(bindingResult.hasErrors()){  //에러가 존재하면 가입페이지로 이동하기
            return "member/insertForm";
        }

        try {
            int n = service.insert(memberDTO);
            log.warn("insert n값==>{}", n);
            model.addAttribute("result","등록성공!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("result","등록실패!");
        }

        return "member/result";
    }
}
