package com.example.demo.controller;

import com.example.demo.dto.ImgBoardDto;
import com.example.demo.service.ImgBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

@Controller
@RequiredArgsConstructor
public class ImgBoardDelController {

    private final ImgBoardService service;

    @Value("${file.path}")
    private String path;

    @GetMapping("/imgboard/del")
    public String delete(@RequestParam("fnum")long fnum){
        //조회
        ImgBoardDto dto = service.select(fnum);
        //파일삭제
        String savefile = dto.getSavefilename();
        File f = new File(path + File.separator + savefile);
        if(f.exists()) f.delete();

        //DB삭제
        service.delete(fnum);

        return "redirect:/imgboard/list";
    }
}
