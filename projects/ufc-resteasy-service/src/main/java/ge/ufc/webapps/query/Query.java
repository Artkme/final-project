package ge.ufc.webapps.query;

public enum Query {
    SELECT_ALL_FROM_PAYMENTS_BY_PAYMENT_ID("SELECT * FROM PAYMENTS WHERE PAYMENT_ID = ?"),
    UPDATE_PAYMENTS_BY_PAYMENT_ID("UPDATE PAYMENTS SET STATUS = ?, CODE = ?, TRANSACTION_ID = ?, REQUEST_DATE = ?, RESPONSE_DATE = ?  WHERE PAYMENT_ID = ?"),
    INSERT_PAYMENTS("INSERT INTO PAYMENTS VALUES (?, ?, ?, ?, ?, ?, ?, ?)"),
    SELECT_ALL_FROM_PAYMENTS_BY_STATUS("SELECT * FROM PAYMENTS WHERE STATUS = ?");

    private final String SQL;

    Query(final String SQL) {
        this.SQL = SQL;
    }

    public String getSQL() {
        return SQL;
    }
}
