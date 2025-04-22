package com.example.demo.service;

import com.example.demo.dto.FileinfoDto;
import com.example.demo.entity.Fileinfo;
import com.example.demo.repository.FileinfoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FileinfoService {

    private final FileinfoRepository fr;

    public FileinfoDto insert(FileinfoDto dto){
        return new FileinfoDto(fr.save(dto.toEntity()));
    }

    public List<FileinfoDto> list(){
        return fr.findAll().stream().map(f->new FileinfoDto(f)).toList();
    }

    public FileinfoDto select(long filenum){
        return new FileinfoDto(fr.findById(filenum).get());
    }

    public void delete(long filenum){
        fr.deleteById(filenum);
    }

    public FileinfoDto update(FileinfoDto dto){
        Fileinfo file=fr.findById(dto.getFilenum()).get();
        file.setWriter(dto.getWriter());
        file.setTitle(dto.getTitle());
        file.setContent(dto.getContent());
        file.setFilesize(dto.getFilesize());
        file.setOrgfilename(dto.getOrgfilename());
        file.setSavefilename(dto.getSavefilename());
        return new FileinfoDto(file);
    }
}
