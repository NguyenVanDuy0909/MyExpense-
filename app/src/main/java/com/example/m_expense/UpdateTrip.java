package com.example.m_expense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateTrip extends AppCompatActivity {

    EditText inputName, inputDestination, inputDeparture, inputVehicle, inputDate,inputDescription;
    private Exam selectedNote;

    private RadioButton yes, no;
    RadioGroup radioGroupTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_trip);
        intWight();
        checkForEdit();


    }

    private void intWight() {
        inputName = findViewById(R.id.inputName2);
        inputDestination = findViewById(R.id.inputDestination2);
        inputDeparture = findViewById(R.id.inputDeparture2);
        inputVehicle = findViewById(R.id.inputVehicle2);
        inputDate = findViewById(R.id.inputDate2);
        inputDescription = findViewById(R.id.inputDescription2);
        yes = findViewById(R.id.radioYes2);
        no = findViewById(R.id.radioNo2);
        radioGroupTrip = findViewById(R.id.radioGroup2);
        inputDate.setOnFocusChangeListener((view1,b)->{
            if(b){
                MainActivity.MyDatePicker dateUpdate = new MainActivity.MyDatePicker();
                dateUpdate.setDateInput(inputDate);
                dateUpdate.show(getSupportFragmentManager(),"Update Date Input Oke");
            }
        });

    }

    private void checkForEdit() {

        Intent previousIntent = getIntent();

        int passedNoteId = previousIntent.getIntExtra(Exam.EDIT_TRIP, -1);
        selectedNote  = Exam.getNoteForID(passedNoteId);
        inputName.setText(selectedNote.getName());
        inputDescription.setText(selectedNote.getDescription());
        inputDestination.setText(selectedNote.getDestination());
        inputDeparture.setText(selectedNote.getDeparture());
        inputVehicle.setText(selectedNote.getVehicle());
        inputDate.setText(selectedNote.getDate());

        int checkRadiId= radioGroupTrip.getCheckedRadioButtonId();

        if (checkRadiId == R.id.radioYes){
            this.no.setChecked(true);
        }else if(checkRadiId == R.id.radioNo){

            this.yes.setChecked(true);
        }

    }

    public void saveNotes(View view) {

        DatabaseHelper sqLiteManager = DatabaseHelper.instanceOfDatabase(this);
        String name, desc,destination,departure, veli,date;
        name = String.valueOf(inputName.getText());
        desc = String.valueOf(inputDescription.getText());
        destination = String.valueOf(inputDestination.getText());
        departure = String.valueOf(inputDeparture.getText());
        veli = String.valueOf(inputVehicle.getText());
        date = String.valueOf(inputDate.getText());
        int radioGroups = radioGroupTrip.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioGroups);
        String ra = String.valueOf(radioButton.getText());

        if(radioGroups == R.id.radioYes ) {
            this.yes.setChecked(true);
        } else if(radioGroups == R.id.radioNo) {
            this.no.setChecked(true);
        }






        selectedNote.setName(name);
        selectedNote.setDescription(desc);
        selectedNote.setDeparture(departure);
        selectedNote.setDestination(destination);
        selectedNote.setVehicle(veli);
        selectedNote.setDate(date);
        selectedNote.setRisk(ra);
        sqLiteManager.updateTrip(selectedNote);
        finish();
    }


    public void deleteTrip(View view) {

        DatabaseHelper sqLiteManager = DatabaseHelper.instanceOfDatabase(this);
        sqLiteManager.deleteTrip(selectedNote.getId());
        finish();

    }

    public void deleteAll(View view) {

        DatabaseHelper sqLiteManager = DatabaseHelper.instanceOfDatabase(this);
        sqLiteManager.deleteTrip(selectedNote.getId());
        finish();

    }

    public void addExpense(View view) {

        Intent editNote = new Intent(getApplicationContext(), ListCost.class);
        editNote.putExtra(Cost.SAVE_COST, selectedNote.getId());


        startActivity(editNote);

    }
}