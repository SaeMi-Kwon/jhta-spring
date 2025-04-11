package service;

import dao.MembersDao;
import dao.PointDao;
import dto.MembersDto;
import dto.PointDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional  //service클래스 전체가 적용
public class MembersService {

    @Autowired
    private MembersDao membersDao;
    @Autowired
    private PointDao pointDao;

    //회원가입
    @Transactional
    public void insert(MembersDto dto){
        //회원 테이블에 추가
        membersDao.insert(dto);

        //포인트 테이블에 추가
        pointDao.insert(new PointDto(0, dto.getNum(), 1000));
    }

    public void delete(int num){
        pointDao.delete(num);
        membersDao.delete(num);
    }

    public void update(){}
    public void select(){}

}
