package com.example.demo.controller;

import com.example.demo.dto.FileinfoDto;
import com.example.demo.service.FileinfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

@Controller
@RequiredArgsConstructor
public class FileDelController {

    private final FileinfoService fileinfoService;
    
    @Value("${file.path}")
    private String filepath;

    @GetMapping("/file/del")
    public String delete(@RequestParam("filenum")long filenum){
        //조회
        FileinfoDto dto = fileinfoService.select(filenum);
        String savefile = dto.getSavefilename();
        
        //파일삭제
        File f= new File(filepath + File.separator + savefile);
        if(f.exists()) f.delete();
        
        //DB삭제
        fileinfoService.delete(filenum);
        
        return "redirect:/file/list";
    }
}
