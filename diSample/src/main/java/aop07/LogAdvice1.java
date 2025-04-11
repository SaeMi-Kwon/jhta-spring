package aop07;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice1 {

    //포인트컷 메소드 - 리턴형이 void이고 @Pointcut으로 포인트컷을 지정한다.
    @Pointcut("execution(public * aop07.MembersDaoImpl.*(..))")
    public void logTarget(){}

    //공통기능
    @Around("logTarget()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();
        System.out.println("메소드에 전달된 파라미터값들...");
        for(int i=0;i<args.length;i++){
            System.out.println( i + ":" + args[i]);
        }

        Object returnValue = joinPoint.proceed();  //핵심기능을 찾는 메소드 호출

        System.out.println("메소드 실행한 후에 리턴된 값:" + returnValue);
        return returnValue;
    }
}
