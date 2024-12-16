package org.example.neuefische_recapproject_todolist.model;


public enum ToDoStatus {
    OPEN ("Open"),
    DOING ("Doing"),
    DONE ("Done");

    private String status;

    ToDoStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
