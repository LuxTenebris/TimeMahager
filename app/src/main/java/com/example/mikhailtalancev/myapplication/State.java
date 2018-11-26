package com.example.mikhailtalancev.myapplication;

import android.graphics.Color;

public class State {

    private String name; // название
    private String priority;  // столица
    private String noteDate; // ресурс флага
    private int color;

    public State(String name, String priority, String noteDate, int color){

        this.name = name;
        this.priority = priority;
        this.noteDate = noteDate;
        this.color = color;
    }

    public int getColor() {
        return  this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getNoteDate() {
        return this.noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }
}