package ge.ufc.webapps.query;

public enum Query {
    SELECT_SYS_TRANS_ID_BY_AGENT_TRANS_ID("SELECT SYSTEM_TRANSACTION_ID FROM TRANSACTIONS WHERE AGENT_TRANSACTION_ID = ? AND AGENT_ID = ?"),
    SELECT_USER_BY_ID("SELECT ID FROM USERS WHERE ID = ?"),
    SELECT_AGENT_BY_ID_AND_PASSWORD("SELECT * FROM AGENTS WHERE ID = ? AND PASSWORD = ?"),
    SELECT_ALLOWED_IP_BY_AGENT_ID("SELECT ALLOWED_IP FROM AGENT_ACCESS WHERE AGENT_ID = ?"),
    SELECT_ALL_PERSON_BY_ID("SELECT * FROM Users WHERE id = ?"),
    INSERT_TRANSACTION("INSERT INTO TRANSACTIONS(AGENT_ID, AGENT_TRANSACTION_ID, USER_ID, AMOUNT) VALUES (?, ?, ?, ?)"),
    SELECT_SYSTEM_TRANSACTION_ID_BY_WHERE_CLAUSE("SELECT SYSTEM_TRANSACTION_ID FROM TRANSACTIONS WHERE AGENT_TRANSACTION_ID = ? AND USER_ID = ? AND AGENT_ID = ? AND AMOUNT = ?"),
    ADD_USER_BALANCE("UPDATE USERS SET BALANCE = BALANCE + (SELECT AMOUNT FROM TRANSACTIONS WHERE USER_ID = ? AND AGENT_TRANSACTION_ID = ?) WHERE ID = ?");
    private final String SQL;

    Query(final String SQL) {
        this.SQL = SQL;
    }

    public String getSQL() {
        return SQL;
    }
}
