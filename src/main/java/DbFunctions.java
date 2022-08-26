import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class DbFunctions {

//    public static void main(String[] args) {
//        try {
//            Class.forName("org.postgresql.Driver");
//            //try (Connection connection = getConnection()) {
//                System.out.println("Connection to PostgreSQL DB successful!");
//            //}
//        } catch (Exception ex) {
//            System.out.println("Connection failed...");
//            System.out.println(ex);
//        }
//    }

    public static Connection getConnection() throws SQLException, IOException {

        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(
                "src/main/resources/database.properties"
        ))) {
            props.load(in);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }

    public void shouldSelectUsers() throws SQLException, IOException {
        Connection connection = DbFunctions.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }
    }

    public void selectAllInfoOfUsers() throws SQLException, IOException {
        Connection connection = DbFunctions.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM USERS");

        while (rs.next()) {
            int id = rs.getInt("ID");
            String firstName = rs.getString("FIRST_NAME");
            String lastName = rs.getString("LAST_NAME");
            String patronymic = rs.getString("PATRONYMIC");
            String birthday = rs.getString("BIRTHDAY");
            String email = rs.getString("EMAIL");
            String phone = rs.getString("PHONE");

            System.out.printf("%d, %s, %s, %s, %s, %s, %s \n", id, firstName, lastName, patronymic, birthday, email,
                    phone);
        }
    }

    public void createUser() throws SQLException, IOException {
        Connection connection = DbFunctions.getConnection();
        Statement statement = connection.createStatement();
        String sqlCommandCreateUser = "INSERT INTO USERS (ID, FIRST_NAME, LAST_NAME, PATRONYMIC, BIRTHDAY, " +
                "EMAIL, PHONE)\n" +
                "     VALUES (911, 'Korben', 'Dallas', 'Testing', '1955-03-19', 'korben@yahoo.net', '+000000000911');";

        statement.executeUpdate(sqlCommandCreateUser);
    }

    public void deleteUser() throws SQLException, IOException {
        Connection connection = DbFunctions.getConnection();
        Statement statement = connection.createStatement();
        String sqlCommandDeleteUser = "DELETE FROM USERS WHERE FIRST_NAME='Кавай'";
        statement.executeUpdate(sqlCommandDeleteUser);
    }

    public void createLoginAndPassword() throws SQLException, IOException {
        Connection connection = DbFunctions.getConnection();
        Statement statement = connection.createStatement();
        String sqlCommandCreateLoginAndPassword = "INSERT INTO SECURE (ID, LOGIN, PASSWORD, ATTEMPT_LOGIN_COUNT, " +
                "EXPIRATION_LOCK_TIME, IS_FIRST_LOGIN, USER_ID)\n" +
                "     VALUES (NEXTVAL('SECURE_SEC'), '1111911', " +
                "'$2a$10$YTsipAwH46/NiTpWfkb9JuJr6nMaUJNQ1/tmgO2FPKjq732T.NadC', 5, " +
                "'2016-12-25 00:00:00', true, 911);";

        statement.executeUpdate(sqlCommandCreateLoginAndPassword);
    }

    public void createUserRole() throws SQLException, IOException {
        Connection connection = DbFunctions.getConnection();
        Statement statement = connection.createStatement();
        String sqlCommandCreateUserRole = "INSERT INTO USER_ROLE (USER_ID, ROLE_ID)\n" +
                "     VALUES (911, 2);";

        statement.executeUpdate(sqlCommandCreateUserRole);
    }

    public void createUserAddresses() throws SQLException, IOException {
        Connection connection = DbFunctions.getConnection();
        Statement statement = connection.createStatement();
        String sqlCommandCreateUserAddresses = "INSERT INTO ADDRESS (ID, USER_ID, POSTAL_CODE_ID, COUNTRY_ID, " +
                "REGION_ID, AREA_ID, CITY_ID, SETTLEMENT_ID, STREET_ID, HOUSE, BLOCK, FLAT, TYPE)\n" +
                "     VALUES (NEXTVAL('ADDRESS_SEC'), 5, 3, 1, 1, 2, 6, 2, 10, '15', null, '25', 'REGISTRATION');";

        statement.executeUpdate(sqlCommandCreateUserAddresses);
    }

    public void createPersonalData() throws SQLException, IOException {
        Connection connection = DbFunctions.getConnection();
        Statement statement = connection.createStatement();
        String sqlCommandCreatePersonaldata = "INSERT INTO PASSPORT (ID, SERIAL_NUMBER, PASSPORT_CODE, " +
                "DIVISION_CODE, BIRTHDAY, DATE_ISSUE, GENDER, TERMINATION_DATE, FIRST_NAME, PATRONYMIC, LAST_NAME, " +
                "DEPARTMENT_ISSUED, BORN_PLACE, VALID, USER_ID)\n" +
                "     VALUES (NEXTVAL('PASSPORT_SEC'), '4568', '9999', '9999', '1991-06-29', '2010-06-29', 'Мужской'," +
                " '2030-06-29', 'Кавай', 'Testing', 'Леонард', 'РОВД г. Минска', 'Лос-Анджелес', true, 5);";

        statement.executeUpdate(sqlCommandCreatePersonaldata);
    }
}