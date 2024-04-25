package com.example.m_expense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SaveCost extends AppCompatActivity {

    EditText costEdit, nameEdit , timeEdit, commentEdit;
    int costId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_cost);

        Intent previousIntent = getIntent();
        costId = previousIntent.getIntExtra(Cost.SAVE_COST, -1);
        iniWidget();
    }

    private void iniWidget() {
        costEdit = findViewById(R.id.saveCost);
        nameEdit = findViewById(R.id.saveName);
        timeEdit = findViewById(R.id.saveTime);
        commentEdit = findViewById(R.id.saveComment);

        timeEdit.setOnFocusChangeListener((view2,b)->{
            if (b){
                MainActivity.MyDatePicker dateCost = new MainActivity.MyDatePicker();
                dateCost.setDateInput(timeEdit);
                dateCost.show(getSupportFragmentManager(),"Date Cost Input Oke");
            }
        });

    }

    public void save(View view) {
        String name = nameEdit.getText().toString();
        String cost  = costEdit.getText().toString();
        String time = timeEdit.getText().toString();
        String comment = commentEdit.getText().toString();

        DataHelper dbHelper = new DataHelper(this);
        dbHelper.insertCost(name, cost, time, costId , comment);
//                Toast.makeText(this,"Information was saved!",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Information was saved!" ,Toast.LENGTH_SHORT).show();


    }
}