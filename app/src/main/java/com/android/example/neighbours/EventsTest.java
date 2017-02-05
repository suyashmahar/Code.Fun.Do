package com.android.example.neighbours;

/**
 * Created by Suyash on 04-02-2017.
 */

public class EventsTest {
    public String id;
    public String title;
    public String description;
    public String time;
    public String image;
    public String organiser;

    public void EventsTest(String description, String id, String image, String organiser, String time, String title) {
        this.description = description;
        this.id = id;
        this.image = image;
        this.organiser = organiser;
        this.time = time;
        this.title = title;
    }
}
