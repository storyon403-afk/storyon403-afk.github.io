package com.example.studentmanagementsystem.services;

public class Student {
    private int age;
    private int id;
    private String name;
    private String address;
    private boolean sex = false;


    public Student(){

    }
    public Student(int id, String name, int age, boolean sex,String address){
            this.id = id;
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.address = address;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setSex(boolean sex){
        this.sex = sex;
    }

    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public int getId(){
        return id;
    }
    public int getAge(){
        return age;
    }
    public boolean getSex(){
        return sex;
    }

    public String reSex(){
        if(this.sex == true){
            return "man";
        }else{
            return "woman";
        }
    }

    @Override
    public String toString(){
        return "$id: " + this.id + "|"+ "name: " + this.name + "|" + "age: " + this.age + "|" + "sex: " + reSex() + "|" + "address: " + this.address;
    }
}
