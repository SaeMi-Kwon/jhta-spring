package di13_scan;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
//@ComponentScan : @Component 및 Stereo Type(@Service, @Repository, @Controller)이 부여된 클래스들을
//자동으로 스캔하여 스프링 빈으로 등록해주는 역할을 한다.
@ComponentScan(basePackages = {"di13_scan"})

public class JdbcConfig {

    //dataSource설정
    @Bean
    public BasicDataSource dataSource(){
        BasicDataSource ds=new BasicDataSource();
        ds.setDriverClassName("oracle.jdbc.OracleDriver");
        ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        ds.setUsername("c##scott");
        ds.setPassword("tiger");
        return ds;
    }

    //jdbcTemplate설정
    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;

        //return new JdbcTemplate(dataSource());
    }

}
