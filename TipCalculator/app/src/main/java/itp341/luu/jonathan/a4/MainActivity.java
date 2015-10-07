package itp341.luu.jonathan.a4;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends Activity {

    EditText billAmount;
    TextView percentText, tipText, totalText, perPersonText;
    SeekBar percentBar;
    Spinner splitSpinner;
    LinearLayout perPersonLay;

    Double billDouble = 0.00;
    Integer tipInt = 0;
    Integer currentPosition = 0;

    public static final String billSave = "Bill Amount";
    public static final String tipSave = "Tip Amount";
    public static final String positionSave = "Position Num";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Retrieve items from UI
        billAmount = (EditText) findViewById(R.id.billText);
        percentText = (TextView) findViewById(R.id.percentTV);
        tipText = (TextView) findViewById(R.id.tipTV);
        totalText = (TextView) findViewById(R.id.totalTV);
        perPersonText = (TextView) findViewById(R.id.perPersonTV);
        percentBar = (SeekBar) findViewById(R.id.percentSB);
        splitSpinner = (Spinner) findViewById(R.id.splitSpin);
        perPersonLay = (LinearLayout) findViewById(R.id.perPersonLinear);

        perPersonLay.setVisibility(View.GONE);

        //Setting anonymous listeners
        billAmount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //Retrieve bill amount from user and test for valid input
                try {
                    billDouble = Double.parseDouble(v.getText().toString());
                }
                catch(NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Please enter in a valid number", Toast.LENGTH_SHORT).show();
                    return true;
                }

                getValues();
                return true;
            }
        });

        percentBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Get seekbar tip amount
                tipInt = progress;
                percentText.setText(tipInt.toString() + "%");
                getValues();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        splitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Choose whether to hide the per person widget or not
                if (position == 0)
                    perPersonLay.setVisibility(View.GONE);
                else if (position == 1 || position == 2 || position == 3) {
                    perPersonLay.setVisibility(View.VISIBLE);
                    currentPosition = position;
                }
                getValues();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }

    private void getValues (){
        double tempTip = billDouble * ((double)tipInt/100);

        //Calculate tip amount
        DecimalFormat df = new DecimalFormat("#0.00");
        tipText.setText("$" + df.format(tempTip));

        //Calculate total amount
        double tempTotal = billDouble + tempTip;
        totalText.setText("$" + df.format(tempTotal));

        //Calculate per person amount
        double tempPerPerson = tempTotal/(currentPosition+1);
        perPersonText.setText("$" + df.format(tempPerPerson));
    }


    //Saved State overrides to save data upon rotation
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putDouble(billSave, billDouble);
        savedInstanceState.putInt(tipSave, tipInt);
        savedInstanceState.putInt(positionSave, currentPosition);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        billDouble = savedInstanceState.getDouble(billSave, 0);
        tipInt = savedInstanceState.getInt(tipSave, 0);
        currentPosition = savedInstanceState.getInt(positionSave, 0);
        getValues();
    }


}
