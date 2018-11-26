package com.example.mikhailtalancev.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mikhailtalancev.myapplication.State;

import java.util.List;

public class StateAdapter extends ArrayAdapter<State> {

    private LayoutInflater inflater;
    private int layout;
    private List<State> states;

    public StateAdapter(Context context, int resource, List<State> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        TextView priorityView = (TextView) view.findViewById(R.id.soloPriority);
        TextView nameView = (TextView) view.findViewById(R.id.soloName);
        TextView dateView = (TextView) view.findViewById(R.id.soloDate);
        FrameLayout noteView = (FrameLayout) view.findViewById(R.id.soloNote);

        State state = states.get(position);

        priorityView.setText(state.getPriority());
        nameView.setText(state.getName());
        dateView.setText(state.getNoteDate());
        noteView.setBackgroundColor(state.getColor());

        return view;
    }
}