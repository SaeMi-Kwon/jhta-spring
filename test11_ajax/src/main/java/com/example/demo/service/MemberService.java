package com.example.demo.service;

import com.example.demo.dto.MemberDto;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository mr;

    public MemberDto join(MemberDto dto){
        Member member=dto.toEntity();
        Member m = mr.save(member);
        return new MemberDto(m);
    }

    //전체데이터를 리턴
    public List<MemberDto> list(){
        List<Member> mem= mr.findAll();

        List<MemberDto> mlist =
                mem.stream().map(m -> new MemberDto(m)).toList();

        return mlist;
    }

    //삭제(delete)
    public void delete(String id){
        System.out.println("service ==> " + id);
        mr.deleteById(id);
    }

    //아이디 조회(select)
    public MemberDto select(String id){
        Optional<Member> m=mr.findById(id);
        if(m.isPresent()){
            return new MemberDto(m.get());
        }
        return null;
    }


    //수정(update)
    public MemberDto update(MemberDto dto){
        Optional<Member> m=mr.findById(dto.getId());
        if(m.isPresent()){
            Member mem=m.get();
            mem.setAge(dto.getAge());
            mem.setEmail(dto.getEmail());
            mem.setPwd(dto.getPwd());
            return new MemberDto(mem);
        }
        return null;
    }

}
