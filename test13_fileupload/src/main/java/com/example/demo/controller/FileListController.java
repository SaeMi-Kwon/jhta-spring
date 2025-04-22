package com.example.demo.controller;

import com.example.demo.service.FileinfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class FileListController {

    private final FileinfoService fileinfoService;

    @GetMapping("/file/list")
    public void list(Model model){  //리턴타입이 void이면 요청경로가 이동할 뷰이름이 된다
        model.addAttribute("list",fileinfoService.list());
    }
}
