package itp341.luu.jonathan.a5luujonathan;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static final String p_save = "itp341.luu.jonathan.savePersonName";
    public static String [][] storedData = new String[3][6];
    Button personOne, personTwo, personThree;
    ButtonListener BL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variables to buttons in Layout, set Button Listener
        personOne = (Button) findViewById(R.id.personOne);
        personTwo = (Button) findViewById(R.id.personTwo);
        personThree = (Button) findViewById(R.id.personThree);

        BL = new ButtonListener();

        personOne.setOnClickListener(BL);
        personTwo.setOnClickListener(BL);
        personThree.setOnClickListener(BL);

        //Check for screen rotation and save data input
        if (savedInstanceState!=null){
            storedData[0] = savedInstanceState.getStringArray("Array0");
            storedData[1] = savedInstanceState.getStringArray("Array1");
            storedData[2] = savedInstanceState.getStringArray("Array2");
        }
        else {
            //Initialize temporary resources from array
            for (int k = 0; k < 6; k++) {
                storedData[0][k] = getResources().getStringArray(R.array.bob_profile)[k];
                storedData[1][k] = getResources().getStringArray(R.array.turkleton_profile)[k];
                storedData[2][k] = getResources().getStringArray(R.array.stan_profile)[k];
            }
        }

    }

    private class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Integer clickedPerson = v.getId();

            Intent detailedScreen = new Intent(getApplicationContext(), DetailActivity.class);

            //Determine which person was clicked and place it in an Intent
            if (clickedPerson == R.id.personOne)
                detailedScreen.putExtra(p_save, 1);
            else if (clickedPerson == R.id.personTwo)
                detailedScreen.putExtra(p_save, 2);
            else if (clickedPerson == R.id.personThree)
                detailedScreen.putExtra(p_save, 3);

            //Determine whether if it is dual-pane or single-pane
            if (findViewById(R.id.combinedContainer) != null){
                DetailFragment dF = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
                dF.updateFields(detailedScreen);
            }
            else
                startActivity(detailedScreen);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArray("Array0", storedData[0]);
        savedInstanceState.putStringArray("Array1", storedData[1]);
        savedInstanceState.putStringArray("Array2", storedData[2]);
    }

}
