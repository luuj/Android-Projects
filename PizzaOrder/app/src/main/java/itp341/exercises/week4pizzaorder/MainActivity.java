package itp341.exercises.week4pizzaorder;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends Activity {

	// TAG for logging
	private static final String TAG = MainActivity.class.getSimpleName();

	// variables for widgets
	CheckBox checkPepperoni;
	CheckBox checkPineapple;
	CheckBox checkTofu;
	RadioGroup radioGroupSize;
	SeekBar seekBarNumPizzas;
	TextView textNumPizzasSeekBarProgress;
	Spinner spinnerSpecials;
	TextView textOrderDisplay;
	EditText editName;

    //Listeners
	OnEditorActionListener textListen;
    CompoundButton.OnCheckedChangeListener checkListen;
    RadioGroup.OnCheckedChangeListener radioListen;
    SeekBar.OnSeekBarChangeListener seekListen;
    OnItemSelectedListener spinnerListen;

	//TODO instance variables
	boolean wantsPepperoni = false;
	boolean wantsPineapple = false;
	boolean wantsTofu = false;
	String size = "";
	String specials = "none";
	Integer numPizzas = 1;
	String name = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get references to widgets
        textOrderDisplay = (TextView) findViewById(R.id.textOrderDisplay);
        checkPepperoni = (CheckBox) findViewById(R.id.checkPepperoni);
        checkPineapple = (CheckBox) findViewById(R.id.checkPineapple);
        checkTofu = (CheckBox) findViewById(R.id.checkTofu);
        radioGroupSize = (RadioGroup) findViewById(R.id.radioGroupSize);
        textNumPizzasSeekBarProgress = (TextView) findViewById(R.id.textNumPizzasSeekBarProgress);
        seekBarNumPizzas = (SeekBar) findViewById(R.id.seekBarNumPizzas);
        spinnerSpecials = (Spinner) findViewById(R.id.spinnerSpecials);
        editName = (EditText) findViewById(R.id.editName);

        //TODO create EditorActionListener
		textListen = new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				name = v.getText().toString();
                displayPizzaOrder();
				return true;
			}
		};
        editName.setOnEditorActionListener(textListen);

        //TODO create checkbox listeners
        checkListen = new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    if (buttonView.getId() == R.id.checkPepperoni)
                        wantsPepperoni = true;
                    else if (buttonView.getId() == R.id.checkPineapple)
                        wantsPineapple = true;
                    else if (buttonView.getId() == R.id.checkTofu)
                        wantsTofu = true;
                    displayPizzaOrder();
                }
                else{
                    if (buttonView.getId() == R.id.checkPepperoni)
                        wantsPepperoni = false;
                    else if (buttonView.getId() == R.id.checkPineapple)
                        wantsPineapple = false;
                    else if (buttonView.getId() == R.id.checkTofu)
                        wantsTofu = false;
                    displayPizzaOrder();
                }
            }
        };
        checkPepperoni.setOnCheckedChangeListener(checkListen);
        checkPineapple.setOnCheckedChangeListener(checkListen);
        checkTofu.setOnCheckedChangeListener(checkListen);

        //TODO create radiogroup listener
        radioListen = new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioSmall)
                    size = "Small";
                else if (checkedId == R.id.radioMedium)
                    size = "Medium";
                else if (checkedId == R.id.radioLarge)
                    size = "Large";

                displayPizzaOrder();
            }
        };
        radioGroupSize.setOnCheckedChangeListener(radioListen);

        //TODO seekbar listener
        seekListen = new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numPizzas = progress+1;
                textNumPizzasSeekBarProgress.setText(numPizzas.toString());
                displayPizzaOrder();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        };
        seekBarNumPizzas.setOnSeekBarChangeListener(seekListen);

        //TODO create spinner listener
        spinnerListen = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] tempArray = getResources().getStringArray(R.array.label_array_specials);

                if (position == 1)
                    specials = tempArray[1];
                else if (position == 2)
                    specials = tempArray[2];
                else if (position == 0)
                    specials = tempArray[0];

                displayPizzaOrder();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };
        spinnerSpecials.setOnItemSelectedListener(spinnerListen);
    }
	//TODO generate "human-readable" description of pizza and write to textView
	public void displayPizzaOrder() {
        String msg = "";

        if (name != "")
            msg = "Name: " + name;
        else
            msg = "Name: ";

        if (wantsPepperoni==true || wantsTofu==true || wantsPineapple==true){
            msg = msg + "\nToppings: ";
            if (wantsPepperoni)
                msg = msg + "Pepperoni, ";
            if (wantsPineapple)
                msg = msg + "Pineapples, ";
            if (wantsTofu)
                msg = msg + "Tofu, ";
            msg = msg.substring(0, msg.length()-2);
        }
        else
            msg = msg + "\nToppings: None";

        if (size != "")
            msg = msg + "\nSize: " + size;
        else
            msg = msg + "\nSize: Not Selected";

        msg = msg + "\nSpecials Chosen: " + specials;
        msg = msg + "\nNumber of Pizzas: " + numPizzas;

		textOrderDisplay.setText(msg);

	}

	//TODO determine what qualifies as a valid order
	public boolean isOrderValid() {
		Log.d(TAG, name);

		boolean validOrder = false;

		if (!editName.getText().toString().equals("") && radioGroupSize.getCheckedRadioButtonId() != -1) {
			validOrder = true;
		}
		return validOrder;
	}

}
