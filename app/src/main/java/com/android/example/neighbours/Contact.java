package com.android.example.neighbours;

/**
 * Created by Suyash on 05-02-2017.
 */

public class Contact {
    public String id;
    public String name;
    public String address;
    public String bloodgroup;
    public Contact(String name, String id, String address,String bloodgroup) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.bloodgroup = bloodgroup;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodGroup() {
        return bloodgroup;
    }

    public void setBloodGroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }
}
