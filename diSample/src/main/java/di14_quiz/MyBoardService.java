package di14_quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBoardService {

    @Autowired
    private MyBoardDao myBoardDao;

    public int insert(MyBoardDTO dto){
        return myBoardDao.insert(dto);
    }

    public int update(MyBoardDTO dto){
        return myBoardDao.update(dto);
    }

    public int delete(int num){
        return myBoardDao.delete(num);
    }

    public MyBoardDTO getInfo(int num){
        return myBoardDao.getInfo(num);
    }

    public List<MyBoardDTO> selectAll(){
        return myBoardDao.selectAll();
    }

}
