package di06_javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //스프링 빈 설정 클래스
public class MyAppConfig {
    
    @Bean  //스프링 빈으로 등록해라
    public Person person(){
        Person p = new Person("홍길동",20);
        return p;
    }

    @Bean(name="dao")
    public MembersDao membersDao(){
        return new MembersDao();
    }

    @Bean
    public MembersController controller(){
        MembersController c=new MembersController(membersDao());
        return c;
    }
}
