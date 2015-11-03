package itp341.exercises.week9_list_singleton.app;

import itp341.exercises.week9_list_singleton.R;
import itp341.exercises.week9_list_singleton.app.model.CoffeeShop;
import itp341.exercises.week9_list_singleton.app.Controllers.CoffeeShopSingleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddEditCoffeeShopListing extends Activity {

    private static final String TAG = AddEditCoffeeShopListing.class
            .getSimpleName();

    //TODO how will we pass data from ViewAllCoffeeShop activity?
    public static final String EXTRA_COFFEE_SHOP_ARRAY_POSITION = "extra_coffee_shop_array_position";

    EditText editName;
    EditText editAddress;
    EditText editCity;
    Spinner spinnerState;
    EditText editZip;
    EditText editPhone;
    EditText editWebsite;
    Button buttonSaveListing;
    Button buttonDeleteListing;

    int position;

    public static String[] states; // read from arrays.xml for US states
    ArrayAdapter<CharSequence> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_coffee_shop_listing);

        //findViews
        editName = (EditText) findViewById(R.id.edit_name);
        editAddress = (EditText) findViewById(R.id.edit_address);
        editCity = (EditText) findViewById(R.id.edit_city);
        spinnerState = (Spinner) findViewById(R.id.spinner_state); // update
        editZip = (EditText) findViewById(R.id.edit_zip);
        editPhone = (EditText) findViewById(R.id.edit_phone);
        editWebsite = (EditText) findViewById(R.id.edit_website);
        buttonSaveListing = (Button) findViewById(R.id.button_save_listing);
        buttonDeleteListing = (Button) findViewById(R.id.button_delete_listing);

        //load spinner values for States in the coffee shop address
        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.states,
                android.R.layout.simple_spinner_item);
        spinnerState.setAdapter(spinnerAdapter);

        // format phone edit text
        editPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        //button listeners
        buttonDeleteListing.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                deleteAndClose();

            }
        });
        buttonSaveListing.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                saveAndClose();

            }
        });

        //TODO check if this is an existing or new listing
        Intent i = getIntent();
        position = i.getIntExtra(EXTRA_COFFEE_SHOP_ARRAY_POSITION, -1);

        if (position != -1) { //this means we are editing an existing listing
            CoffeeShop coffeeShop = CoffeeShopSingleton.get(this).getCoffeShop(position);
            if (coffeeShop != null) { // this means we are editing old record
                loadData(coffeeShop);

            }
        } else {//else means this should be a new (blank) entry
            buttonDeleteListing.setEnabled(false); //shouldn't be able to "delete" new entry
        }
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        // Called after onStart() as Activity comes to foreground.
        Log.d(TAG, "onResume");
        super.onPause();
        CoffeeShopSingleton.get(this).saveCoffeeShops();
    }

    //TODO load data from existing coffee shop object
    private void loadData(CoffeeShop cs) {
        editName.setText(cs.getName());
        editAddress.setText(cs.getAddress());
        editCity.setText(cs.getCity());

        editZip.setText(cs.getZip());
        editPhone.setText(cs.getPhone());
        editWebsite.setText(cs.getWebsite());

        // find spinner value
        int pos = spinnerAdapter.getPosition(cs.getState());
        spinnerState.setSelection(pos);
    }

    //TODO Listing should be saved (updated if existing, or added if new)
    private void saveAndClose() {
        Log.d(TAG, "saveAndClose");
        CoffeeShop coffeeShop = new CoffeeShop();


        if (buttonSaveListing != null) {
            coffeeShop.setName(editName.getText().toString());
            coffeeShop.setAddress(editAddress.getText().toString());
            coffeeShop.setCity(editCity.getText().toString());
            coffeeShop.setZip(editZip.getText().toString());
            coffeeShop.setName(editName.getText().toString());
            coffeeShop.setPhone(editPhone.getText().toString());
            coffeeShop.setWebsite(editWebsite.getText().toString());


            TextView tv = (TextView) spinnerState.getSelectedView();
            if (tv != null) {
                coffeeShop.setState(tv.getText().toString());
            }

            if (position != -1) {    //this was an existing list we should update
                CoffeeShopSingleton.get(this).updateCoffeeShop(position, coffeeShop);
            } else {  //this is a new list we should add
                CoffeeShopSingleton.get(this).addCoffeeShop(coffeeShop);

            }

            setResult(RESULT_OK);
            finish();

        }
    }

    //TODO Listing should be deleted (only it was an existing entry, not if it was new))
    private void deleteAndClose() {
        Log.d(TAG, "onClick");

        if (position != -1) {    //this was an existing list we should update
            CoffeeShopSingleton.get(this).removeCoffeeShop(position);
        }
        //else --there is nothing to delete because this was a new entry       }

        setResult(RESULT_OK);
        finish();
    }

}
