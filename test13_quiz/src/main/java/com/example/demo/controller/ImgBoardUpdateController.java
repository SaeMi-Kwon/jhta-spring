package com.example.demo.controller;

import com.example.demo.dto.BoardRequestDto;
import com.example.demo.dto.ImgBoardDto;
import com.example.demo.service.ImgBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ImgBoardUpdateController {

    private final ImgBoardService service;

    @Value("${file.path}")
    private String path;

    @GetMapping("/imgboard/update")
    public void updateForm(@RequestParam("fnum")long fnum, Model model){
        model.addAttribute("dto",service.select(fnum));
    }

    @PostMapping("/imgboard/update")
    public String update(BoardRequestDto boardRequestDto) throws IOException {
        MultipartFile file = boardRequestDto.getFile1();

        long fnum = boardRequestDto.getFnum();
        ImgBoardDto dto = service.select(fnum);

        String orgfile = dto.getOrgfilename();
        String savefile = dto.getSavefilename();
        long filesize = dto.getFilesize();

        if(!file.isEmpty()){ //수정할 파일이 전송된 경우
            //기존파일삭제
            File f = new File(path + File.separator + savefile);
            if(f.exists()) f.delete();

            //파일업로드
            orgfile = file.getOriginalFilename();
            savefile = UUID.randomUUID() + "_" + orgfile;
            filesize = file.getSize();

            File ff = new File(path + File.separator + savefile);
            file.transferTo(ff);

        }

        //DTO에 담기
        ImgBoardDto imgBoardDto = ImgBoardDto.builder()
                .fnum(fnum)
                .writer(boardRequestDto.getWriter())
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .orgfilename(orgfile)
                .savefilename(savefile)
                .filesize(filesize)
                .build();

        //DB반영
        service.update(imgBoardDto);

        return "redirect:/imgboard/detail?fnum=" + fnum;
    }
}
