package com.falconraptor.timekeeper.schedule;
public class Lunch{
  private Time start;
  private Time end;
  private int lunch;
  public void setLunch(int l){
    lunch=l;
  }
  public void setStart(Time s){
    start=s;
  }
  public void setStart(int hour,int minute){
    start=new Time(hour,minute);
  }
  public void setEnd(Time s){
    end=s;
  }
  public void setEnd(int hour,int minute){
    end=new Time(hour,minute);
  }
  public Time getStart(){
    return start;
  }
  public Time getEnd(){
    return end;
  }
  public int getLunch(){
    return lunch;
  }
}
