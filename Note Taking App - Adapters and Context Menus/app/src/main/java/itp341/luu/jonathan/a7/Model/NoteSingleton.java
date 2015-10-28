package itp341.luu.jonathan.a7.Model;


import android.content.Context;

import java.util.ArrayList;

public class NoteSingleton {

    private ArrayList<Note> mNoteList;
    private static NoteSingleton sNoteSingleton;
    private Context mAppContext;

    private NoteSingleton(Context c){
        mAppContext = c;
        mNoteList = new ArrayList<Note>();
    }

    public static NoteSingleton get(Context c){
        if (sNoteSingleton == null)
            sNoteSingleton = new NoteSingleton(c.getApplicationContext());

        return sNoteSingleton;
    }

    public void addNote(Note n){
        mNoteList.add(n);
    }

    public void deleteNote(int position){
        mNoteList.remove(position);
    }

    public void updateNote(int position, Note n){
        mNoteList.set(position, n);
    }

    public Note getNote(int position){
        return mNoteList.get(position);
    }

    public ArrayList<Note> getArrayList(){
        return mNoteList;
    }

}
