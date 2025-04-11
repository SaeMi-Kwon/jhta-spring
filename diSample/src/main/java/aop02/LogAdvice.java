package aop02;

import org.aspectj.lang.ProceedingJoinPoint;

//메소드 실행전에 전달된 파라미터를 출력 / 메소드 실행후에 리턴값을 출력
public class LogAdvice {

    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        //////////////// 메소드 실행되기 전에 수행될 기능 /////////////////
        Object[] args = joinPoint.getArgs();   //메소드에 전달된 파라미터값들 얻어오기
        System.out.println("메소드에 전달된 파라미터값들...");
        for(int i=0;i<args.length;i++){
            System.out.println( i + ":" + args[i]);
        }

        /// //////////////////////////////////////////////////////////

        Object returnValue = joinPoint.proceed();  //핵심기능을 찾는 메소드 호출

        /////////////// 메소드 실행된 후에 수행될 기능 ////////////////////
        System.out.println("메소드 실행한 후에 리턴된 값:" + returnValue);

        //////////////////////////////////////////////////////////////

        return returnValue;
    }
}
