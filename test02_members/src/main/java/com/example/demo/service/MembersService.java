package com.example.demo.service;

import com.example.demo.dto.MembersDTO;
import com.example.demo.mapper.MembersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembersService {

    @Autowired
    private MembersMapper mapper;

    public int insert(MembersDTO dto){
        return mapper.insert(dto);
    }

    public int update(MembersDTO dto){
        return mapper.update(dto);
    }

    public int delete(int num){
        return mapper.delete(num);
    }

    public MembersDTO getinfo(int num){
        return mapper.getinfo(num);
    }

    public List<MembersDTO> selectAll(){
        return mapper.selectAll();
    }

}
