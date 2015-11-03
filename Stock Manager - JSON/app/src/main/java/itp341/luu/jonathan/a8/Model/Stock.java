package itp341.luu.jonathan.a8.Model;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Stock {

    private static final String JSON_NAME = "name";
    private static final String JSON_BRAND = "brand";
    private static final String JSON_COLOR = "color";
    private static final String JSON_PRICE = "price";
    private static final String JSON_COUNT = "stock";
    private static final String JSON_ID = "id";

    String name, brand, color, price;
    Integer stockCount, id;

    public Stock (){
        super();
    }

    //Constructor for JSON
    public Stock(JSONObject json) throws JSONException{
        name = json.getString(JSON_NAME);
        brand = json.getString(JSON_BRAND);
        color = json.getString(JSON_COLOR);
        price = json.getString(JSON_PRICE);
        stockCount = json.getInt(JSON_COUNT);
        id = json.getInt(JSON_ID);
    }

    //Convert the Stock object to JSON format
    public JSONObject toJSON() throws JSONException{
        JSONObject json = new JSONObject();

        json.put(JSON_NAME, name);
        json.put(JSON_ID, id);
        json.put(JSON_PRICE, price);
        json.put(JSON_COUNT, stockCount);
        json.put(JSON_COLOR, color);
        json.put(JSON_BRAND, brand);

        return json;
    }

    public String toString(){
        String stockInfo = "Name: " + name
                + "\nBrand: " + brand
                + "\nColor: " + color
                + "\nPrice: " + price.toString()
                + "\nCount: " + stockCount.toString()
                + "\nID: " + id.toString();

        return stockInfo;
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getPrice() {
        return price;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void incrementStock(){ stockCount++; }

    public void decrementStock(){ stockCount--; }
}
