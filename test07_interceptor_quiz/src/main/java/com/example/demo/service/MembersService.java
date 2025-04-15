package com.example.demo.service;

import com.example.demo.dto.MembersDTO;
import com.example.demo.mapper.MembersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MembersService {

    @Autowired
    private MembersMapper mapper;

    public MembersDTO isMemberRole(HashMap<String,String> map){
        return mapper.isMemberRole(map);
    }


}
