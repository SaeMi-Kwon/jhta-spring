package di03;

public class BoardController {
    private CommonDao dao;   //인터페이스를 멤버변수로 설정해주면 자식멤버 사용할수있음

    public BoardController(){}

    //생성자나 setter 둘중하나로 받아오면 된다(사용).
//    public BoardController(CommonDao dao){
//        this.dao=dao;
//    }

    public void setDao(CommonDao dao){
        this.dao=dao;
    }

    public void service(){
        dao.insert("스프링di이해1");
        dao.update("스프링di이해2");
        dao.delete("스프링di이해3");
        dao.select("스프링di이해4");
    }

}
