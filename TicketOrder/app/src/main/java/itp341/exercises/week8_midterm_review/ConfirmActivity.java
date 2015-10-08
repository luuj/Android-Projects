package itp341.exercises.week8_midterm_review;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmActivity extends Activity {
    public static final String TAG = MainActivity.class.getSimpleName();
    //TODO create Extra Keys

    Button buttonConfirm;
    Button buttonCancel;
    TextView textTicketQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        //need to create the corresponding view in the XML layout
        buttonConfirm = (Button)findViewById(R.id.buttonConfirm);
        buttonCancel = (Button)findViewById(R.id.buttonCancel);
        textTicketQuantity = (TextView)findViewById(R.id.textTicketQuantity);

        // TODO read intent number of tickets
        Intent temp = getIntent();
        Integer num = temp.getIntExtra("numTickets", -1);

        textTicketQuantity.setText(num.toString());

        // TODO create button confirm listener--what does it do?
        buttonConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        //TODO create button cancel listener--what does it do?
        buttonCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

}
