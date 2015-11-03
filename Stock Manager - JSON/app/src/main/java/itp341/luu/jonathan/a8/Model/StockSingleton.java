package itp341.luu.jonathan.a8.Model;


import android.content.Context;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StockSingleton {

    private static final String FILENAME = "stocks.json";

    private ArrayList<Stock> mStockList;
    private static StockSingleton sStockList;
    private Context mAppContext;
    private JSONSerializer mSerializer;
    private Comparator compare;

    private StockSingleton(Context appContext){
        mAppContext = appContext;
        mSerializer = new JSONSerializer(appContext, FILENAME);

        try{
            mStockList = mSerializer.loadStockList();
        }
        catch (IOException e){
            mStockList = new ArrayList<Stock>();
        }
        catch (JSONException e){
            mStockList = new ArrayList<Stock>();
        }

        compare = new Comparator<Stock>(){
            public int compare(Stock s1, Stock s2){
                return s1.getName().toLowerCase().compareTo(s2.getName().toLowerCase());
            }
        };

        sortList();
    }

    public static StockSingleton get(Context c){
        if (sStockList == null)
            sStockList = new StockSingleton(c.getApplicationContext());

        return sStockList;
    }

    public ArrayList<Stock> getmStockList() {
        return mStockList;
    }

    public Stock getStock(int position){
        return mStockList.get(position);
    }

    public void addStock(Stock n){
        mStockList.add(n);
        sortList();
    }

    public void deleteStock(int position){
        mStockList.remove(position);
        sortList();
    }

    public void sortList(){
        Collections.sort(mStockList, compare);
    }

    public void saveStockList(){
        try {
            mSerializer.saveStockList(mStockList);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
