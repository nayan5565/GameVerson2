package com.example.nayan.gameverson2;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nayan.gameverson2.model.MContents;
import com.example.nayan.gameverson2.model.MLevel;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.utils.DialogSoundOnOff;
import com.example.nayan.gameverson2.utils.MyDatabase;
import com.example.nayan.gameverson2.utils.Utils;

import java.util.ArrayList;

/**
 * Created by NAYAN on 11/24/2016.
 */
public class SubLevelActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String IMAGE_URL = "http://www.radhooni.com/content/match_game/v1/images/";
    private static int value;
    private static SubLevelAdapter subLevelAdapter;
    public static ArrayList<MSubLevel> mSubLevels;
    private static ArrayList<MContents> contentses;
    private static ArrayList<MLevel> mLevels;
    private static MSubLevel mSubLevel = new MSubLevel();
    private static MLevel mLevel = new MLevel();
    private MyDatabase database;
    private RecyclerView recyclerView;
    private TextView textView;
    private String lName;
    private int STORAGE_PERMISSION_CODE = 23;
    private Button back;
    public static int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_level_activity);

        lName = getIntent().getStringExtra("name");
        value = getIntent().getIntExtra("id", 0);
        Log.e("log", "is" + value);

        init();
        prepareDisplay();
        getLocalData();
    }

    private void requestStoragePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                downloadAssets();
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            finish();

        } else if (id == R.id.action_settings) {
            DialogSoundOnOff.dialogShow(this);
            return true;
        }

        //id unused
        return super.onOptionsItemSelected(item);
    }

    private void getLocalData() {
        mSubLevels = database.getSubLevelData(value);
        mLevels = database.getLevelData();
        Log.e("getDb", "sublevel : " + mSubLevels.size());
        mSubLevels.get(0).setUnlockNextLevel(1);
        subLevelAdapter.setData(mSubLevels);


    }

    @Override
    protected void onRestart() {
        getLocalData();
        super.onRestart();
    }

    private void init() {
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);
        Utils.levels = new ArrayList<>();
        mLevels = new ArrayList<>();
        database = new MyDatabase(this);
        textView = (TextView) findViewById(R.id.txtPName);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        subLevelAdapter = new SubLevelAdapter(this);


    }

    private void prepareDisplay() {
        int item= Utils.getScreenSize(this,80);
        textView.setText(lName);
        recyclerView.setLayoutManager(new GridLayoutManager(this, item));
        recyclerView.setAdapter(subLevelAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.back){
            finish();
        }
    }
}
