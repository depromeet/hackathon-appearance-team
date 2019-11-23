package com.depromeet.hackthon7th.database;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject {

    @PrimaryKey
    private int id;

    private String title;  // required
    private String description; // option
    private String type;
    private Date deadLine; // required
    private Boolean isDone = false;
    private Date alarmStart;
    private Date alarmEnd;
    private String priority;

    public Task() {
    }

    public Task(String title, Date deadLine, Date alarmStart, Date alarmEnd, Priority priority) {
        this(title, null, TaskType.ONCE, deadLine, alarmStart, alarmEnd, priority);
    }

    public Task(String title, TaskType type, Date deadLine,
                Date alarmStart, Date alarmEnd, Priority priority) {
        this(title, null, type, deadLine, alarmStart, alarmEnd, priority);
    }

    public Task(String title, String description, TaskType type,
                Date deadLine, Date alarmStart, Date alarmEnd, Priority priority) {
        this.title = title;
        this.description = description;
        this.type = type.getType();
        this.deadLine = deadLine;
        this.alarmStart = alarmStart;
        this.alarmEnd = alarmEnd;
        this.priority = priority.getPriority();
    }

    // Getter
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskType getType() {
        return TaskType.valueOf(type);
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public Boolean getDone() {
        return isDone;
    }

    public Date getAlarmStart() {
        return alarmStart;
    }

    public Date getAlarmEnd() {
        return alarmEnd;
    }

    public Priority getPriority() {
        return Priority.valueOf(priority);
    }
    // Setter

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(TaskType type) {
        this.type = type.getType();
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public void setAlarmStart(Date alarmStart) {
        this.alarmStart = alarmStart;
    }

    public void setAlarmEnd(Date alarmEnd) {
        this.alarmEnd = alarmEnd;
    }

    public void setPriority(Priority priority) {
        this.priority = priority.getPriority();
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", deadLine=" + deadLine +
                ", isDone=" + isDone +
                ", alarmStart=" + alarmStart +
                ", alarmEnd=" + alarmEnd +
                ", priority='" + priority + '\'' +
                '}';
    }
}
