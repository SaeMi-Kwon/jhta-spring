package di13_scan;

import members.dto.MembersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JdbcService {

    @Autowired
    private JdbcDao jdbcDao;

    public void insert(MembersDto dto){
        jdbcDao.insert(dto);
    }

    public void update(MembersDto dto){
        jdbcDao.update(dto);
    }

    public void delete(int num){
        jdbcDao.delete(num);
    }

    public MembersDto select(int num){
        return jdbcDao.select(num);
    }

}
