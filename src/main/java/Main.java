import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        DbFunctions db = new DbFunctions();
//        db.shouldSelectUsers();
//        db.selectAllInfoOfUsers();
        //db.createUser();
       // db.deleteUser();
       // db.createLoginAndPassword();
       // db.createUserAddresses();
        db.shouldSelectUsers();
        db.selectAllInfoOfUsers();
        //db.createUserRole();
        //db.createPersonalData();
    }
}
