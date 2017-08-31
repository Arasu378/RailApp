package com.kyros.technologies.railapp.model;

/**
 * Created by kyros on 21-07-2017.
 */

public class TaskDataModel {
    private String TaskDataValue;
    private String Date;
    private String Comments;
    private boolean checked;
    public TaskDataModel(){

    }

    public String getTaskDataValue() {
        return TaskDataValue;
    }

    public void setTaskDataValue(String taskDataValue) {
        TaskDataValue = taskDataValue;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
