package di12_scan;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan : @Component 및 Stereo Type(@Service, @Repository, @Controller)이 부여된 클래스들을
//자동으로 스캔하여 스프링 빈으로 등록해주는 역할을 한다.
@ComponentScan(basePackages = {"di12_scan"}) //ex) @ComponentScan(basePackages = {"di12_scan","di11_quiz})
public class MybatisConfig {

    //setMapperLocations(context.getResources)를 얻어오기 위함
    @Autowired
    private ApplicationContext context;

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

    //sqlSessionFactory설정
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setMapperLocations(context.getResources("classpath*:mapper/*.xml"));
        SqlSessionFactory factory=(SqlSessionFactory) sqlSessionFactoryBean.getObject();
        return factory;
    }

    //sqlSession설정
    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        SqlSessionTemplate sqlSessionTemplate=new SqlSessionTemplate(sqlSessionFactory());
        return sqlSessionTemplate;

        //return new SqlSessionTemplate(sqlSessionFactory());
    }


}
