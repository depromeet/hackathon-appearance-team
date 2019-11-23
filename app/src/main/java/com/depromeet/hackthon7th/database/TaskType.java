package com.depromeet.hackthon7th.database;

public enum TaskType {
    ONCE("once"),
    DAILY("daily"),
    WEEKLY("weekly"),
    MONTHLY("monthly"),
    WEEKEND("weekend"),
    WEEKDAYS("weekdays");

    private String type;

    TaskType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
