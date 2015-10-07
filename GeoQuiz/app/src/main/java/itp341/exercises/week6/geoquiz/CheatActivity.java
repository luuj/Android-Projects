package itp341.exercises.week6.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity{

	private static final String TAG = "CheatActivity";
	private static final String textSave = "Save true/false";
	private static final String textCheat = "Did he cheat or not";

	// View references
	TextView textAnswer;
	Button buttonShowAnswer;

	// instance variables
	boolean clicked;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate() called");
		setContentView(R.layout.activity_cheat);
		Log.d(TAG, getCallingActivity().toString());

		//find views
		textAnswer = (TextView) findViewById(R.id.text_answer);
		buttonShowAnswer = (Button) findViewById(R.id.button_show_answer);
		clicked = false;

		if (savedInstanceState != null){
			clicked = savedInstanceState.getBoolean(textCheat);
			String updateTV = savedInstanceState.getString(textSave);
			if (clicked==true){
				textAnswer.setText(updateTV);
				setResult(RESULT_OK);
			}
		}

		buttonShowAnswer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				setResult(RESULT_OK);
				clicked = true;
				Intent tempIntent = getIntent();

				int tempIndex = tempIntent.getIntExtra(QuizActivity.indexString, -1);

				if (tempIntent.getIntArrayExtra(QuizActivity.answerString)[tempIndex] == 0)
					textAnswer.setText("False");
				else
					textAnswer.setText("True");
			}
		});
	}

	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		String temp = (String) textAnswer.getText();
		savedInstanceState.putString(textSave, temp);
		savedInstanceState.putBoolean(textCheat, clicked);
	}
}
