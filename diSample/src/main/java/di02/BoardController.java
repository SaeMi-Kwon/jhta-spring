package di02;

public class BoardController {
    //private OracleDao dao;
    //private MysqlDao dao;
    private CommonDao dao;   //인터페이스를 멤버변수로 설정해주면 자식멤버 사용할수있음

    public BoardController(){
        //dao=new OracleDao();
        dao=new MysqlDao();
    }

    public void service(){
        dao.insert("스프링di이해1");
        dao.update("스프링di이해2");
        dao.delete("스프링di이해3");
        dao.select("스프링di이해4");
    }

}
