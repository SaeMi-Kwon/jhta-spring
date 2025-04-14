package com.example.demo.mapper;

import com.example.demo.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;


@Mapper
public interface MemberMapper {
    MemberDTO isMemeber(HashMap<String, Object> map);
    int insert(MemberDTO dto);
//    int update(MemberDTO dto);
//    int delete(String id);
//    MemberDTO select(String id);
//    List<MemberDTO> selectAll(HashMap<String, Object> map);

}
