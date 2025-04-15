package com.example.demo.mapper;

import com.example.demo.dto.MembersDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface MembersMapper {
    MembersDTO isMemberRole(HashMap<String,String> map);
}
