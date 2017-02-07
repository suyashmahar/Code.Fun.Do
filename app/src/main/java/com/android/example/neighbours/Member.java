package com.android.example.neighbours;

/**
 * Created by Harshit Bansal on 2/6/2017.
 */

public class Member {
    private String name,phone,email,relation;

    public Member(String name,String phone,String email,String relation){
        this.setName(name);
        this.setPhone(phone);
        this.setEmail(email);
        this.setRelation(relation);
    }
    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
    public String getEmail(){
        return email;
    }
    public String getRelation(){return relation;}
    public void setName(String a){
        this.name=a;
    }
    public void setPhone(String a){
        this.phone=a;
    }
    public void setEmail(String a){
        this.email=a;
    }
    public void setRelation(String a){
        this.relation=a;
    }
}