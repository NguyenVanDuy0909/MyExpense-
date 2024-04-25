package com.example.m_expense;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Cost {
    protected  int id;
    protected  String name;
    protected String cost ;
    private int tripId;
    protected String time;
    protected String comments;

    public static ArrayList<Cost> costs = new ArrayList<>();
    public static ArrayList<Cost> viewCost(){
        ArrayList<Cost> views = new ArrayList<>();
        for (Cost note : costs) {

            views.add(note);
        }

        return views;

    }

    public static String SAVE_COST =  "save";

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public Cost() {

    }

    public Cost(int id, String name, String cost, String time, int tripId, String comments) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.tripId = tripId;
        this.time = time;
        this.comments = comments;
    }

    @NonNull
    @Override
    public String toString() {
        return  id + "-" + name + "-"  + "-" + time +"-" + comments ;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}


    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTime() {return time;}

    public void setTime(String time) {this.time = time;}

    public String getComments() {return comments;}

    public void setComments(String comments) {this.comments = comments;}



}
