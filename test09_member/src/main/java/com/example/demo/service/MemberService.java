package com.example.demo.service;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.MemberDto;
import com.example.demo.dto.PageResultDto;
import com.example.demo.dto.PageUtilDto;
import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberRepository mr;

    public MemberDto insert(MemberDto dto){
        //save()메소드 반환타입이 entity이므로 dto의 toEntity()로 통해 entity로 변환해준다.
        Member m = dto.toEntity();
        Member member = mr.save(m);
        mr.flush();

        //entity는 DB에 밀접한 관계이므로 servie,repository에서만 사용해야한다.
        //controller에서 사용할때또는 값을 넘길때는 entity -> dto로 변환시켜서 사용한다.
        MemberDto memberDto = new MemberDto(member);
        return memberDto;
    }

//    //전체목록 반환하는 메소드
//    public List<MemberDto> list(){
//        List<Member> mlist = mr.findAll();
//
//        //toList()는 자바17버전 사용
//        //collect(Collectors.toList())는 자바 8~16 버전에서 사용
////        List<MemberDto> dtoList = mlist.stream().map(m->{
////            return new MemberDto(m);
////        }).toList();
//
//        //축약1
//        //mlist.stream().map(m->new MemberDto(m)).toList();
//
//        //축약2
//        List<MemberDto> dtoList=mlist.stream().map(MemberDto::new).toList();
//        return dtoList;
//    }

    //페이징 처리
    public PageUtilDto<MemberDto> list(Pageable pageable){
        Page<Member> page = mr.findAll(pageable);
        List<MemberDto> list = page.stream().map(m-> new MemberDto(m)).toList();
        PageUtilDto<MemberDto> dto =
                new PageUtilDto<>(list,page.getNumber(),page.getTotalPages(),3);
        return dto;
    }

    public MemberDto select(String id){
        Optional<Member> m = mr.findById(id);
        if(!m.isEmpty()){  //Optional로 감싸두면 null값이 아니라 비워있는지 아닌지로 판단해야된다.
            return new MemberDto(m.get());
        }else {
            return null;
        }
    }

    //수정메소드 완성해 보세요(비밀번호,이메일,나이 수정)
    public MemberDto update(MemberDto dto){
        Optional<Member> m = mr.findById(dto.getId());
        if(!m.isEmpty()){
            Member member = m.get();
            member.setPwd(dto.getPwd());
            member.setEmail(dto.getEmail());
            member.setAge(dto.getAge());
            //mr.save(m2);    //@Transactional가 없을때 명시적으로 사용해줘야한다(안정하게 사용해도 무관)
            return new MemberDto(member);
        }
        return null;
    }

    public void delete(String id){
        mr.deleteById(id);
    }

}
