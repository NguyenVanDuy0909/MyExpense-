package com.example.m_expense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TripAdapter extends ArrayAdapter<Exam> {

    public TripAdapter(Context context, List<Exam> resource) {
        super(context,0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        Exam note = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cell_trip,parent,false);

        }
        TextView name = convertView.findViewById(R.id.cellName);
        TextView destination = convertView.findViewById(R.id.cellDestination);
        TextView departure = convertView.findViewById(R.id.cellDeparture);
        TextView vehicle = convertView.findViewById(R.id.cellVehicle);
        TextView date = convertView.findViewById(R.id.cellDate);
        TextView desc = convertView.findViewById(R.id.cellDesc);
        TextView risk = convertView.findViewById(R.id.cellRisk);




        name.setText(note.getName());
        desc.setText(note.getDescription());
        vehicle.setText(note.getVehicle());
        destination.setText(note.getDestination());
        date.setText(note.getDate());
        departure.setText(note.getDeparture());
        risk.setText(note.getRisk());




        return convertView;


    }
}
