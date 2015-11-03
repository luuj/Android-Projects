package itp341.luu.jonathan.a8.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import itp341.luu.jonathan.a8.Model.CustomAdapter;
import itp341.luu.jonathan.a8.Model.Stock;
import itp341.luu.jonathan.a8.Model.StockSingleton;
import itp341.luu.jonathan.a8.R;

public class MainActivity extends Activity {

    ListView stockListView;
    Button addButton;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Stock> allStocks = StockSingleton.get(getApplicationContext()).getmStockList();
        adapter = new CustomAdapter(this, allStocks);

        stockListView = (ListView) findViewById(R.id.stockListView);
        addButton = (Button) findViewById(R.id.addButton);

        stockListView.setAdapter(adapter);

        //Button Listener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NewStock.class);
                startActivityForResult(i, 0);
            }
        });

        //ListView listener
        stockListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), UpdateStock.class);
                intent.putExtra(UpdateStock.POSITIONCLICKED, position);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //If the user saved or deleted a note, do something
        if (resultCode == RESULT_OK){
            adapter.notifyDataSetChanged();
        }
    }
}
