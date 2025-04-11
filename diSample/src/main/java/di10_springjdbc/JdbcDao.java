package di10_springjdbc;

import members.dto.MembersDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//members테이블과 연결작업하는 클래스
public class JdbcDao {
    private JdbcTemplate jdbcTemplate;

    //스프링이 생성해준 객체를 주입받기위한 setter
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public int insert(MembersDto dto){
        String sql="insert into members values(?,?,?,?,sysdate)";
        //방법1
        //jdbcTemplate.update("실행할 sql구문, ?에 대응되는 파라미터값들);
        //int n=jdbcTemplate.update(sql,dto.getNum(),dto.getName(),dto.getPhone(),dto.getAddr());

        //방법2
        Object[] args=new Object[]{dto.getNum(),dto.getName(),dto.getPhone(),dto.getAddr()};

        //방법3
        //Object[] args=new Object[]
        //args[0]=dto.getNum();
        //args[1]=dto.getName();

        int n=jdbcTemplate.update(sql,args);
        return n;
    }

    public int delete(int num){
        int n=jdbcTemplate.update("delete from members where num=?",num);
        return  n;
    }

    public int update(MembersDto dto){
        String sql="update members set name=?,phone=?,addr=? where num=?";
        Object[] args=new Object[]{dto.getName(),dto.getPhone(),dto.getAddr(),dto.getNum()};
        int n=jdbcTemplate.update(sql,args);
        return n;
    }
    
    public MembersDto select(int num){
        String sql="select * from members where num=?";

        try {
            //select로 조회된 결과를 어떤 DTO에 어떻게 담을지를 구현하는 익명의(인터페이스)클래스
            RowMapper<MembersDto> rowMapper = new RowMapper<MembersDto>() {
                @Override
                public MembersDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                    MembersDto dto = new MembersDto();
                    dto.setNum(rs.getInt("num"));
                    dto.setName(rs.getString("name"));
                    dto.setPhone(rs.getString("phone"));
                    dto.setAddr(rs.getString("addr"));
                    dto.setRegdate(rs.getDate("regdate"));
                    return dto;
                }
            };
            MembersDto dto = jdbcTemplate.queryForObject(sql, rowMapper, num);
            return dto;

        }catch (EmptyResultDataAccessException e){ //조회된 값이 없으면 예외발생함
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<MembersDto> selectAll(){
        RowMapper<MembersDto> rowMapper=(ResultSet rs,int rowNum)->{
            MembersDto dto = new MembersDto();
            dto.setNum(rs.getInt("num"));
            dto.setName(rs.getString("name"));
            dto.setPhone(rs.getString("phone"));
            dto.setAddr(rs.getString("addr"));
            dto.setRegdate(rs.getDate("regdate"));
            return dto;
        };
        List<MembersDto> list = jdbcTemplate.query("select * from members",rowMapper);
        return list;
    }

}
