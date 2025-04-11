package aop03;

import org.aspectj.lang.ProceedingJoinPoint;

public class TimeAdvice {
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime=System.currentTimeMillis();  //메소드 호출전에 수행

        Object returnValue = joinPoint.proceed();  //타켓객체 메소드 호출(핵심기능)

        long endTime=System.currentTimeMillis();   //메소드 호출후에 수행
        System.out.println("메소드 수행시간 :" + ((endTime-startTime)/1000) + "초");

        return returnValue;
    }
}
