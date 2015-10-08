package itp341.exercises.week8_midterm_review;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final String TAG = MainActivity.class.getSimpleName();
	Spinner spinnerNumTickets;
	Button buttonProceed;
	TextView textCost;
	
	int numTickets = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//need to create the corresponding view in the XML layout
		spinnerNumTickets = (Spinner) findViewById(R.id.ticketCountSpinner);
		buttonProceed = (Button) findViewById(R.id.proceedButton);
		textCost = (TextView) findViewById(R.id.totalCost);

        //TODO implement spinner listener to update total cost
		spinnerNumTickets.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                numTickets = Integer.parseInt((String)spinnerNumTickets.getSelectedItem());
                textCost.setText("Total: " + numTickets*9.99);
			}
			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
			}
		});

		//TODO implement proceed button listener to launch ConfirmActivity and pass any info
        buttonProceed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonProceed.getText() != "Order") {
                    Intent i = new Intent(getApplicationContext(), ConfirmActivity.class);
                    i.putExtra("numTickets", numTickets);
                    startActivityForResult(i, 0);
                }
                else{
                    Toast.makeText(MainActivity.this, "Congrats!", Toast.LENGTH_SHORT).show();
                    resetSpinner();
                }
            }
        });
	}

    //TODO complete OnActivityResult
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            buttonProceed.setText("Order");
            spinnerNumTickets.setEnabled(false);
        }
        else
            resetSpinner();
	}

    public void resetSpinner(){
        buttonProceed.setText(R.string.pString);
        spinnerNumTickets.setEnabled(true);
        numTickets = 1;
        textCost.setText("Total: " + numTickets*9.99);
        spinnerNumTickets.setSelection(0);
    }

}
