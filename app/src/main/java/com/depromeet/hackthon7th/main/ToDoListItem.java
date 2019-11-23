package com.depromeet.hackthon7th.main;

public class ToDoListItem {

  private int id;
  private String alarmType;
  private String taskType;
  private String title;
  private String desc;
  private String date;
  private String time;
  private String priority;

  public ToDoListItem(int id, String alarmType, String taskType, String title, String desc,
      String date, String time, String priority) {
    this.id = id;
    this.alarmType = alarmType;
    this.taskType = taskType;
    this.title = title;
    this.desc = desc;
    this.date = date;
    this.time = time;
    this.priority = priority;

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public String getTaskType() {
    return taskType;
  }

  public void setTaskType(String taskType) {
    this.taskType = taskType;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getAlarmType() {
    return alarmType;
  }

  public void setAlarmType(String alarmType) {
    this.alarmType = alarmType;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMemo() {
    return desc;
  }

  public void setMemo(String desc) {
    this.desc = desc;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

}
