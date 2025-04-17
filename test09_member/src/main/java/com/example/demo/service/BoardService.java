package com.example.demo.service;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.PageResultDto;
import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor  //생상자로 주입받기
public class BoardService {

    private final BoardRepository br;  //final사용하면 자동으로 주입
    private final MemberRepository mr;

    public BoardDto insert(BoardDto boardDto){
        Optional<Member> mem = mr.findById(boardDto.getId());
        if(mem.isPresent()){  //데이터가 존재하는 경우
//            Member member = mem.get();
//            Board board = boardDto.toEntity(member);
            Board board = boardDto.toEntity(mem.get());
            Board b = br.save(board);
            return new BoardDto(b);
        }
        return null;
    }

    public PageResultDto list(Pageable pageable){
        Page<Board> page = br.findAll(pageable);
        List<BoardDto> list = page.stream().map(b-> new BoardDto(b)).toList();
        PageResultDto dto =
                new PageResultDto(list,page.getNumber(),page.getTotalPages(),3);
        return dto;
    }

    public BoardDto select(Long num){
        Optional<Board> b = br.findById(num);
        if(b.isPresent()){
            Board board = b.get();
            return new BoardDto(board);
        }
        return null;

        //return new BoardDto(br.findById(num).get());
    }

    public BoardDto update(BoardDto boardDto) {
        Optional<Board> b = br.findById(boardDto.getNum());
        if (b.isPresent()) {
            Board board = b.get();
            board.setTitle(boardDto.getTitle());
            board.setContent(boardDto.getContent());
            return new BoardDto(board);
        }
        return null;
    }

    public void delete(Long num){
        br.deleteById(num);
    }
}
