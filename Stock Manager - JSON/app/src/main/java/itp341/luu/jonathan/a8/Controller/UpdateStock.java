package itp341.luu.jonathan.a8.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import itp341.luu.jonathan.a8.Model.Stock;
import itp341.luu.jonathan.a8.Model.StockSingleton;
import itp341.luu.jonathan.a8.R;

public class UpdateStock extends Activity {

    public static final String POSITIONCLICKED = "position";

    Integer position;
    Stock currStock;

    TextView nameTV, brandTV, priceTV, colorTV, stockTV;
    Button buyButton, sellButton, sellAllButton, deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_entry);

        Intent i = getIntent();
        position = i.getIntExtra(POSITIONCLICKED, -1);

        nameTV = (TextView) findViewById(R.id.productNameUpdate);
        brandTV = (TextView) findViewById(R.id.brandUpdate);
        priceTV = (TextView) findViewById(R.id.priceUpdate);
        colorTV = (TextView) findViewById(R.id.colorUpdate);
        stockTV = (TextView) findViewById(R.id.stockUpdate);

        buyButton = (Button) findViewById(R.id.buyButton);
        sellButton = (Button) findViewById(R.id.sellButton);
        sellAllButton = (Button) findViewById(R.id.sellAllButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        currStock = StockSingleton.get(this).getStock(position);
        setTextViews();

        ButtonListenerUpdate BLU = new ButtonListenerUpdate();
        buyButton.setOnClickListener(BLU);
        sellButton.setOnClickListener(BLU);
        sellAllButton.setOnClickListener(BLU);
        deleteButton.setOnClickListener(BLU);

        if (currStock.getStockCount() == 0)
            disableSell();
    }

    public class ButtonListenerUpdate implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                //Buy button
                case R.id.buyButton:
                    currStock.incrementStock();
                    Toast.makeText(UpdateStock.this, "You bought 1 stock", Toast.LENGTH_SHORT).show();

                    enableSell();
                    updateStockCount();
                    break;

                //Sell Button
                case R.id.sellButton:
                    currStock.decrementStock();
                    Toast.makeText(UpdateStock.this, "You sold 1 stock", Toast.LENGTH_SHORT).show();

                    if (currStock.getStockCount() <= 0)
                        disableSell();

                    updateStockCount();
                    break;

                //Sell all button
                case R.id.sellAllButton:
                    currStock.setStockCount(0);
                    Toast.makeText(UpdateStock.this, "You sold all your stocks", Toast.LENGTH_SHORT).show();

                    disableSell();
                    updateStockCount();
                    break;

                //Delete button
                case R.id.deleteButton:
                    StockSingleton.get(UpdateStock.this).deleteStock(position);
                    setResult(RESULT_OK);
                    finish();
                    break;
            }
            setResult(RESULT_OK);
        }
    }

    public void setTextViews(){
        nameTV.setText(currStock.getName());
        brandTV.setText(currStock.getBrand());
        priceTV.setText(currStock.getPrice());
        colorTV.setText(currStock.getColor().toUpperCase());
        stockTV.setText(currStock.getStockCount().toString());
    }

    public void updateStockCount(){
        stockTV.setText(currStock.getStockCount().toString());
    }

    public void disableSell(){
        sellButton.setEnabled(false);
        sellAllButton.setEnabled(false);
    }

    public void enableSell(){
        sellButton.setEnabled(true);
        sellAllButton.setEnabled(true);
    }

    protected void onPause(){
        super.onPause();
        StockSingleton.get(UpdateStock.this).saveStockList();
    }


}