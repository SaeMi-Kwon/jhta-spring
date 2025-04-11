package aop01;

public interface MembersDao {
    int insert(Object data);
    int update(Object data);
    int delete(Object data);
    Object select(Object data);
}
