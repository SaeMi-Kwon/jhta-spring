package com.example.demo.service;

import com.example.demo.dto.CommentsDto;
import com.example.demo.dto.PageResultDto;
import com.example.demo.entity.Comments;
import com.example.demo.entity.Movie;
import com.example.demo.repository.CommentsRepository;
import com.example.demo.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository cr;
    private final MovieRepository mr;
    private final CommentsRepository commentsRepository;

    //저장
    public CommentsDto insert(CommentsDto commentsDto){
        Optional<Movie> movie = mr.findById(commentsDto.getMnum());
        if(movie.isPresent()){
            Movie m = movie.get();
            Comments comm= cr.save(commentsDto.toEntity(m));
            return new CommentsDto(comm);
        }
        return null;
    }

    //삭제할 댓글번호를 파라미터로 받아서 삭제하는 메소드
    public void delete(Long cnum){
        cr.deleteById(cnum);
    }

    //전체 목록 얻어오기
    public PageResultDto list(Long mnum, PageRequest pageable){
        Page<Comments> pageList=commentsRepository.findByMnum(mnum,pageable);
        List<CommentsDto> clist=pageList.stream().map(c->new CommentsDto(c)).toList();
        PageResultDto pageRequestDto =
                new PageResultDto(clist,pageList.getNumber(),pageList.getTotalPages(),3); //페이지갯수:3

        return pageRequestDto;
    }

    public CommentsDto getComm(Long cnum){
        Optional<Comments> comm = commentsRepository.findById(cnum);
        if(comm.isPresent()){
            Comments c = comm.get();
            return new CommentsDto(c);
        }
        return null;
    }

    public CommentsDto update(CommentsDto commentsDto){
        Optional<Comments> comm = commentsRepository.findById(commentsDto.getCnum());
        if(comm.isPresent()){
            Comments c = comm.get();
            c.setComments(commentsDto.getId());
            c.setComments(commentsDto.getComments());
            return new CommentsDto(c);
        }
        return null;
    }
}
