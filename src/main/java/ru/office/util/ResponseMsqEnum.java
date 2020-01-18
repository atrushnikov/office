package ru.office.util;

public enum ResponseMsqEnum {

    NO_ENTRY("No entry in table ${tableName} with id: ${id}");


    private String message;

    ResponseMsqEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
