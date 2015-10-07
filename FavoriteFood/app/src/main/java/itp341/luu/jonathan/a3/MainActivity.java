package itp341.luu.jonathan.a3;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    ImageView American, Chinese, Indian, Portuguese, MiddleEast, Italian;
    Integer aCount, cCount, inCount, pCount, mCount, itCount;
    ImageListener image;
    ImageView previousImage;

    public static final String AmericanC = "itp341.luu.jonathan.a3.AmericanCount";
    public static final String ChineseC = "itp341.luu.jonathan.a3.ChineseCount";
    public static final String IndianC = "itp341.luu.jonathan.a3.IndianCount";
    public static final String PortugueseC = "itp341.luu.jonathan.a3.PortugueseCount";
    public static final String MiddleEastC = "itp341.luu.jonathan.a3.MiddleEastCount";
    public static final String ItalianC = "itp341.luu.jonathan.a3.ItalianCount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Determine which layout to use - Landscape or portrait
        if (getResources().getConfiguration().orientation == 2)
            setContentView(R.layout.landscapelayout);
        else
            setContentView(R.layout.activity_main);

        //Initialize the toast counters
        if (savedInstanceState == null){
            aCount = 0;
            cCount = 0;
            inCount = 0;
            pCount = 0;
            mCount = 0;
            itCount = 0;
        }

        //Retrieving image references
        American = (ImageView) findViewById(R.id.americanIV);
        Chinese = (ImageView) findViewById(R.id.chineseIV);
        Indian = (ImageView) findViewById(R.id.indianIV);
        Italian = (ImageView) findViewById(R.id.italianIV);
        MiddleEast = (ImageView) findViewById(R.id.middleeastIV);
        Portuguese = (ImageView) findViewById(R.id.portugueseIV);

        //Attaching listeners to each image
        image = new ImageListener();
        American.setOnClickListener(image);
        Chinese.setOnClickListener(image);
        Indian.setOnClickListener(image);
        Italian.setOnClickListener(image);
        MiddleEast.setOnClickListener(image);
        Portuguese.setOnClickListener(image);

    }
    public class ImageListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            int currentID = v.getId();

            //Determine which image was pressed and switch image to "pressed"
            if (currentID == R.id.americanIV) {
                aCount++;
                String combinedString = "You liked American food " + aCount.toString() + " time(s)";
                Toast.makeText(MainActivity.this, combinedString, Toast.LENGTH_SHORT).show();

                switchImage(previousImage, American);
                previousImage = American;
            }
            else if (currentID == R.id.chineseIV){
                cCount++;
                String combinedString = "You liked Chinese food " + cCount.toString() + " time(s)";
                Toast.makeText(MainActivity.this, combinedString, Toast.LENGTH_SHORT).show();

                switchImage(previousImage, Chinese);
                previousImage = Chinese;
            }
            else if (currentID == R.id.indianIV){
                inCount++;
                String combinedString = "You liked Indian food " + inCount.toString() + " time(s)";
                Toast.makeText(MainActivity.this, combinedString, Toast.LENGTH_SHORT).show();

                switchImage(previousImage, Indian);
                previousImage = Indian;
            }
            else if (currentID == R.id.italianIV){
                itCount++;
                String combinedString = "You liked Italian food " + itCount.toString() + " time(s)";
                Toast.makeText(MainActivity.this, combinedString, Toast.LENGTH_SHORT).show();

                switchImage(previousImage, Italian);
                previousImage = Italian;
            }
            else if (currentID == R.id.middleeastIV){
                mCount++;
                String combinedString = "You liked Middle Eastern food " + mCount.toString() + " time(s)";
                Toast.makeText(MainActivity.this, combinedString, Toast.LENGTH_SHORT).show();

                switchImage(previousImage, MiddleEast);
                previousImage = MiddleEast;
            }
            else if (currentID == R.id.portugueseIV){
                pCount++;
                String combinedString = "You liked Portuguese food " + pCount.toString() + " time(s)";
                Toast.makeText(MainActivity.this, combinedString, Toast.LENGTH_SHORT).show();

                switchImage(previousImage, Portuguese);
                previousImage = Portuguese;
            }
            else{
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Functions to save data upon activity destruction
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt(AmericanC, aCount);
        savedInstanceState.putInt(ChineseC, cCount);
        savedInstanceState.putInt(ItalianC, itCount);
        savedInstanceState.putInt(IndianC, inCount);
        savedInstanceState.putInt(PortugueseC, pCount);
        savedInstanceState.putInt(MiddleEastC, mCount);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState (Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        aCount = savedInstanceState.getInt(AmericanC, 0);
        cCount = savedInstanceState.getInt(ChineseC, 0);
        inCount = savedInstanceState.getInt(IndianC, 0);
        itCount = savedInstanceState.getInt(ItalianC, 0);
        mCount = savedInstanceState.getInt(MiddleEastC, 0);
        pCount = savedInstanceState.getInt(PortugueseC, 0);
    }

    //Function to switch between clicked and non-clicked images
    public void switchImage(ImageView switchBack, ImageView switchCurrent){

        //Check if an item was previously selected
        if (switchBack != null) {
            switch (switchBack.getId()) {
                case R.id.americanIV:
                    American.setImageResource(R.drawable.american);
                    break;
                case R.id.chineseIV:
                    Chinese.setImageResource(R.drawable.chinese);
                    break;
                case R.id.indianIV:
                    Indian.setImageResource(R.drawable.indian);
                    break;
                case R.id.italianIV:
                    Italian.setImageResource(R.drawable.italian);
                    break;
                case R.id.portugueseIV:
                    Portuguese.setImageResource(R.drawable.portuguese);
                    break;
                case R.id.middleeastIV:
                    MiddleEast.setImageResource(R.drawable.middle_east);
                    break;
            }
        }

        switch (switchCurrent.getId()){
            case R.id.americanIV:
                American.setImageResource(R.drawable.american_clicked);
                break;
            case R.id.chineseIV:
                Chinese.setImageResource(R.drawable.chinese_clicked);
                break;
            case R.id.indianIV:
                Indian.setImageResource(R.drawable.indian_clicked);
                break;
            case R.id.italianIV:
                Italian.setImageResource(R.drawable.italian_clicked);
                break;
            case R.id.portugueseIV:
                Portuguese.setImageResource(R.drawable.portuguese_clicked);
                break;
            case R.id.middleeastIV:
                MiddleEast.setImageResource(R.drawable.middle_east_clicked);
                break;
        }
    }

}
