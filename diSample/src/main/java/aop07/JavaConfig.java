package aop07;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "aop07")
@EnableAspectJAutoProxy   //<aop:aspectj-autoproxy/>
public class JavaConfig {


}
