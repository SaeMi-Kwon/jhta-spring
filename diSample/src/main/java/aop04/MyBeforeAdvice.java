package aop04;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

//MethodBeforeAdvice 인터페이스를 상속받으면 메소드 실행전에 동작되는 어드바이스가 됨
public class MyBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("호출되는 메소드 ==> " + method.getName());
        System.out.println("전달되는 파라미터들");
        for(Object p:args){
            System.out.println(p);
        }
        System.out.println("tager ==> " + target);
        System.out.println();

    }
}
