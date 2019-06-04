package ru.office.util;

public enum  TableNamesEnum {

    OFFICE_PROPERTY_TYPE("office_property_type"),
    OFFICE_CATEGORY("office_category"),
    OFFICE("office"),
    DEPARTMENT("department");

    private String name;

    TableNamesEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
