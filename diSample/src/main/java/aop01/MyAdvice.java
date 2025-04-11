package aop01;

//공통관심사항 - 어드바이스
public class MyAdvice {
    public void preMethod(){
        System.out.println("메소드가 호출됩니다..");
    }
    
    public void test(){
        System.out.println("메소드가 종료됩니다..");
    }
}
