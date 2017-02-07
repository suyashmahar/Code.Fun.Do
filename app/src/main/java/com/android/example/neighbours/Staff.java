package com.android.example.neighbours;

/**
 * Created by Harshit Bansal on 2/7/2017.
 */

public class Staff {
    private String name,phone,badge,address;

    public Staff(String name,String phone,String badge,String address){
        this.setName(name);
        this.setPhone(phone);
        this.setBadge(badge);
        this.setAddress(address);
    }

    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
    public String getBadge(){
        return badge;
    }
    public String getAddress(){return address;}

    public void setName(String a){
        this.name=a;
    }
    public void setPhone(String a){
        this.phone=a;
    }
    public void setBadge(String a){
        this.badge=a;
    }
    public void setAddress(String a){
        this.address=a;
    }
}
