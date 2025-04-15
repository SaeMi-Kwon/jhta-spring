package com.example.demo.interceptor;

/*
    1)스프링 인터셉터 흐름
    Http 요청 ->| WAS(톰캣) -> 필터 -> 서블릿(Dispatcher Servlet) |-> |스프링 인터셉터 -> 컨트롤러|
    서블릿 필터는 서블릿이 호출되기 이전에 호출된다.
    스프링 인터셉터는 컨트롤러 호출 직전에 호출된다.
    URL패턴을 서블릿 필터에 비해 정밀하게 설정할 수 있다.

    2)interface HandlerInterceptor
    preHandle 메소드
    - 컨트롤러 호출 전 수행되는 메소드
    - return true : 다음 인터셉터 또는 컨트롤러가 호출된다.
    - return false : 다음이 호출되지 않고 종료된다.
    postHandle 메소드
    - 컨트롤러 수행후 수행되는 메소드
    - 컨트롤러에서 예외가 발생하면 호출되지 않는다.
    afterCompletion 메소드
    - 컨트롤러 수행후 수행되는 메소드
    - 요청 완료 이후 항상 호출된다.
 */

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    //컨트롤러 수행전 실행
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri=request.getRequestURI(); //요청uri
        log.info("요청 uri ==>{}",uri);
        if(handler instanceof HandlerMethod){
            HandlerMethod method=(HandlerMethod) handler;
            String controllerName=method.getBeanType().getSimpleName();  //호출되는 컨트롤러 이름
            String methodName=method.getMethod().getName();  //호출되는 메소드 이름
            log.info("controller ==>{}", controllerName);
            log.info("method ==>{}", methodName);
        }

        //Enumeration == Iterator 와 비슷
        Enumeration<String> em = request.getParameterNames();  //전달된 모든 파라미터에 대한 이름들 얻어오기
        while (em.hasMoreElements()){
            //전달된 파라미터 이름 얻어오기
            String parameterName=em.nextElement();
            //전달된 파라미터이름에 해당되는 파라미터 값 얻어오기
            String parameterValue=request.getParameter(parameterName);
            log.info("parameterName ==>{}",parameterName);
            log.info("parameterValue ==>{}",parameterValue);
        }

        return true;  //다음 인터셉터 또는 컨트롤러 호출
    }

    //컨트롤러 수행후 실행 - 에러가 나면 호출하지 않음
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle.........");
        log.info("controller ==>{}", handler);
        log.info("model and view ==>{}", modelAndView);

    }

    //컨트롤러 수행후 실행 - 에러가 나도 호출됨
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion.........");
        if(ex!=null){  //ex가 null이 아니면 예외가 발생된 경우
            log.info("afterCompletion - Exception : {}", ex);
        }
    }
    
}
