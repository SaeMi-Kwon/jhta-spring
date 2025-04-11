package aop08;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeAdvice1 {
    //pointCut
    @Pointcut("execution(public * aop08.MembersDaoImpl.*(..))")
    public void exeuteTime(){}

    //공통기능
    @Around("exeuteTime()")
    public Object execute(ProceedingJoinPoint joinPoint)throws Throwable {
        long startTime=System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime=System.currentTimeMillis();
        System.out.println("메소드실행시간: "+ (endTime-startTime)/1000 + "초");
        return result;
    }
}
