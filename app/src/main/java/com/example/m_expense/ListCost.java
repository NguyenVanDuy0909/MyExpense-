package com.example.m_expense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ListCost extends AppCompatActivity {

    int costId;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cost);


        iniWidget();
        
        loadDb();
        setAdapter();
        
    }

    private void setAdapter() {

        CostAdapter noteAdapter = new CostAdapter(getApplicationContext(), Cost. viewCost());
        list.setAdapter(noteAdapter);
    }

    private void loadDb() {
        Intent previousIntent = getIntent();
        costId = previousIntent.getIntExtra(Cost.SAVE_COST, -1);

        DataHelper sqLiteManager = DataHelper.instanceOfDatabases(this);
        sqLiteManager.getCost(costId);
    }

    private void iniWidget() {


        list = findViewById(R.id.listCost);
//        selectedNote  = Exam.getNoteForID(costId);
    }

    public void saveCost(View view) {
        Intent editNote = new Intent(getApplicationContext(), SaveCost.class);
        editNote.putExtra(Cost.SAVE_COST, costId);

        startActivity(editNote);

        
    }

    public void deleteAllCost(View view) {
        DataHelper sqLiteManager = DataHelper.instanceOfDatabases(this);
        sqLiteManager.deleteAll();
        finish();

    }
}