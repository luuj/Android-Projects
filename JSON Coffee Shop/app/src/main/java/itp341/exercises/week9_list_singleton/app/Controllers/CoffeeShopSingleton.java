package itp341.exercises.week9_list_singleton.app.Controllers;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import itp341.exercises.week9_list_singleton.app.model.CoffeeShop;

public class CoffeeShopSingleton {
    private static final String TAG = CoffeeShopSingleton.class.getSimpleName();

    //TODO JSON - filename
    private static final String FILENAME = "coffee_shops.json";

    //TODO JSON - add serializer
    private CoffeeShopJSONSerializer mSerializer;

    private ArrayList<CoffeeShop> mCoffeeShops;

    private static CoffeeShopSingleton sCoffeeShops;
    private Context mAppContext;

    //TODO JSON --revise
    private CoffeeShopSingleton(Context appContext) {
        mAppContext = appContext;

        mSerializer = new CoffeeShopJSONSerializer(appContext, FILENAME);

        try {
            mCoffeeShops = mSerializer.loadCoffeeShops();
        } catch (IOException e) {
            mCoffeeShops = new ArrayList<>();
        } catch (JSONException e) {
            mCoffeeShops = new ArrayList<>();
        }

    }

    public static CoffeeShopSingleton get(Context c) {
        if (sCoffeeShops == null) {
            sCoffeeShops = new CoffeeShopSingleton(c.getApplicationContext());
        }
        return sCoffeeShops;
    }

    public void loadDummyData() {
        for (int i = 0; i < 10; i++) {
            CoffeeShop cs = new CoffeeShop();
            cs.setName("Coffee Shop #" + i);
            mCoffeeShops.add(cs);
        }
    }

    public ArrayList<CoffeeShop> getCoffeeShops() {
        return mCoffeeShops;
    }

    public CoffeeShop getCoffeShop(int index) {
        return mCoffeeShops.get(index);
    }

    public void addCoffeeShop(CoffeeShop cs) {
        mCoffeeShops.add(cs);
    }

    public void removeCoffeeShop(int index) {
        if (index >= 0 && index < mCoffeeShops.size())
            mCoffeeShops.remove(index);
    }

    public void updateCoffeeShop(int index, CoffeeShop cs) {
        if (index >= 0 && index < mCoffeeShops.size())
            mCoffeeShops.set(index, cs);
    }

    //TODO JSON -- saving
    public boolean saveCoffeeShops (){
        try {
            mSerializer.saveCoffeeShops(mCoffeeShops);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean loadCoffeeShops(){
        try {
            mSerializer.loadCoffeeShops();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
