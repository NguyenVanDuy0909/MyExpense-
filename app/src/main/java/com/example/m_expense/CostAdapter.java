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

public class CostAdapter extends ArrayAdapter <Cost>{

    public CostAdapter(Context context, List<Cost> resource) {
        super(context,0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        Cost note = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cell_cost,parent,false);

        }
        TextView name = convertView.findViewById(R.id.cellNameCost);
        TextView time = convertView.findViewById(R.id.cellTime);
        TextView comments = convertView.findViewById(R.id.cellComment);
        TextView cost = convertView.findViewById(R.id.cellCost);




        name.setText(note.getName());
        time.setText(note.getTime());
        comments.setText(note.getComments());
        cost.setText(note.getCost());


        return convertView;


    }
}
