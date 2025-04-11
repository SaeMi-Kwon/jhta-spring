package di04;

public class BoardController {
    private Board boardDao;

    public BoardController(Board boardDao){
        this.boardDao=boardDao;
    }

    public void service(){
        boardDao.insert("di04복습1");
        boardDao.delete("di04복습2");
        boardDao.update("di04복습3");
        boardDao.select("di04복습4");
    }
}
