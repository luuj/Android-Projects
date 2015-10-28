package itp341.luu.jonathan.a7.Controller;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import itp341.luu.jonathan.a7.Model.Note;
import itp341.luu.jonathan.a7.Model.NoteSingleton;
import itp341.luu.jonathan.a7.R;

public class CustomAdapter extends ArrayAdapter<Note> {
    TextView title, date;

    public CustomAdapter (Context c, ArrayList<Note> notes){
        super(c, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Note currNote = NoteSingleton.get(getContext()).getNote(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_entry, parent, false);

        title = (TextView) convertView.findViewById(R.id.titleListName);
        date = (TextView) convertView.findViewById(R.id.dateListName);

        title.setText(currNote.getTitle());
        date.setText(currNote.getDate());

        return convertView;
    }



}
