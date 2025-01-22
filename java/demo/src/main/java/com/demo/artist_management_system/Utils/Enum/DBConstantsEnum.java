package com.example.demo.Utils.Enum;

public class DBConstantsEnum {

    ACTIVE("A", "Active Record"),
    DELETED("X", "Deleted Record");

    private String status;
    private String description;

    DBConstantsEnum(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
