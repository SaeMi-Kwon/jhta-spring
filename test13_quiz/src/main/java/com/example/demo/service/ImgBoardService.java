package com.example.demo.service;

import com.example.demo.dto.ImgBoardDto;
import com.example.demo.entity.ImgBoard;
import com.example.demo.repository.BoardUserRepository;
import com.example.demo.repository.ImgBoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ImgBoardService {

    private final ImgBoardRepository imgBoardRepository;
    private final BoardUserRepository boardUserRepository;

    public ImgBoardDto insert(ImgBoardDto imgBoardDto){
        return new ImgBoardDto(imgBoardRepository.save(imgBoardDto.toEntity(boardUserRepository.findById(imgBoardDto.getId()))));
    }

    public void delete(long fnum){
        imgBoardRepository.deleteById(fnum);
    }

    public ImgBoardDto select(long fnum){
        return new ImgBoardDto(imgBoardRepository.findById(fnum).get());
    }

    public List<ImgBoardDto> list(){
        return imgBoardRepository.findAll().stream().map(b->new ImgBoardDto(b)).toList();
    }

    public ImgBoardDto update(ImgBoardDto imgBoardDto){
        ImgBoard imgBoard =imgBoardRepository.findById(imgBoardDto.getFnum()).get();
        imgBoard.setTitle(imgBoardDto.getTitle());
        imgBoard.setContent(imgBoardDto.getContent());
        imgBoard.setOrgfilename(imgBoardDto.getOrgfilename());
        imgBoard.setSavefilename(imgBoardDto.getSavefilename());
        imgBoard.setFilesize(imgBoardDto.getFilesize());
        return new ImgBoardDto(imgBoard);
    }
}
