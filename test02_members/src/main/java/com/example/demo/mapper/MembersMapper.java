package com.example.demo.mapper;

import com.example.demo.dto.MembersDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MembersMapper {
    int insert(MembersDTO dto);
    int delete(int num);
    int update(MembersDTO dto);
    MembersDTO getinfo(int num);
    List<MembersDTO> selectAll();

}
