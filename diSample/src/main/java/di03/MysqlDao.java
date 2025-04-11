package di03;

public class MysqlDao implements CommonDao {

    @Override
    public void insert(String data){
        System.out.println("Mysql과 연동해서 " + data + " 추가");
    }
    @Override
    public void update(String data){
        System.out.println("Mysql과 연동해서 " + data + " 수정");
    }
    @Override
    public void delete(String data){
        System.out.println("Mysql과 연동해서 " + data + " 삭제");
    }
    @Override
    public void select(String data){
        System.out.println("Mysql과 연동해서 " + data + " 조회");
    }



}
