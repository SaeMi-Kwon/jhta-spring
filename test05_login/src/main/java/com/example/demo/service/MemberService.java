package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class MemberService {

    @Autowired
    private MemberMapper mapper;

    public MemberDTO isMember(HashMap<String, Object> map){
        return mapper.isMemeber(map);
    }

    public int insert(MemberDTO dto){
        return mapper.insert(dto);
    }

//    public int update(MemberDTO dto){
//        return mapper.update(dto);
//    }
//
//    public int delete(String id){
//        return mapper.delete(id);
//    }
//
//    public MemberDTO select(String id){
//        return mapper.select(id);
//    }

}
