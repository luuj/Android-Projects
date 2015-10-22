package itp341.luu.jonathan.a6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static final String passObject = "itp.341.luu.jonathan.a6.ViewOrderActivity";

    Spinner drinkType, brewType, sweetType;
    RadioGroup size, iced, milk;
    TextView descriptionText;
    CoffeeOrder coffeeInstance;
    String [] descriptionArray;
    Button load, save, order, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drinkType = (Spinner) findViewById(R.id.drinkSpinner);
        brewType = (Spinner) findViewById(R.id.brewSpinner);
        sweetType = (Spinner) findViewById(R.id.sweetSpinner);
        size = (RadioGroup) findViewById(R.id.sizeRadio);
        iced = (RadioGroup) findViewById(R.id.icedRadio);
        milk = (RadioGroup) findViewById(R.id.milkRadio);
        descriptionText = (TextView) findViewById(R.id.descriptionText);
        load = (Button) findViewById(R.id.loadButton);
        save = (Button) findViewById(R.id.saveButton);
        order = (Button) findViewById(R.id.orderButton);
        clear = (Button) findViewById(R.id.clearButton);

        coffeeInstance = new CoffeeOrder();
        descriptionArray = getResources().getStringArray(R.array.description_type_array);

        SpinnerListener SL = new SpinnerListener();
        drinkType.setOnItemSelectedListener(SL);
        brewType.setOnItemSelectedListener(SL);
        sweetType.setOnItemSelectedListener(SL);

        RadioListener RL = new RadioListener();
        size.setOnCheckedChangeListener(RL);
        iced.setOnCheckedChangeListener(RL);
        milk.setOnCheckedChangeListener(RL);

        ButtonListener BL = new ButtonListener();
        load.setOnClickListener(BL);
        save.setOnClickListener(BL);
        order.setOnClickListener(BL);
        clear.setOnClickListener(BL);
    }

    //Listener for both spinners
    public class SpinnerListener implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (parent.getId() == drinkType.getId())
                coffeeInstance.setDrinkType(drinkType.getSelectedItem().toString());
            else if (parent.getId() == brewType.getId()) {
                coffeeInstance.setBrewType(brewType.getSelectedItem().toString());
                descriptionText.setText(descriptionArray[position]);
            }
            else if (parent.getId() == sweetType.getId())
                coffeeInstance.setSweetType(sweetType.getSelectedItem().toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent){}
    }

    public class RadioListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch(checkedId){
                //Size Options
                case (R.id.smallRB):
                    coffeeInstance.setSize("Small");
                    break;
                case (R.id.mediumRB):
                    coffeeInstance.setSize("Medium");
                    break;
                case (R.id.largeRB):
                    coffeeInstance.setSize("Large");
                    break;
                //Iced Options
                case (R.id.yesRB):
                    coffeeInstance.setIced(true);
                    break;
                case (R.id.noRB):
                    coffeeInstance.setIced(false);
                    break;
                //Milk Options
                case(R.id.noneRB):
                    coffeeInstance.setMilkType("None");
                    break;
                case(R.id.normalRB):
                    coffeeInstance.setMilkType("Whole");
                    break;
                case(R.id.soyRB):
                    coffeeInstance.setMilkType("Skim");
                    break;
            }
        }
    }

    public class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //Clear Button
            if (v.getId() == clear.getId()){
                clearAll();
            }
            //Order Button
            if(v.getId() == order.getId()){
                //Check if everything is filled out
                if (coffeeInstance.completedForm()==false) {
                    Toast toast = Toast.makeText(MainActivity.this, "Please fill out the required fields", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.NO_GRAVITY,0,435);
                    toast.show();
                    return;
                }

                //Start new order summary activity
                Intent i = new Intent(getApplicationContext(), ViewOrderActivity.class);
                i.putExtra(passObject, coffeeInstance);
                startActivityForResult(i, 0);
            }
        }
    }

    public void clearAll(){
        coffeeInstance.clearAll();
        drinkType.setSelection(0);
        brewType.setSelection(0);
        sweetType.setSelection(0);
        descriptionText.setText(descriptionArray[0]);
        size.clearCheck();
        iced.clearCheck();
        milk.clearCheck();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity.RESULT_OK){
            clearAll();
            Toast toast = Toast.makeText(MainActivity.this, "Thank you for your order", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.NO_GRAVITY,0,435);
            toast.show();
        }
        else {
            Toast toast = Toast.makeText(MainActivity.this, "Please try again", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.NO_GRAVITY,0,435);
            toast.show();
        }
    }

    //TODO: Shared preferences and rotation
}
