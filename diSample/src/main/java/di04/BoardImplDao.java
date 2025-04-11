package di04;

public class BoardImplDao implements Board{
    @Override
    public void insert(String str) {
        System.out.println(str + " 글 등록하기");
    }

    @Override
    public void delete(String str) {
        System.out.println(str + " 글 삭제하기");
    }

    @Override
    public void update(String str) {
        System.out.println(str + " 글 수정하기");
    }

    @Override
    public void select(String str) {
        System.out.println(str + " 글 조회하기");
    }
}
