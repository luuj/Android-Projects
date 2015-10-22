package itp341.luu.jonathan.a6;

import android.util.Log;

import java.io.Serializable;

public class CoffeeOrder implements Serializable{

    private String drinkType, size, milkType, brewType, sweetType;
    private Boolean iced;

    public CoffeeOrder(){
        drinkType = "None";
        size = null;
        milkType = null;
        brewType = "None";
        iced = null;
        sweetType = "None";
    }

    public void setSweetType(String sweetType) {
        this.sweetType = sweetType;
    }

    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setMilkType(String milkType) {
        this.milkType = milkType;
    }

    public void setBrewType(String brewType) {
        this.brewType = brewType;
    }

    public void setIced(Boolean iced) {
        this.iced = iced;
    }

    public String getDrinkType() {
        return drinkType;
    }

    public String getSize() {
        return size;
    }

    public String getMilkType() {
        return milkType;
    }

    public String getBrewType() {
        return brewType;
    }

    public String getSweetType() {
        return sweetType;
    }

    public String getIced() {
        if (iced)
            return "Yes";
        else
            return "No";
    }

    public Boolean completedForm(){
        if (drinkType.equals("None") || brewType.equals("None")) //None for sweetener is okay
            return false;
        if (size == null || iced == null || milkType == null)
            return false;

        return true;
    }

    public void clearAll(){
        drinkType = "None";
        size = null;
        milkType = null;
        brewType = "None";
        iced = null;
        sweetType = "None";
    }

    public void printAll(){
        Log.d("Tag", "Drink Type: "  + drinkType + "\nSweetener: " + sweetType +
            "\n Brew Type: " + brewType + "\nSize: " + size + "\nIced: " + iced +
            "\nMilk: " + milkType);
    }
}
