package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageResultDto {
    private List<CommentsDto> list;  //현재페이지의 게시글 목록
    private int startPage;
    private int endPage;
    private int totalPageCount;  //전체 페이지 수(0부터 시작)
    private int page;       //현재 페이지 번호(0부터 시작)

    //blockLimit : 한페이지에 페이지블록 수
    public PageResultDto(List<CommentsDto> list, int page, int totalPageCount, int blockLimit){
        this.list=list;
        this.page=page;
        this.totalPageCount=totalPageCount;
        startPage = (page / blockLimit) * blockLimit;
        //마지막 페이지 번호를 초과하지 않도록
        endPage = Math.min(startPage+blockLimit-1, totalPageCount-1);
        //게시글이 하나도 없을 경우 endPage = 0으로 초기화
        if(totalPageCount==0) endPage=0;
    }
}
