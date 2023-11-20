import db.Db;
import exceptions.CustomException;
import exceptions.DbException;
import ui.MainFrame;

import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            Statement statement = Db.dbConnect();

            MainFrame mainFrame = new MainFrame();
        } catch (DbException | CustomException e) {
            System.out.println(e.getMessage());
        }
    }
}