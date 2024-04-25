package com.example.m_expense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static  final  String DATABASE_NAMES ="CostDB";

    public  static  final  String ID = "name_id";
    public  static  final  String NAME = "name";
    public  static  final  String AMOUNT = "amount";
    public  static  final  String TIME= "time";
    public  static  final  String TRIPID= "tripId";
    public  static  final  String COMMENTS  = "comments";

    private static SQLiteDatabase database;
    private static DataHelper dataHelper;


    private  static  final  String DATABASE_CREATE = String.format(
            "CREATE TABLE %s("+
                    " %s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    " %s TEXT, "+
                    " %s TEXT, "+
                    " %s TEXT, "+
                    " %s TEXT, "+
                    " %s TEXT) ",
            DATABASE_NAMES, ID, NAME,AMOUNT,TIME,TRIPID,COMMENTS);


    public  DataHelper(Context context){
        super(context, DATABASE_NAMES,null,1);
        database = getWritableDatabase();
    }

    public static DataHelper instanceOfDatabases(Context context)
    {
        if(dataHelper == null)
            dataHelper = new DataHelper(context);

        return dataHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_NAMES);

        Log.v(this.getClass().getName(), DATABASE_NAMES +"database upgrade to version"+
                newVersion + " - old data lost");
        onCreate(db);
    }

    public  long insertCost(String name, String amount, String time,int tripId ,String comments){
        ContentValues rowValues = new ContentValues();

        rowValues.put(NAME,name);
        rowValues.put(AMOUNT,amount);
        rowValues.put(TIME,time);
        rowValues.put(TRIPID,tripId);
        rowValues.put(COMMENTS,comments);


        return  database.insertOrThrow(DATABASE_NAMES,null,rowValues);
    }

    public  void getCost(int nodeId) {
        Cursor cursor = database.rawQuery("SELECT * FROM CostDB WHERE tripId =  " + String.valueOf(nodeId), null);

        cursor.moveToFirst();
        Cost.costs.clear();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String amount = cursor.getString(2);
            String time = cursor.getString(3);
            int tripId = cursor.getInt(4);
            String comments = cursor.getString(5);


            Cost cost = new Cost(id, name, amount, time, tripId, comments);
            Cost.costs.add(cost);
            cursor.moveToNext();
        }
    }
    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + DATABASE_NAMES);
        db.close();
    }






}
