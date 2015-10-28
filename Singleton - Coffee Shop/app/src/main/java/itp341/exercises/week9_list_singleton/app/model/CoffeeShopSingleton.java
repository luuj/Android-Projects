package itp341.exercises.week9_list_singleton.app.model;

import android.content.Context;

import java.util.ArrayList;

//TODO complete
public class CoffeeShopSingleton {

    ArrayList<CoffeeShop> mCoffeeShops;

    private static CoffeeShopSingleton sCoffeeShopSingleton;
    private Context mAppContext;

    private CoffeeShopSingleton(Context c){
        mAppContext = c;

        mCoffeeShops = new ArrayList<CoffeeShop>();
        //Dummy code to load array
        for (int i=0; i<6; i++){
            CoffeeShop cs = new CoffeeShop();
            cs.setName("Coffee Shop " + i);
            mCoffeeShops.add(cs);
        }
    }

    public static CoffeeShopSingleton get(Context c){
        if (sCoffeeShopSingleton == null)
            sCoffeeShopSingleton = new CoffeeShopSingleton(c.getApplicationContext());

        return sCoffeeShopSingleton;
    }

    public ArrayList<CoffeeShop> getCoffeeShops(){
        return mCoffeeShops;
    }

    public CoffeeShop getCoffeeShopInstance(int position){
        return mCoffeeShops.get(position);
    }

    public void addCoffeeShop(CoffeeShop cs){
        mCoffeeShops.add(cs);
    }

}
