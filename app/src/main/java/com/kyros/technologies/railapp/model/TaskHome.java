package com.kyros.technologies.railapp.model;

/**
 * Created by kyros on 21-07-2017.
 */

public class TaskHome {
    private String TaskHeader;
    private String TaskSubHeaderOne;
    private String TaskSubHeaderTwo;
    public TaskHome(){

    }

    public String getTaskHeader() {
        return TaskHeader;
    }

    public void setTaskHeader(String taskHeader) {
        TaskHeader = taskHeader;
    }

    public String getTaskSubHeaderOne() {
        return TaskSubHeaderOne;
    }

    public void setTaskSubHeaderOne(String taskSubHeaderOne) {
        TaskSubHeaderOne = taskSubHeaderOne;
    }

    public String getTaskSubHeaderTwo() {
        return TaskSubHeaderTwo;
    }

    public void setTaskSubHeaderTwo(String taskSubHeaderTwo) {
        TaskSubHeaderTwo = taskSubHeaderTwo;
    }
}
