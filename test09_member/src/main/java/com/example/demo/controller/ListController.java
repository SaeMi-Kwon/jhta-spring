package com.example.demo.controller;

import com.example.demo.dto.MemberDto;
import com.example.demo.dto.PageResultDto;
import com.example.demo.dto.PageUtilDto;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/member")
public class ListController {

    @Autowired
    private MemberService service;

//    @GetMapping("/list")
//    public String list(Model model){
//        List<MemberDto> list= service.list();
//        model.addAttribute("list",list);
//        return "member/list";
//    }

    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "0") int page, Model model){
        PageRequest pageable=PageRequest.of(page,3, Sort.by("id").descending());
        PageUtilDto<MemberDto> pageUtilDto = service.list(pageable);

        model.addAttribute("list", pageUtilDto.getList());
        model.addAttribute("startPage", pageUtilDto.getStartPage());
        model.addAttribute("endPage", pageUtilDto.getEndPage());
        model.addAttribute("pageCount", pageUtilDto.getTotalPageCount());
        model.addAttribute("page", pageUtilDto.getPage());

        return "member/list";
    }

}
