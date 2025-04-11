package di11_quiz;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MyusersConfig {

    @Bean
    public BasicDataSource dataSource(){
        BasicDataSource bs=new BasicDataSource();
        bs.setDriverClassName("oracle.jdbc.OracleDriver");
        bs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        bs.setUsername("c##scott");
        bs.setPassword("tiger");
        return bs;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public MyusersDao myusersDao(){
        return new MyusersDao(jdbcTemplate());
    }

}

