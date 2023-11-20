import db.Db;
import exceptions.CustomException;
import exceptions.DbException;
import ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        try {
            Db.initializeDb();

            MainFrame mainFrame = new MainFrame();
        } catch (DbException | CustomException e) {
            System.out.println(e.getMessage());
        }
    }
}