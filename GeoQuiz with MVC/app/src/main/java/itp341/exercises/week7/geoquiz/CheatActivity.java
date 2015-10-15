package itp341.exercises.week7.geoquiz;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
 *	Functionality:
 *		Show Answer Button
 *			Displays answer to question (either true or false) using String resource
 *			Sets intent boolean extra value to false
 *
 *		Back Button
 *			User will terminate CheatActivity by pressing the back button
 *			
 *	Sending Intent Result back to Quiz Activity:
 * 		Instead of using Result_OK or Result_CANCELED, we will create an Intent 
 * 		and pass an boolean Extra that will indicate if the user cheated or not
 * 
 * 	Second Thoughts:
 * 		If the user loads this activity but decides not to click Show Answer, 
 * 		then that is NOT considered cheating so the boolean extra should indicate false
 * 
 */

public class CheatActivity extends Activity {

	private static final String TAG = "CheatActivity";
	public static final String EXTRA_ANSWER_IS_TRUE = "itp341.exercises.week7.geoquiz.ANSWER_IS_TRUE";
	
	// View references
	TextView textAnswer;
	Button buttonShowAnswer;

	// instance variables
	boolean answerIsTrue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate() called");
		setContentView(R.layout.activity_cheat);
		Log.d(TAG, getCallingActivity().toString());

		//find views
		textAnswer = (TextView) findViewById(R.id.text_answer);
		buttonShowAnswer = (Button) findViewById(R.id.button_show_answer);

		// If user opens activity and decides not to cheat, we should
		// set boolean extra to false
		setAnswerShownResult(false);

		answerIsTrue = getIntent()
				.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

	    //TODO
	    /*
	     * 	setup buttonShowAnswer event handler
	     * 		- displays the correct answer
	     * 		- 
	     */
		buttonShowAnswer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "showAnswer clicked");
				if (answerIsTrue) {
					textAnswer.setText(R.string.label_true);
				} else {
					textAnswer.setText(R.string.label_false);
				}
				setAnswerShownResult(true);
			}
		});
	}
	
	//TODO
    /*
     * 	setAnswerShownResult
     * 		input: boolean - whether user clicked the button
     * 
     * 		description: see initial comment at the top of the file
     */
	private void setAnswerShownResult(boolean isAnswerShown) {
		Log.d(TAG, "setAnswerShownResult() called");
		Intent data = new Intent();
		data.putExtra(QuizActivity.EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}

}
