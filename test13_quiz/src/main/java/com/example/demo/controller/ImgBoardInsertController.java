package com.example.demo.controller;

import com.example.demo.dto.BoardRequestDto;
import com.example.demo.dto.ImgBoardDto;
import com.example.demo.entity.ImgBoard;
import com.example.demo.service.ImgBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ImgBoardInsertController {

    private final ImgBoardService service;

    @Value("${file.path}")
    private String path;

    @GetMapping("/imgboard/insert")
    public void insertForm(@AuthenticationPrincipal UserDetails user, Model model){
        model.addAttribute("user",user);
    }

    @PostMapping("/imgboard/insert")
    public String insert(BoardRequestDto boardRequestDto) throws IOException {
        MultipartFile file = boardRequestDto.getFile1();
        File f= new File(path);
        if(!f.exists()) f.mkdir();

        String orgfile = file.getOriginalFilename();
        String savefile = UUID.randomUUID() + "_" + orgfile;
        long fileSize = file.getSize();

        File fileSave = new File(path + File.separator + savefile);
        file.transferTo(fileSave);

        ImgBoardDto dto= ImgBoardDto.builder()
                .id(boardRequestDto.getId())
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .orgfilename(orgfile)
                .savefilename(savefile)
                .filesize(fileSize)
                .build();

        service.insert(dto);

        return "redirect:/";
    }
}
