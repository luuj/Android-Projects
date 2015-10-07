package itp341.luu.jonathan.a2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    //UI Buttons and ButtonListener
    Button Android, IOS;
    ButtonListener btnListener;

    Integer AndroidCount, IOSCount;

    public static final String AndroidString = "itp341.luu.jonathan.a2.AndroidCount";
    public static final String iOSString = "itp341.luu.jonathan.a2.iOSCount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the toast counters
        if (savedInstanceState == null){
            AndroidCount = 0;
            IOSCount = 0;
        }

        //Associate buttons with UI and add listeners
        Android = (Button) findViewById(R.id.btnAndroid);
        IOS = (Button) findViewById(R.id.btniOS);

        btnListener = new ButtonListener();
        Android.setOnClickListener(btnListener);
        IOS.setOnClickListener(btnListener);
    }

    public class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //Check if the clicked button was either the Android button or iOS button.
            //If android, show Android toast. Else, show iOS toast
            if (v.getId() == R.id.btnAndroid){
                AndroidCount++;
                String combinedString = getResources().getString(R.string.toast_android) + " (" + AndroidCount.toString() + ")";
                Toast.makeText(MainActivity.this, combinedString, Toast.LENGTH_SHORT).show();
            }
            else if (v.getId() == R.id.btniOS){
                IOSCount++;
                String combinedString = getResources().getString(R.string.toast_ios) + " (" + IOSCount.toString() + ")";
                Toast.makeText(MainActivity.this, combinedString, Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(MainActivity.this, R.string.error_text, Toast.LENGTH_SHORT).show();
        }
    }

    //Saved State overrides to save data upon rotation
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt(AndroidString, AndroidCount);
        savedInstanceState.putInt(iOSString, IOSCount);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        AndroidCount = savedInstanceState.getInt(AndroidString, 0);
        IOSCount = savedInstanceState.getInt(iOSString, 0);
    }

}
