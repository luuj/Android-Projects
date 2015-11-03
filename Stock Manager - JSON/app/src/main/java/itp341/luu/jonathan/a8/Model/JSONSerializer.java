package itp341.luu.jonathan.a8.Model;


import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import itp341.luu.jonathan.a8.Model.Stock;

public class JSONSerializer {


    private Context mContext;
    private String mFileName;

    public JSONSerializer(Context c, String f){
        mContext = c;
        mFileName = f;
    }

    public void saveStockList(ArrayList<Stock> saveStocks) throws JSONException, IOException {
        JSONArray array = new JSONArray();

        for (Stock c: saveStocks){
            array.put(c.toJSON());
        }

        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public ArrayList<Stock> loadStockList() throws JSONException, IOException {
        ArrayList<Stock> stockList = new ArrayList<Stock>();

        BufferedReader reader = null;
        try {
            InputStream in;

            try{
                in = mContext.openFileInput(mFileName);
            } catch (FileNotFoundException ec) {
                in = mContext.getAssets().open("stocks.json");
            }

            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for (int i = 0; i < array.length(); i++) {
                Stock x = new Stock(array.getJSONObject(i));
                stockList.add(x);
            }
        } catch (FileNotFoundException e) {
            Log.d("FileNotFound", "Failed to find file");
        } finally {
            if (reader != null)
                reader.close();
        }

        return stockList;
    }
}
