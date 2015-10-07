package itp341.exercises.week6.geoquiz;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

    private static final String TAG = "QuizActivity";
    public static final String indexString = "ITP_Is_Fun";
    public static final String answerString = "itp341.answerString";
    private static final String saveString = "SaveBoolean";
    private static final String intString = "SaveIndex";

    //View references
    Button buttonTrue;
    Button buttonFalse;
    Button buttonNext;
    Button buttonCheat;
    TextView textQuestion;
    
    //Instance variables
    boolean isCheater = false;		//flag which indicates if user cheated on current question
    String[] questions;		//array of questions
    int[] answers;			//array of answers (0 for false, 1 for true)
    int currentIndex = 0;		//current question

    @Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    Log.d(TAG, "onCreate() called");
	    setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            isCheater = savedInstanceState.getBoolean(saveString, false);
            currentIndex = savedInstanceState.getInt(intString, -1);
        }

	    //find view
	    textQuestion = (TextView)findViewById(R.id.text_question);
	    buttonTrue = (Button)findViewById(R.id.button_true);	
	    buttonFalse = (Button)findViewById(R.id.button_false);
	    buttonNext = (Button)findViewById(R.id.button_next);
	    buttonCheat = (Button)findViewById(R.id.button_cheat);
	    
	    //initialize variables
	    questions = getResources().getStringArray(R.array.array_questions);
	    answers = getResources().getIntArray(R.array.array_answers);
	    
	    //set up listeners
	    buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);            //pass true because this is the True button
            }
        });
	    		
	    buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);            //pass false because this is the False button
            }
        });

	    buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1) % 5;
                updateQuestion();
                isCheater = false;
            }
        });

	    buttonCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cheatIntent = new Intent(getApplicationContext(), CheatActivity.class);
                cheatIntent.putExtra(indexString, currentIndex );
                cheatIntent.putExtra(answerString, answers);
                startActivityForResult(cheatIntent, 0);
            }
        });

        updateQuestion();
    }

	private void updateQuestion() {
		textQuestion.setText(questions[currentIndex]);
    }

	private void checkAnswer(boolean userPressedTrue) {
        if (isCheater == false) {
            if (userPressedTrue == true)
                if (answers[currentIndex] == 1)
                    Toast.makeText(QuizActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(QuizActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
            else if (userPressedTrue == false)
                if (answers[currentIndex] == 0)
                    Toast.makeText(QuizActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(QuizActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
        }
        else if (isCheater == true){
            if (userPressedTrue == true)
                if (answers[currentIndex] == 1)
                    Toast.makeText(QuizActivity.this, "Cheating is wrong", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(QuizActivity.this, "You did not cheat correctly", Toast.LENGTH_SHORT).show();
            else if (userPressedTrue == false)
                if (answers[currentIndex] == 0)
                    Toast.makeText(QuizActivity.this, "Cheating is wrong", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(QuizActivity.this, "You did not cheat correctly", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	Log.d(TAG, "onActivityResult");
        if (requestCode==0){
            if (resultCode == RESULT_OK)
                isCheater = true;
            else
                isCheater = false;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState");
        savedInstanceState.putBoolean(saveString, isCheater);
        savedInstanceState.putInt(intString, currentIndex);
    }

}
