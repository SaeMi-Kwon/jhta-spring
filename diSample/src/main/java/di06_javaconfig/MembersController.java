package di06_javaconfig;

public class MembersController {
    private MembersDao dao;

    public MembersController(){}

    public MembersController(MembersDao dao){
        this.dao =dao;
    }
    public void setDao(MembersDao dao){
        this.dao=dao;
    }
    public void service(){
        dao.insert("홍길동1");
        dao.update("홍길동2");
        dao.delete("홍길동3");
        dao.select("홍길동4");

    }
}
