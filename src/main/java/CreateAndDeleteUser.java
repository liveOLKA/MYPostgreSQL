import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAndDeleteUser {

    // 1. Создание юзера: в запросе прикручиваем ему в values след. значания: ID (если ничего не менять, а оставить NEXTVAL('USERS_SEC'),
    // то id проставится автоматически; если нужно определенное id, то проставить вместо значения NEXTVAL('USERS_SEC')  -  придуманное id,
    // FIRST_NAME, LAST_NAME, PATRONYMIC (тут решили указывать занчение Testing), BIRTHDAY, EMAIL (это значение не должно совпадать с уже имеющимися в БД),
    // PHONE (это значение не должно совпадать с уже имеющимися в БД)
    public void createUser() throws SQLException, IOException {
        Connection connection = DbFunctions.getConnection();
        Statement statement = connection.createStatement();
        String sqlCommandCreateUser = "INSERT INTO USERS (ID, FIRST_NAME, LAST_NAME, PATRONYMIC, BIRTHDAY, " +
                "EMAIL, PHONE)\n" +
                "     VALUES (NEXTVAL('USERS_SEC'), 'Korben', 'Dallas', 'Testing', '1955-03-19', 'korben@yahoo.net', '+000000000911');";

        statement.executeUpdate(sqlCommandCreateUser);
    }

    // 2. Создание логина и пароля для данного юзера: в запросе прописываем ему в values след. значения: ID (вставляем NEXTVAL('USERS_SEC'),
    // LOGIN (логин должен начинаться с 111), PASSWORD (здесь должен быть захешированный пароль. Его хешировать нужно
    // по данной ссылке https://bcrypt-generator.com/   В поле "String" данного приложения вводим придуманный пароль (сохраняем в блокнотике).
    //Требования к паролю: пароль должен содержать от 7 до 20 символов, буквы латинского алфавита (Aa-Zz)(без привязки к регистру), цифры (0-9)
    // в поле Rounds выбираем = 10.
    //Копируем полученный хешированный пароль (сохраняем в блокнотике) и вставляем полученный захешированный пароль сюда в значение),
    // ATTEMPT_LOGIN_COUNT (проставляем 5), EXPIRATION_LOCK_TIME (вставляем: 2016-12-25 00:00:00),
    // IS_FIRST_LOGIN (вставляем true), USER_ID (вставляем id данного юзера)
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

    // 3. Создание роли для этого юзера: в запросе прописываем в values след. значения: USER_ID (Id данного юзера),
    // ROLE_ID (id роли. Если ставим цифру 1, то юзер - неверифицированный (AUTH_NOT_VERIFIED), если 2 - верифицированный (AUTH_VERIFIED)
    public void createUserRole() throws SQLException, IOException {
        Connection connection = DbFunctions.getConnection();
        Statement statement = connection.createStatement();
        String sqlCommandCreateUserRole = "INSERT INTO USER_ROLE (USER_ID, ROLE_ID)\n" +
                "     VALUES (911, 2);";

        statement.executeUpdate(sqlCommandCreateUserRole);
    }

    // 4. Удаление созданного юзера: в запросе прописываем в FIRST_NAME=имя созданного юзера
    public void deleteUser() throws SQLException, IOException {
        Connection connection = DbFunctions.getConnection();
        Statement statement = connection.createStatement();
        String sqlCommandDeleteUser = "DELETE FROM USERS WHERE FIRST_NAME='Кавай'";
        statement.executeUpdate(sqlCommandDeleteUser);
    }
}
