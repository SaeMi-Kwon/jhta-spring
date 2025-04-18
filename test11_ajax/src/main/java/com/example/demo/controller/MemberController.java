package com.example.demo.controller;

import com.example.demo.dto.MemberDto;
import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
    [ Rest API ]
    요청방식 :  GET  - 조회(select)
	          POST - 등록(insert)
	          PUT  - 수정(update)
	          DELETE  - 삭제(delete)

    * 변하지 않는 데이터는 @PathVariable로 파라미터를 보낸다. (상품번호,아이디,...)
    http://localhost:8080/members/hello
    http://localhost:8080/members/rest
    * 변하는 데이터는 쿼리스트링으로 보낸다. (검색조건,페이지,...)
    http://localhost:8080/members?page=1

 */

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;
    private final MemberService memberService;

    //@RequestBody : 파라미터값 json를 객체로 받는다(JSON를 객체로 변환)
    @PostMapping("/member/join")
    public ResponseEntity<String> join(@RequestBody MemberDto dto){
        try {
            service.join(dto);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("fail", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberDto>> list(){
        List<MemberDto> mlist=memberService.list();
        return new ResponseEntity<>(mlist,HttpStatus.OK);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<String> del(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>("success", HttpStatus.OK);

    }

    @GetMapping("/member/{id}")
    public ResponseEntity<MemberDto> getInfo(@PathVariable String id) {
        MemberDto dto = memberService.select(id);
        if(dto!=null){
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/members/update")
    public ResponseEntity<String> update(@RequestBody MemberDto dto) {
        try {
            MemberDto m = service.update(dto);
            return new ResponseEntity<>("success", HttpStatus.OK);

        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("fail", HttpStatus.EXPECTATION_FAILED);
        }
    }

}
