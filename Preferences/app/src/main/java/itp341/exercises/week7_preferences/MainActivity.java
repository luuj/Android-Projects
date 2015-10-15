package itp341.exercises.week7_preferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();
	

	TextView textDisplay;
	EditText editInfo;
	Button buttonSave;
	Button buttonLoad;
    SharedPreferences.Editor prefEditor;
    SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textDisplay = (TextView) findViewById(R.id.textDisplay);
		editInfo = (EditText) findViewById(R.id.editInfo);
		buttonSave = (Button) findViewById(R.id.buttonSave);
		buttonLoad = (Button) findViewById(R.id.buttonLoad);

        prefs = getSharedPreferences("Preferences", MODE_PRIVATE);
        prefEditor = prefs.edit();

		buttonSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, editInfo.getText().toString(), Toast.LENGTH_SHORT).show();
                prefEditor.putString("Name", editInfo.getText().toString());
                prefEditor.commit();
            }
        });

        buttonLoad.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = prefs.getString("Name", "None");
                textDisplay.setText(temp);
            }
        });


	}
}






