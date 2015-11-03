package itp341.luu.jonathan.a8.Controller;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import itp341.luu.jonathan.a8.Model.Stock;
import itp341.luu.jonathan.a8.Model.StockSingleton;
import itp341.luu.jonathan.a8.R;

public class NewStock extends Activity {

    Button saveButton, cancelButton;
    EditText nameTV, brandTV, priceTV, colorTV, stockTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_entry);

        saveButton = (Button) findViewById(R.id.saveButtonNew);
        cancelButton = (Button) findViewById(R.id.cancelButtonNew);

        nameTV = (EditText) findViewById(R.id.productTV);
        brandTV = (EditText) findViewById(R.id.brandTV);
        priceTV = (EditText) findViewById(R.id.priceTV);
        colorTV = (EditText) findViewById(R.id.colorTV);
        stockTV = (EditText) findViewById(R.id.stockTV);

        ButtonListener BL = new ButtonListener();
        saveButton.setOnClickListener(BL);
        cancelButton.setOnClickListener(BL);
    }

    public class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            //If the user presses save
            if (view.getId() == saveButton.getId()){
                Stock newAddition = new Stock();

                newAddition.setName(nameTV.getText().toString());
                newAddition.setBrand(brandTV.getText().toString());
                newAddition.setColor(colorTV.getText().toString());
                newAddition.setId(0);

                //Check for errors in input
                try {
                    Integer tempCount = Integer.parseInt(stockTV.getText().toString());
                    newAddition.setStockCount(tempCount);
                    Integer tempPrice = Integer.parseInt(priceTV.getText().toString());
                    newAddition.setPrice("$" + tempPrice.toString());
                }catch (Exception e){
                    Toast.makeText(NewStock.this, "Please enter in a valid integer stock count and price", Toast.LENGTH_SHORT).show();
                    return;
                }

                StockSingleton.get(NewStock.this).addStock(newAddition);
                StockSingleton.get(NewStock.this).saveStockList();

                setResult(RESULT_OK);
                finish();
            }
            //If the user presses cancel
            else if (view.getId() == cancelButton.getId()){
                setResult(RESULT_CANCELED);
                finish();
            }
        }
    }
}