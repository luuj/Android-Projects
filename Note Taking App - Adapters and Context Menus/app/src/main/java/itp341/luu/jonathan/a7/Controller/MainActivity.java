package itp341.luu.jonathan.a7.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import itp341.luu.jonathan.a7.Model.Note;
import itp341.luu.jonathan.a7.Model.NoteSingleton;
import itp341.luu.jonathan.a7.R;

public class MainActivity extends Activity {

    ImageView addButton;
    ListView noteList;
    ArrayList<Note> noteArrayList;
    CustomAdapter adapter;
    int longPressPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = (ImageView) findViewById(R.id.addButton);
        noteList = (ListView) findViewById(R.id.listView);

        //Set custom adapter for the listview and assign context menu for long press
        noteArrayList = NoteSingleton.get(this).getArrayList();
        adapter = new CustomAdapter(this, noteArrayList);
        noteList.setAdapter(adapter);
        registerForContextMenu(noteList);

        //Add starting notes at the beginning only
        if (savedInstanceState == null)
            addStartingNotes();


        //Listeners to start the Editing activity
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EditActivity.class);
                startActivityForResult(i, 0);
            }
        });

        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), EditActivity.class);
                i.putExtra(EditActivity.POSITION_VALUE, position);
                startActivityForResult(i, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //If the user saved or deleted a note, do something
        if (resultCode == RESULT_OK){
            adapter.notifyDataSetChanged();
        }
    }

    public void addStartingNotes(){
        Date d0 = new GregorianCalendar(1985, GregorianCalendar.JANUARY, 15).getTime();
        Date d1 = new GregorianCalendar(2014, GregorianCalendar.MAY, 1).getTime();
        Date d2 = new GregorianCalendar(2014, GregorianCalendar.AUGUST, 29).getTime();

        String [] titleTemp = getResources().getStringArray(R.array.title_included_list);
        String [] bodyTemp = getResources().getStringArray(R.array.body_included_list);

        NoteSingleton.get(MainActivity.this).addNote(new Note(titleTemp[0], bodyTemp[0], d0));
        NoteSingleton.get(MainActivity.this).addNote(new Note(titleTemp[1], bodyTemp[1], d1));
        NoteSingleton.get(MainActivity.this).addNote(new Note(titleTemp[2], bodyTemp[2], d2));
    }

    //Long Press context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        longPressPosition = info.position;

        menu.setHeaderTitle(NoteSingleton.get(MainActivity.this).getNote(longPressPosition).getTitle());
        menu.add(Menu.NONE, 0, 0, "Delete");
        menu.add(Menu.NONE, 0, 0, "Cancel");
    }

    //What to do when an item has been long pressed
    @Override
    public boolean onContextItemSelected(MenuItem item){
        super.onContextItemSelected(item);

        //If user presses delete, delete the note
        if (item.getTitle().equals("Delete")){
            NoteSingleton.get(MainActivity.this).deleteNote(longPressPosition);
            adapter.notifyDataSetChanged();
        }
        return true;
    }
}
