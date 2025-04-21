package com.example.demo.controller;

import com.example.demo.dto.CommentsDto;
import com.example.demo.dto.PageResultDto;
import com.example.demo.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commService;

    @PostMapping("/comments")
    public ResponseEntity<String> insert(@RequestBody CommentsDto dto){
        try{
            commService.insert(dto);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("fail",HttpStatus.EXPECTATION_FAILED);
        }
    }

    //영화번호 페이지번호
    @GetMapping("/comments")
    //페이지 번호에 해당하는 댓글 3개 AJAX로 응답하기(PageResultDto)
    public ResponseEntity<PageResultDto> list(@RequestParam("mnum") long mnum,
                                                  @RequestParam(value = "page", defaultValue = "0")int page){

        PageRequest pageable=PageRequest.of(page,3);
        PageResultDto pageResultDto = commService.list(mnum,pageable);
        return new ResponseEntity<>(pageResultDto,HttpStatus.OK);
    }

    @DeleteMapping("/comments/{cnum}")
    public ResponseEntity<String> delete(@PathVariable long cnum){
        try {
            commService.delete(cnum);
            return new ResponseEntity<>("success",HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("fail",HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/comments/{cnum}")
    public ResponseEntity<CommentsDto> updateForm(@PathVariable long cnum){
        CommentsDto dto = commService.getComm(cnum);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PutMapping("/comments")
    public ResponseEntity<CommentsDto> update(@RequestBody CommentsDto commDto){
        CommentsDto dto = commService.update(commDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

//    @PutMapping("/comments")
//    public ResponseEntity<String> update(@RequestBody CommentsDto dto){
//        commService.insert(dto);
//        return new ResponseEntity<String>("success",HttpStatus.OK);
//    }


}
