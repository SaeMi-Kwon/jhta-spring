package di09_mybatis;

import di08_quiz.MyusersDao;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyusersConfig {

    @Autowired
    private ApplicationContext context;

    @Bean
    public BasicDataSource dataSource(){
        BasicDataSource ds=new BasicDataSource();
        ds.setDriverClassName("oracle.jdbc.OracleDriver");
        ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        ds.setUsername("c##scott");
        ds.setPassword("tiger");
        return ds;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setMapperLocations(context.getResources("classpath*:mapper/*.xml"));
        SqlSessionFactory factory=sqlSessionFactoryBean.getObject();
        return factory;
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    public MyusersDao myusersDao() throws Exception{
        MyusersDao dao = new MyusersDao();
        dao.setSqlSession(sqlSession());
        return dao;
    }

}
