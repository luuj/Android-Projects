package itp341.luu.jonathan.a5luujonathan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class DetailActivity extends Activity {

    public static final String saveDF = "itp341.saveDetailedFragment";
    DetailFragment dF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Send data up to the detailFragment
        if (savedInstanceState == null)
            dF = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
        else
            dF = (DetailFragment) getFragmentManager().getFragment(savedInstanceState, saveDF);

        dF.updateFields(getIntent());
    }

    //Account for screen rotation
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        getFragmentManager().putFragment(savedInstanceState, saveDF, dF);
    }
}
