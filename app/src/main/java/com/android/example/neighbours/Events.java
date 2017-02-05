package com.android.example.neighbours;

public class Events{
    @com.google.gson.annotations.SerializedName("id")
    private String id;
    @com.google.gson.annotations.SerializedName("title")
    private String title;
    @com.google.gson.annotations.SerializedName("description")
    private String description;
    @com.google.gson.annotations.SerializedName("image")
    private String image;
    @com.google.gson.annotations.SerializedName("votes")
    private String votes;
    @com.google.gson.annotations.SerializedName("organizer")
    private String organizer;
    @com.google.gson.annotations.SerializedName("of_community")
    private String of_community;
    @com.google.gson.annotations.SerializedName("time")
    private String time;

    // Indicates if the item is completed
    @com.google.gson.annotations.SerializedName("complete")
    private boolean mComplete;

    public Events(){

    }

    public Events(String description, String id, String image, String of_community, String organizer, String time, String title, String votes) {
        this.description = description;
        this.id = id;
        this.image = image;
        this.of_community = of_community;
        this.organizer = organizer;
        this.time = time;
        this.title = title;
        this.votes = votes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean ismComplete() {
        return mComplete;
    }

    public void setmComplete(boolean mComplete) {
        this.mComplete = mComplete;
    }

    public String getOf_community() {
        return of_community;
    }

    public void setOf_community(String of_community) {
        this.of_community = of_community;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
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

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }


    @Override
    public boolean equals(Object o) {
        return o instanceof Events && ((Events) o).id == id;
    }
}