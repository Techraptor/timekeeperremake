package com.falconraptor.timekeeper.school;
public class Teacher{
  private String name;
  public Schedule schedule;
  public String getName(){return name;}
  public void setName(String n){name=n;}
  public Teacher(String n){
    name=n;
  }
}
