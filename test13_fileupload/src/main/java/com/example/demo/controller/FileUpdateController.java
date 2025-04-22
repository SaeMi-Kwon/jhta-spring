package com.example.demo.controller;

import com.example.demo.dto.FileRequestDto;
import com.example.demo.dto.FileinfoDto;
import com.example.demo.service.FileinfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileUpdateController {

    private final FileinfoService fileinfoService;
    
    @Value("${file.path}")
    private String filepath;

    @GetMapping("/update")
    public void update(@RequestParam("filenum")long filenum, Model model){
        //조회
        model.addAttribute("dto",fileinfoService.select(filenum));
    }

    @PostMapping("/update")
    public String update(FileRequestDto requestDto) throws IOException {
        //파일정보 꺼내오기
        MultipartFile file1 = requestDto.getFile1();

        //DB조회
        long filenum = requestDto.getFilenum();
        FileinfoDto dto =  fileinfoService.select(filenum);

        //기존에 DB에 저장되어있는 파일정보
        String orgfile = dto.getOrgfilename();
        String savefile = dto.getSavefilename();
        long filesize = dto.getFilesize();

        if(!file1.isEmpty()){  //수정할 파일이 전송된 경우
            //기존파일삭제
            File delfile = new File(filepath + File.separator + savefile);
            if(delfile.exists()) delfile.delete();

            //파일업로드
            orgfile = file1.getOriginalFilename();
            savefile = UUID.randomUUID() + "_" + orgfile;
            filesize = file1.getSize();

            File file = new File(filepath + File.separator + savefile);
            file1.transferTo(file);

        }

        //dto에 수정된거 담기
        FileinfoDto fileinfoDto = FileinfoDto.builder()
                .filenum(filenum)
                .writer(requestDto.getWriter())
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .orgfilename(orgfile)
                .savefilename(savefile)
                .filesize(filesize)
                .build();

        //DB반영
        fileinfoService.update(fileinfoDto);

        return "redirect:/file/info?filenum=" + filenum;
    }
}
