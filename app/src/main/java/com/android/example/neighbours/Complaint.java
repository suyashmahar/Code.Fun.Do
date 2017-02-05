package com.android.example.neighbours;

/**
 * Created by Suyash on 05-02-2017.
 */

public class Complaint {
    public String title;
    public String time;
    public String byUser;
    public String description;

    public Complaint(String byUser, String description, String time, String title) {
        this.byUser = byUser;
        this.description = description;
        this.time = time;
        this.title = title;
    }

    public String getByUser() {
        return byUser;
    }

    public void setByUser(String byUser) {
        this.byUser = byUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
