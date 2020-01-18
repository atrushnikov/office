package ru.office.util;

public class NoEntryException extends Exception {

    private String id;
    private String tableName;

    public NoEntryException() {
    }

    public NoEntryException(String message, String id, String tableName) {
        super(message);
        this.id = id;
        this.tableName = tableName;
    }

    public NoEntryException(String message) {
        super(message);
    }

    public NoEntryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoEntryException(Throwable cause) {
        super(cause);
    }

    public NoEntryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getId() {
        return id;
    }

    public String getTableName() {
        return tableName;
    }
}
