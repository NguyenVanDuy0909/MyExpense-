package com.example.m_expense;

import  android.app.DatePickerDialog;
import  android.content.ContentValues;
import  android.content.Context;
import  android.database.Cursor;
import  android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import  android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static  final  String DATABASE_NAME ="TRIPA";

    public  static  final  String ID = "name_id";
    public  static  final  String NAME = "name";
    public  static  final  String DESTINATION = "destination";
    public  static  final  String DEPARTURE ="departure";
    public  static  final  String VEHICLE ="vehicle";
    public  static  final  String DATE = "date";
    public  static  final  String DESCRIPTION = "description";
    public  static  final  String RISK = "risk";

    private static  SQLiteDatabase database;
    private static DatabaseHelper databaseHelper;

    private  static  final  String DATABASE_CREATE = String.format(
            "CREATE TABLE %s("+
                    " %s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    " %s TEXT, "+
                    " %s TEXT, "+
                    " %s TEXT, "+
                    " %s TEXT, "+
                    " %s TEXT, "+
                    " %s TEXT, "+
                    " %s TEXT) ",
            DATABASE_NAME, ID, NAME,DESTINATION,DEPARTURE,VEHICLE,DATE,DESCRIPTION,RISK);

    public  DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
        database = getWritableDatabase();
    }

   public static DatabaseHelper instanceOfDatabase(Context context)
    {
        if(databaseHelper == null)
            databaseHelper = new DatabaseHelper(context);

        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_NAME);

        Log.v(this.getClass().getName(),DATABASE_NAME+"database upgrade to version"+
                newVersion + " - old data lost");
        onCreate(db);
    }


    public  long insertDetails(String name, String destination, String departure, String vehicle, String date, String description,String risk){
        ContentValues rowValues = new ContentValues();

         rowValues.put(NAME,name);
         rowValues.put(DESTINATION,destination);
         rowValues.put(DEPARTURE,departure);
         rowValues.put(VEHICLE,vehicle);
         rowValues.put(DATE,date);
         rowValues.put(DESCRIPTION,description);
         rowValues.put(RISK,risk);

         return  database.insertOrThrow(DATABASE_NAME,null,rowValues);
    }

    public  void getExams(){
        Cursor cursor = database.query(DATABASE_NAME,new String[]{ID,NAME,DESTINATION,DEPARTURE,VEHICLE,DATE,DESCRIPTION,RISK},
                null,null,null,null,NAME);


        cursor.moveToFirst();
        Exam.notes.clear();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String destination = cursor.getString(2);
            String departure = cursor.getString(3);
            String vehicle = cursor.getString(4);
            String date = cursor.getString(5);
            String description = cursor.getString(6);
            String risk = cursor.getString(7);

          Exam exam = new Exam(id, name, destination,departure,vehicle,date,description,risk);
          Exam.notes.add(exam);
             cursor.moveToNext();
        }
    }

    public void updateTrip(Exam note)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues rowValues = new ContentValues();

        rowValues.put(NAME,note.getName());
        rowValues.put(DESTINATION,note.getDestination());
        rowValues.put(DEPARTURE,note.getDeparture());
        rowValues.put(VEHICLE,note.getVehicle());
        rowValues.put(DATE,note.getDate());
        rowValues.put(DESCRIPTION,note.getDescription());
        rowValues.put(RISK,note.getRisk());

        sqLiteDatabase.update(DATABASE_NAME, rowValues, ID + " =? ", new String[]{String.valueOf(note.getId())});
    }


    public void deleteTrip(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_NAME, ID + "=" + id, null);

    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + DATABASE_NAME);
        db.close();
    }


}
