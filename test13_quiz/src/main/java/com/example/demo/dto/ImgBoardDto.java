package com.example.demo.dto;

import com.example.demo.entity.BoardUser;
import com.example.demo.entity.ImgBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ImgBoardDto {
    private long fnum;
    private String id;
    private String title;
    private String content;
    private String orgfilename;
    private String savefilename;
    private long filesize;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public ImgBoardDto(ImgBoard imgBoard){
        this.fnum = imgBoard.getFnum();
        BoardUser user = imgBoard.getBoardUser();
        this.id = (user != null) ? user.getId() : null;
        this.title = imgBoard.getTitle();
        this.content = imgBoard.getContent();
        this.orgfilename = imgBoard.getOrgfilename();
        this.savefilename = imgBoard.getSavefilename();
        this.filesize = imgBoard.getFilesize();
        this.createdDate = imgBoard.getCreatedDate();
        this.updatedDate = imgBoard.getUpdatedDate();
    }

    public ImgBoard toEntity(BoardUser boardUser){
        return ImgBoard.builder()
                .fnum(fnum)
                .boardUser(boardUser)
                .title(title)
                .content(content)
                .orgfilename(orgfilename)
                .savefilename(savefilename)
                .filesize(filesize)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }

}
