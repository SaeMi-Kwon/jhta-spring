package aop05;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//메소드 실행전후에 동작되는 어드비저(advisor) 만들기
public class LogAdviceAround implements MethodInterceptor {
    //로그를 출력하기 위한 객체 얻어오기
    private Logger logger= LoggerFactory.getLogger(getClass());

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //////////////////// 메소드 실행전에 수행 ////////////////////////
        Object[] args=invocation.getArguments();  //전달된 파라미터
        String methodName=invocation.getMethod().getName();  //호출된 메소드이름
        if(logger.isDebugEnabled()){   //디버그 레벨인 경우
            logger.debug(methodName + "메소드 호출!");
            logger.debug("<< 전달된 파라미터 >>");
            for(Object a: args){
                logger.debug(a + " ");
            }
            logger.debug("..................");
        }

        ///////////////////////////////////////////////////////////////

        Object result = invocation.proceed();  //핵심기능을 갖는 메소드 호출

        //////////////////// 메소드 실행후에 수행 ////////////////////////
        if(logger.isDebugEnabled()){
            logger.debug(methodName + "메소드 종료...");
            logger.debug("메소드에서 리턴된 값:" + result);
        }

        ///////////////////////////////////////////////////////////////

        return result;
    }
}
