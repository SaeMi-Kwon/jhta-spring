package di11_quiz;

import members.dto.MembersDto;
import myusers.dto.MyusersDTO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public class MyusersDao {
    private JdbcTemplate jdbcTemplate;

    public MyusersDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public int insert(MyusersDTO dto){
        String sql="insert into myusers values(?,?,?,sysdate)";
        Object[] arr=new Object[]{dto.getId(),dto.getPwd(),dto.getEmail()};
        int n=jdbcTemplate.update(sql,arr);
        return n;
    }

    public int update(MyusersDTO dto){
        String sql="update myusers set pwd=?,email=? where id=?";
        Object[] arr=new Object[]{dto.getPwd(),dto.getEmail(),dto.getId()};
        int n=jdbcTemplate.update(sql,arr);
        return n;
    }

    public int delete(String id){
        int n=jdbcTemplate.update("delete from myusers where id=?",id);
        return n;
    }

    public MyusersDTO select(String id){
        String sql="select * from myusers where id=?";

        try {
            RowMapper<MyusersDTO> rowMapper = (ResultSet rs, int rowNum) -> {
                MyusersDTO dto = new MyusersDTO();
                dto.setId(rs.getString("id"));
                dto.setPwd(rs.getString("pwd"));
                dto.setEmail(rs.getString("email"));
                dto.setRegdate(rs.getDate("regdate"));
                return dto;
            };
            MyusersDTO dto = jdbcTemplate.queryForObject(sql, rowMapper, id);
            return dto;

        }catch (EmptyResultDataAccessException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<MyusersDTO> selectAll(){
        RowMapper<MyusersDTO> rowMapper=(ResultSet rs, int rowNum)->{
            MyusersDTO dto=new MyusersDTO();
            dto.setId(rs.getString("id"));
            dto.setPwd(rs.getString("pwd"));
            dto.setEmail(rs.getString("email"));
            dto.setRegdate(rs.getDate("regdate"));
            return dto;
        };
        List<MyusersDTO> list = jdbcTemplate.query("select * from myusers",rowMapper);
        return list;
    }
}
