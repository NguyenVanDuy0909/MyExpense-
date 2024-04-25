package com.example.m_expense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class ViewTrip extends AppCompatActivity {

    ListView listView;
//    SearchView searchView;
//
//    ArrayAdapter<Exam> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip);
        iniWidget();
        loadDb();
        setAdapter();
//        arrayAdapter = new ArrayAdapter<Exam>(this, android.R.layout.simple_list_item_1);//,text1,nameList
//        listView.setAdapter(arrayAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(ViewTrip.this,"You click")
//            }
//        });





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Exam selectedNote = (Exam) listView.getItemAtPosition(position);
                Intent editNote = new Intent(getApplicationContext(), UpdateTrip.class);
                editNote.putExtra(Exam.EDIT_TRIP, selectedNote.getId());

                startActivity(editNote);
            }
        });


    }

    private void setAdapter() {
        TripAdapter noteAdapter = new TripAdapter(getApplicationContext(), Exam.viewTrip());
        listView.setAdapter(noteAdapter);

    }

    private void loadDb() {
        DatabaseHelper sqLiteManager = DatabaseHelper.instanceOfDatabase(this);
        sqLiteManager.getExams();
    }

    private void iniWidget() {
        listView = findViewById(R.id.examListView);


    }


    public void deleteAllTrip(View view) {
        DatabaseHelper sqLiteManager = DatabaseHelper.instanceOfDatabase(this);
        sqLiteManager.deleteAll();
        finish();

    }
}