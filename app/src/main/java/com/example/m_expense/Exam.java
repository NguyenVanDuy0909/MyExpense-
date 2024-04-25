package com.example.m_expense;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Exam {
    protected  int id;
    protected String name;
    protected String destination;
    public static String EDIT_TRIP =  "edit";

    public static ArrayList<Exam> notes = new ArrayList<>();



    public static ArrayList<Exam> viewTrip(){
        ArrayList<Exam> views = new ArrayList<>();
        for (Exam note : notes) {
            views.add(note);
        }

        return views;

    }
//    private static final List<Exam> results = new ArrayList<>();

    public Exam(int id, String name, String destination, String departure, String vehicle, String date, String description, String risk) {
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.departure = departure;
        this.vehicle = vehicle;
        this.date = date;
        this.description = description;
        this.risk=risk;
    }


    public static Exam getNoteForID(int passedNoteId) {

        for (Exam note : notes) {
            if (note.getId() == passedNoteId){
                return  note;
            }
        }
        return null;

    }

    @NonNull
    @Override
    public String toString() {
        return id  + "-" + name + "-" + destination + "-" + departure  + "-" + vehicle  + "-" + date  + "-" + description + "-" + risk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected String departure;
    protected String vehicle;
    protected String date;
    protected String description;
    protected String risk;

    public String getRisk() {return risk;}

    public void setRisk(String risk) {this.risk = risk;}
}
