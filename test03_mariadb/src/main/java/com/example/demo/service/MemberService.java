package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberMapper mapper;

    public int insert(MemberDTO dto){
        return mapper.insert(dto);
    }

    public int update(MemberDTO dto){
        return mapper.update(dto);
    }

    public int delete(String id){
        return mapper.delete(id);
    }

    public MemberDTO select(String id){
        return mapper.select(id);
    }

    public List<MemberDTO> selectAll(HashMap<String,Integer> map){
        return mapper.selectAll(map);
    }

    public int getConunt(){
        return mapper.totalCount();
    }


}
