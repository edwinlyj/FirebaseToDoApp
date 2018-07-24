package com.example.a16022895.firebasetodoapp;


import java.io.Serializable;

public class Message implements Serializable {

    private String title;
    private String date;
    private int numOfDays;
    private boolean completed;

    public Message() {
        // Default constructor required for calls to DataSnapshot.getValue(Message.class)

    }

    public Message(String title, String date , int numOfDays , boolean completed ) {
        this.title = title;
        this.date = date;
        this.numOfDays= numOfDays;
        this.completed = completed;
    }

    public int getNumOfDays(){
        return numOfDays;
    }
    public void setNumOfDays(int numOfDays){
        this.numOfDays = numOfDays;
    }

    public boolean getCompleted(){
        return completed;
    }

    public void setCompleted(boolean completed){
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
