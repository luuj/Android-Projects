package itp341.exercises.week5_intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class MainActivity extends Activity {
	
	private static final String TAG = MainActivity.class.getSimpleName();
	
	EditText editName;
	Spinner spinnerDifficulty;
	Switch switchCheatMode;
	Button buttonLaunchActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editName = (EditText)findViewById(R.id.editName);
		spinnerDifficulty = (Spinner)findViewById(R.id.spinnerLevel);
		switchCheatMode = (Switch)findViewById(R.id.switchCheatMode);
		buttonLaunchActivity = (Button)findViewById(R.id.button_launch_activity);
		
		buttonLaunchActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				launchSecondActivity();
			}
		});
	
	}

	private void launchSecondActivity(){
		Intent i = new Intent(getApplicationContext(), SecondActivity.class);
		i.putExtra(SecondActivity.nameString, editName.getText().toString());
		if (switchCheatMode.isChecked())
			i.putExtra(SecondActivity.cheatString, "On" );
		else
			i.putExtra(SecondActivity.cheatString, "Off" );

		i.putExtra(SecondActivity.difficultyString, spinnerDifficulty.getSelectedItem().toString());
		startActivity(i);

		//finish() ends the activity
		//setResult(RESULT_OK) to send back a result

		//Use override for onactivityresult

		//Pass data back - make a new intent in second activity
		//setResult(RESULT_OK, intent)
	}
}
