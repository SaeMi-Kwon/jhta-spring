package di01;

public class MembersDao {
    public int insert(String data){
        System.out.println(data + "등록완료");
        return 1;
    }

    public int delete(String data){
        System.out.println(data + "삭제완료");
        return 1;
    }

    public int update(String data){
        System.out.println(data + "수정완료");
        return 1;
    }

    public String select(String data){
        System.out.println(data + "조회완료");
        return data;
    }
}
