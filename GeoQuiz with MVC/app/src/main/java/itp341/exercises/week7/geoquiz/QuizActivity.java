package itp341.exercises.week7.geoquiz;


import itp341.exercises.week7.geoquiz.model.QuizQuestion;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer.TrackInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

    private static final String TAG = "QuizActivity";
    
    private static final String KEY_INDEX = "itp341.exercises.week7.geoquiz.index";
	public static final String EXTRA_ANSWER_SHOWN = "itp341.exercises.week7.geoquiz.ANSWER_SHOWN";


    //View references
    Button buttonTrue;
    Button buttonFalse;
    Button buttonNext;
    Button buttonCheat;
    TextView textQuestion;
    
    //Instance variables
    boolean isCheater;		//flag which indicates if user cheated on current question
    int currentIndex;		//current question
	QuizQuestion[] questions = new QuizQuestion[]{
		new QuizQuestion(R.string.question_oceans, R.integer.answer_oceans),
		new QuizQuestion(R.string.question_mideast, R.integer.answer_mideast),
		new QuizQuestion(R.string.question_africa, R.integer.answer_africa),
		new QuizQuestion(R.string.question_asia, R.integer.answer_asia),
		new QuizQuestion(R.string.question_americas, R.integer.answer_americas)
	};

    public boolean convertIntResToBoolean(int resID){
        if (getResources().getInteger(resID) != 0)
            return true;
        else
            return false;
    }

    @Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    Log.d(TAG, "onCreate() called");
	    setContentView(R.layout.activity_quiz);
	  
	    //find view
	    textQuestion = (TextView)findViewById(R.id.text_question);
	    buttonTrue = (Button)findViewById(R.id.button_true);	
	    buttonFalse = (Button)findViewById(R.id.button_false);
	    buttonNext = (Button)findViewById(R.id.button_next);
	    buttonCheat = (Button)findViewById(R.id.button_cheat);
	    
	    //initialize variables
	    isCheater = false;		//assume user has NOT cheated
	    
	    //set up listeners
	    buttonTrue.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            checkAnswer(true);			//pass true because this is the True button
	        }
	    });
	
	    		
	    buttonFalse.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            checkAnswer(false);			//pass false because this is the False button
	        }
	    });

	    buttonNext.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            currentIndex = (currentIndex + 1) % questions.length;
	            isCheater = false;
	            updateQuestion();
	        }
	    });		

	    buttonCheat.setOnClickListener(new View.OnClickListener() {
	
	        @Override
	        public void onClick(View v) {
	            Log.d(TAG, "cheat button clicked");
	            Intent i = new Intent(QuizActivity.this, CheatActivity.class);
	            Log.d(TAG, "intent created");
	            boolean answerIsTrue = convertIntResToBoolean(questions[currentIndex].getAnswer());
	            i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
	            startActivityForResult(i, 0);
	        }
	    });

	    if (savedInstanceState != null) {
	        currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
	    }
	
	    updateQuestion();
	}

	private void updateQuestion() {
       textQuestion.setText(questions[currentIndex].getQuizquestion());
    }

    private void checkAnswer(boolean userPressedTrue) {
    	
        //boolean answerIsTrue = (answers[currentIndex] == 0) ? false : true;
        boolean answerIsTrue = convertIntResToBoolean(questions[currentIndex].getAnswer());

        int messageResId = 0;
        
        if (isCheater) {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.toast_correct_judgment;
            } else {
                messageResId = R.string.toast_incorrect_judgment;
            }
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.toast_correct;
            } else {
                messageResId = R.string.toast_incorrect;
            }
        }
        
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	Log.d(TAG, "onActivityResult");
        isCheater = data.getBooleanExtra(QuizActivity.EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, currentIndex);
    }
    

}
