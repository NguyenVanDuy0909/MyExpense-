package com.example.m_expense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioButton yes, no;
    RadioGroup radioGroupTrip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nameOfTrip = findViewById(R.id.nameOfTrip);
        EditText destination = findViewById(R.id.destination);
        EditText departure = findViewById(R.id.txtDeparture);//điểm đến
        EditText vehicle = findViewById(R.id.txtVehicle);//phuonwg tien
        EditText dateOfTheTrip = findViewById(R.id.dateOfTheTrip);
        EditText description = findViewById(R.id.description);
        yes = findViewById(R.id.radioYes);
        no = findViewById(R.id.radioNo);


        dateOfTheTrip.setOnFocusChangeListener((view,b)->{
            if (b){
                MyDatePicker dateLog = new MyDatePicker();
                dateLog.setDateInput(dateOfTheTrip);
                dateLog.show(getSupportFragmentManager(),"Date Input Oke");
            }
        });


        radioGroupTrip = findViewById(R.id.radioGroup);

        this.radioGroupTrip.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                doOnGroupTrip(group, checkedId);
            }
        });

        Button saveButton = findViewById(R.id.btnSave);

        saveButton.setOnClickListener(view->{
            String name = nameOfTrip.getText().toString();
            String inDestination  = destination.getText().toString();
            String inDeparture = departure.getText().toString();
            String inVehicle = vehicle.getText().toString();
            String date = dateOfTheTrip.getText().toString();
            String inDescription = description.getText().toString();

            int radioGroups = radioGroupTrip.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(radioGroups);
            String ra = String.valueOf(radioButton.getText());

            if (name.isEmpty() ) {
                nameOfTrip.setError("Field can't be empty");
            } else if(inDestination.isEmpty()) {
                destination.setError("Field can't be empty");
            } else if(inDeparture.isEmpty()){
                departure.setError("Field can't be empty");
            } else if(inVehicle.isEmpty()){
                vehicle.setError("Field can't be empty");
            } else if(date.isEmpty()){
                dateOfTheTrip.setError("Field can't be empty");
            } else if(inDescription.isEmpty()){
                description.setError("Field can't be empty");
            }else {
                nameOfTrip.setError(null);
                DatabaseHelper dbHelper = new DatabaseHelper(this);
                dbHelper.insertDetails(name,inDestination,inDeparture,inVehicle,date,inDescription,ra);
//                Toast.makeText(this,"Information was saved!",Toast.LENGTH_SHORT).show();
                Toast.makeText(this,"Information was saved!" + ra,Toast.LENGTH_SHORT).show();
            }
        });



//        Button viewButton = findViewById(R.id.btnView);
//        viewButton.setOnClickListener(v -> {
////            DatabaseHelper dbHelper = new DatabaseHelper( this);
//
////            List<Exam> exams = dbHelper.getExams();
////
////            ArrayAdapter<Exam>arrayAdapter= new ArrayAdapter<Exam>(this,
////                    android.R.layout.simple_list_item_1,exams);
//
//
////            ListView examList = findViewById(R.id.examListView);
////            examList.setAdapter(arrayAdapter);
////
////            examList.setOnItemClickListener((parent, view, position, id) -> {
////                Exam selectedExam = exams.get(position);
////                openAddExpen(selectedExam);
//            });
//        });

    }

    private void doOnGroupTrip(RadioGroup group, int checkedId) {
        int checkedRadioId = group.getCheckedRadioButtonId();

        if(checkedRadioId == R.id.radioYes ) {
            this.yes.setChecked(true);
        } else if(checkedRadioId == R.id.radioNo) {
            this.no.setChecked(true);
        }
    }


//    private void openAddExpen(Exam selectedExam) {
//        Intent intent = new Intent(this, AddExpen.class);
//        intent.putExtra("name",selectedExam.name);
//        intent.putExtra("id",selectedExam.id);
//        intent.putExtra("destination",selectedExam.destination);
//        intent.putExtra("Departure",selectedExam.departure);
//
//        intent.putExtra("vehicle",selectedExam.vehicle);
//        intent.putExtra("date",selectedExam.date);
//        intent.putExtra("description",selectedExam.description);
//
//        startActivity(intent);
//    }
    public  static  class MyDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        public EditText getDateInput() {return dateInput;}

        public void setDateInput(EditText dateInput) {this.dateInput = dateInput;}

        @Override
        public Dialog onCreateDialog(@Nullable Bundle saveInstanceSate){
            final Calendar c= Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return  new DatePickerDialog(requireContext(),this,year,month,day);
        }


        EditText dateInput;

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String valueDate = dayOfMonth + "/" + month + "/" +  year;
            dateInput.setText(valueDate);
        }
    }

    public void viewListTrip(View view) {

        Intent viewTrip = new Intent(this, ViewTrip.class );
        startActivity(viewTrip);

    }
}

