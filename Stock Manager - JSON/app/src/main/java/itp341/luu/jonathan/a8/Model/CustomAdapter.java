package itp341.luu.jonathan.a8.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import itp341.luu.jonathan.a8.R;

public class CustomAdapter extends ArrayAdapter<Stock>{
    TextView stockName, stockCount;
    ImageView icon;

    public CustomAdapter (Context c, ArrayList<Stock> stocks){
        super(c, 0, stocks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Stock currStock = StockSingleton.get(getContext()).getStock(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_entry, parent, false);

        stockName = (TextView) convertView.findViewById(R.id.stockName);
        stockCount = (TextView) convertView.findViewById(R.id.stockCount);
        icon = (ImageView) convertView.findViewById(R.id.stockIcon);

        stockName.setText(currStock.getName());
        stockCount.setText(currStock.getStockCount().toString());

        switch(currStock.getBrand().toLowerCase()){
            case "apple":
                icon.setImageResource(R.drawable.apple);
                break;
            case "facebook":
                icon.setImageResource(R.drawable.facebook);
                break;
            case "microsoft":
                icon.setImageResource(R.drawable.microsoft);
                break;
            case "samsung":
                icon.setImageResource(R.drawable.samsung);
                break;
            default:
                icon.setImageResource(R.drawable.logo);
                break;
        }
        return convertView;
    }

}
