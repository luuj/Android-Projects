package itp341.luu.jonathan.a5luujonathan;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailFragment extends Fragment {

    EditText nameField, emailField, phoneField, addressField, notesField;
    Button eButton, sButton;
    ImageView profilePicture;
    BListener BL;

    Integer currPerson = -1;

    //Mandatory constructor
    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Assign variables to layout IDs
        nameField = (EditText) getView().findViewById(R.id.nameTF);
        emailField = (EditText) getView().findViewById(R.id.emailTF);
        phoneField = (EditText) getView().findViewById(R.id.phoneTF);
        addressField = (EditText) getView().findViewById(R.id.addressTF);
        notesField = (EditText) getView().findViewById(R.id.notesTF);
        profilePicture = (ImageView) getView().findViewById(R.id.pictureIV);

        eButton = (Button) getView().findViewById(R.id.editButton);
        sButton = (Button) getView().findViewById(R.id.saveButton);

        //Set up listeners for editing and saving
        BL = new BListener();
        eButton.setOnClickListener(BL);
        sButton.setOnClickListener(BL);

        if (savedInstanceState!=null){
            currPerson = savedInstanceState.getInt("CurrentPerson");
            setPictures();
        }

        //Initially disable editing
        disableTextFields();
    }

    public void updateFields(Intent receiveData){
        currPerson = receiveData.getIntExtra(MainActivity.p_save, -1);

        //Update all the info on the display
        setPictures();
        setTextFields(currPerson-1);
        disableTextFields();
    }

    public void setPictures(){
        if (currPerson == 1) {
            profilePicture.setImageResource(R.drawable.picture_one);
        }
        else if (currPerson == 2) {
            profilePicture.setImageResource(R.drawable.picture_two);
        }
        else if (currPerson == 3) {
            profilePicture.setImageResource(R.drawable.picture_three);
        }
    }

    //Function to update the display
    public void setTextFields(Integer currPerson){
        nameField.setText(MainActivity.storedData[currPerson][0]);
        emailField.setText(MainActivity.storedData[currPerson][2]);
        phoneField.setText(MainActivity.storedData[currPerson][3]);
        addressField.setText(MainActivity.storedData[currPerson][4]);
        notesField.setText(MainActivity.storedData[currPerson][5]);
    }

    //Disable editing of text fields
    public void disableTextFields(){
        nameField.setEnabled(false);
        emailField.setEnabled(false);
        phoneField.setEnabled(false);
        addressField.setEnabled(false);
        notesField.setEnabled(false);
    }

    //Enable editing of text fields
    public void enableTextFields(){
        emailField.setEnabled(true);
        phoneField.setEnabled(true);
        addressField.setEnabled(true);
        notesField.setEnabled(true);
    }

    //Edit and Save buttons
    private class BListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.editButton && currPerson!=-1)
                enableTextFields();
            else if (v.getId() == R.id.saveButton && currPerson!=-1){
                MainActivity.storedData[currPerson-1][2] = emailField.getText().toString();
                MainActivity.storedData[currPerson-1][3] = phoneField.getText().toString();
                MainActivity.storedData[currPerson-1][4] = addressField.getText().toString();
                MainActivity.storedData[currPerson-1][5] = notesField.getText().toString();

                disableTextFields();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("CurrentPerson", currPerson);
    }




}
