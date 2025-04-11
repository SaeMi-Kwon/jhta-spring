package aop09_quiz;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class QuizAdvice {

    //포인트컷
    @Pointcut("execution(public * aop09_quiz.PaymentDaoImpl.*(..)) || execution(public * aop09_quiz.PurchaseDaoImpl.*(..))")
    public void pointCut(){}

    //공통기능
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();
        System.out.println("메소드에 전달된 파라미터값 :");
        for(int i=0;i<args.length;i++){
            System.out.println( i + ":" + args[i]);
        }

        Object returnValue = joinPoint.proceed();  //핵심기능을 찾는 메소드 호출

        System.out.println("메소드 실행한 후에 리턴된 값:" + returnValue);
        return returnValue;
    }
}
