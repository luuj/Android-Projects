package itp341.exercises.week5_intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SecondActivity extends Activity {

    public static final String nameString = "Fun";
    public static final String difficultyString = "FunTwo";
    public static final String cheatString = "FunThree";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent i = getIntent();
        String name = i.getStringExtra(nameString);
        String cheat = i.getStringExtra(cheatString);
        String difficulty = i.getStringExtra(difficultyString);

        TextView temp =  (TextView) findViewById(R.id.textOutput);
        temp.setText("Name: " + name + "\nCheat: " + cheat + "\nDifficulty: " + difficulty);
    }

}
