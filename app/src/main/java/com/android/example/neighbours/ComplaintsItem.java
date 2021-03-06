package com.android.example.neighbours;

/**
 * Created by Suyash on 06-02-2017.
 */

public class ComplaintsItem {

    public String title, description, time, byUser;

    public ComplaintsItem(String title, String time, String description, String byUser) {
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
