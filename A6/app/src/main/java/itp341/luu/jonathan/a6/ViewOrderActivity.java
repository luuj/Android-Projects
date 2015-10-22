package itp341.luu.jonathan.a6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewOrderActivity extends Activity {

    TextView drink, brew, sweet, size, iced, milk;
    Button confirm, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        drink = (TextView) findViewById(R.id.drinkSummary);
        brew = (TextView) findViewById(R.id.brewSummary);
        sweet = (TextView) findViewById(R.id.sweetSummary);
        size = (TextView) findViewById(R.id.sizeSummary);
        iced = (TextView) findViewById(R.id.icedSummary);
        milk = (TextView) findViewById(R.id.milkSummary);
        confirm = (Button) findViewById(R.id.confirmButton);
        cancel = (Button) findViewById(R.id.cancelButton);

        Intent i = getIntent();
        CoffeeOrder transferOrder = (CoffeeOrder) i.getSerializableExtra(MainActivity.passObject);

        drink.setText(transferOrder.getDrinkType());
        brew.setText(transferOrder.getBrewType());
        sweet.setText(transferOrder.getSweetType());
        size.setText(transferOrder.getSize());
        iced.setText(transferOrder.getIced());
        milk.setText(transferOrder.getMilkType());

        BListener listener = new BListener();
        confirm.setOnClickListener(listener);
        cancel.setOnClickListener(listener);
    }

    public class BListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (v.getId()==confirm.getId()){
                setResult(Activity.RESULT_OK);
                finish();
            }
            else if (v.getId()==cancel.getId())
                finish();
        }
    }
}
