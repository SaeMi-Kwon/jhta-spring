package aop09_quiz;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "aop09_quiz")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class QuizConfig {

    @Autowired
    ApplicationContext context;

    //dataSouce설정
    @Bean
    public BasicDataSource dataSource(){
        BasicDataSource bs=new BasicDataSource();
        bs.setDriverClassName("oracle.jdbc.OracleDriver");
        bs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        bs.setUsername("c##scott");
        bs.setPassword("tiger");
        return bs;
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
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    //트랜잭션 매니져 설정
    @Bean
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

}
