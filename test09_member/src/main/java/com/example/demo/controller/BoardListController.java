package com.example.demo.controller;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.PageResultDto;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardListController {

    private final BoardService service;

    //방법1
    @GetMapping("/list")
    public String list(@PageableDefault(size=3,sort = "num",direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        PageResultDto pageResultDto = service.list(pageable);
        model.addAttribute("list", pageResultDto.getList());
        model.addAttribute("startPage", pageResultDto.getStartPage());
        model.addAttribute("endPage", pageResultDto.getEndPage());
        model.addAttribute("pageCount", pageResultDto.getTotalPageCount());
        model.addAttribute("page", pageResultDto.getPage());  //현재 페이지번호

        return "board/list";
    }

    //방법2
    @GetMapping("/list1")
    public String list1(@RequestParam(value = "page",defaultValue = "0")int page, Model model){
        PageRequest pageable=PageRequest.of(page,3, Sort.by("num").descending());
        PageResultDto pageResultDto = service.list(pageable);

        model.addAttribute("list", pageResultDto.getList());
        model.addAttribute("startPage", pageResultDto.getStartPage());
        model.addAttribute("endPage", pageResultDto.getEndPage());
        model.addAttribute("pageCount", pageResultDto.getTotalPageCount());
        model.addAttribute("page", pageResultDto.getPage());  //현재 페이지번호

        return "board/list";
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam("num")Long num, Model model){
        BoardDto dto = service.select(num);
        model.addAttribute("dto",dto);
        return "board/update";
    }

    @PostMapping("/update")
    public String update(BoardDto boardDto){
        BoardDto dto = service.update(boardDto);
        System.out.println("수정dto ==>" + dto);

        return "redirect:/board/list";
        //return "redirect:/board/list?page=0";  //defaultValue = "0" 설정안했을경우 사용
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("num")Long num, RedirectAttributes redirectAttributes){
        service.delete(num);
        redirectAttributes.addFlashAttribute("msg","삭제완료!");
        return "redirect:/board/list";
    }

}
