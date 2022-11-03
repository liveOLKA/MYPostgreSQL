import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateCardsAndDeposits {

    // 1. Создание карты: в запросе прикручиваем в values след. значания: ID (NEXTVAL('CARD_SEC'), USER_ID,
    //CARD_PRODUCT_ID (их от 1 до 23), PAY_SYS_ID (1-VISA, 2-MASTER_CARD, 3- MIR), MOB_PAY_SYS_ID (вроде только 1),
    // CURRENCY_ID (1-USD, 2-EUR, 3-RUB), NUMBER (количество цифр обычно 16), EXPIRATION_DATE, OWNER (имя и фамилия юзера),
    // CARD_NAME (наверное, можно самому придумать), BALANCE,  ACCOUNT_NUMBER (должны быть цифры, какие ограничения не знаю),
    // CARD_LIMIT (не знаю ограничений), CARD_STATUS_ID (1-ACTIVE, 2-INACTIVE, 3-BLOCKED, 4-CLOSED), MAIN_CARD (true/false)
    public void createUserCard() throws SQLException, IOException {
        Connection connection = DbFunctions.getConnection();
        Statement statement = connection.createStatement();
        String sqlCommandCreateUserCard = "INSERT INTO CARD (ID, USER_ID, CARD_PRODUCT_ID, PAY_SYS_ID, " +
                "MOB_PAY_SYS_ID, CURRENCY_ID, NUMBER, EXPIRATION_DATE, OWNER, CARD_NAME, BALANCE, ACCOUNT_NUMBER, " +
                "CARD_LIMIT, CARD_STATUS_ID, MAIN_CARD)\n" +
                "     VALUES (6, 5, 2, 1, 1, 1, '12345678901236', '2025-01-01', 'KAWAI LEONARD', 'Smart Platinum', 10000, " +
                "'40817840543671234568', 1000, 2, true);";

        statement.executeUpdate(sqlCommandCreateUserCard);
    }

    // 1. Создание депозита: в запросе прикручиваем в values след. значания: ID (NEXTVAL('DEPOSIT_SEC'), NUMBER (12 цифр),
    // TYPE_DEPOSIT_ID (вроде от 1 до 32), CREATION_DATE,  EXPIRATION_DATE (CREATION_DATE +  количество месяцев = EXPIRATION_DATE(полагаю 6, 12, 18, 36, 72, 120),
    // USER_ID, BALANCE (там есть ограничения на каждую валюту и срок депозита), ACCOUNT_FOR_PERCENT (я вписывала тоже самое что в колонку NUMBER),
    // STATUS_ID (2-OPEN, 3-CLOSED, 4-LIMITED, 5-UNDER_OPENING, 6-UNDER_DOCUMENTS_SIGNING, 7-PENDING_BANK_CONFIRMATION,
    // 8-REFUSED_BY_THE_BANK, 9-CLOSED_EARLY)
    public void createUserDeposit() throws SQLException, IOException {
        Connection connection = DbFunctions.getConnection();
        Statement statement = connection.createStatement();
        String sqlCommandCreateUserDeposit = "INSERT INTO DEPOSIT (ID, NUMBER, TYPE_DEPOSIT_ID, CREATION_DATE, " +
                "EXPIRATION_DATE, USER_ID, BALANCE, ACCOUNT_FOR_PERCENT, STATUS_ID)" +
                "     VALUES (9, 408178105709, 22, '2022-05-02', '2022-11-02', 5, 145000, 408178105709, 3);";

        statement.executeUpdate(sqlCommandCreateUserDeposit);
    }
}
