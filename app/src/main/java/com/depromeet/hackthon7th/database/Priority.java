package com.depromeet.hackthon7th.database;

public enum Priority {
    HIGH("high"),
    NORMAL("normal"),
    LOW("low");

    private String priority;


    Priority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }
}
