package itp341.luu.jonathan.a7.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import itp341.luu.jonathan.a7.Model.Note;
import itp341.luu.jonathan.a7.Model.NoteSingleton;
import itp341.luu.jonathan.a7.R;

public class EditActivity extends Activity{

    public static final String POSITION_VALUE = "itp341.luu.jonathan.position";
    int position;

    Button saveButton, deleteButton;
    EditText titleText, bodyText;
    ButtonListener BL;
    Note editNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        saveButton = (Button) findViewById(R.id.saveButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        titleText = (EditText) findViewById(R.id.titleText);
        bodyText = (EditText) findViewById(R.id.textEditor);

        BL = new ButtonListener();
        saveButton.setOnClickListener(BL);
        deleteButton.setOnClickListener(BL);

        Intent i = getIntent();
        position = i.getIntExtra(POSITION_VALUE, -1);

        //If this is a new note, create a new one. Else update it
        if (position == -1){
            editNote = new Note();
            deleteButton.setEnabled(false);
        }
        else{
            editNote = NoteSingleton.get(this).getNote(position);
            setNoteFields();
        }

    }

    public class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //If the user presses Save
            if (v.getId() == saveButton.getId()){
                Date date = new Date();
                editNote.setDate(date);
                editNote.setTitle(titleText.getText().toString());
                editNote.setContent(bodyText.getText().toString());

                //If new note, add to ArrayList. Else, update it in the list
                if (position == -1)
                    NoteSingleton.get(EditActivity.this).addNote(editNote);
                else
                    NoteSingleton.get(EditActivity.this).updateNote(position, editNote);

                setResult(RESULT_OK);
                finish();
            }
            //If the user presses Delete
            else if (v.getId() == deleteButton.getId()){
                NoteSingleton.get(EditActivity.this).deleteNote(position);
                setResult(RESULT_OK);
                finish();
            }

        }
    }

    public void setNoteFields(){
        titleText.setText(editNote.getTitle());
        bodyText.setText(editNote.getContent());
    }

}
