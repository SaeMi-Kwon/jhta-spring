package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
public class MemberListController {

    @Autowired
    private MemberService service;

    @GetMapping("/member/list")
    public String list(@RequestParam(value="pageNum", defaultValue="1")int pageNum, Model model){
        int rowCount=5;  //한페이지당 보여줄 게시글 수
        int pageCount=3;  //한페이지에 보여줄 페이지 수
        int startRow=(pageNum-1)*rowCount;    //행 시작(0이 나오면 0초과 --> 1)
        int startPage=(pageNum-1)/pageCount*pageCount+1;  //시작 페이지
        int endPage=startPage+2;   //마지막 페이지

        int totalRowCount=service.getConunt();  //총 게시글 수
        int totalPageCount=(int)Math.ceil((double)totalRowCount/rowCount);   //전체 페이지
        if(endPage>totalPageCount) {
            endPage=totalPageCount;
        }

        System.out.println("시작행"+ startRow);

        HashMap<String,Integer> map=new HashMap<String,Integer>();
        map.put("startRow",startRow);
        map.put("rowCount",rowCount);
        List<MemberDTO> list =service.selectAll(map);

        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("totalPageCount",totalPageCount);
        model.addAttribute("list",list);
        model.addAttribute("pageNum",pageNum);
        return "member/list";
    }

}
