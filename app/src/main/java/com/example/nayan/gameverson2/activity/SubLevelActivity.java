package com.example.nayan.gameverson2.activity;

import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.adapter.SubLevelAdapter;
import com.example.nayan.gameverson2.model.MLevel;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.utils.DatabaseHelper;
import com.example.nayan.gameverson2.utils.DialogSoundOnOff;
import com.example.nayan.gameverson2.utils.Global;
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
    private static ArrayList<MLevel> mLevels;
    private static MSubLevel mSubLevel = new MSubLevel();
    private static MLevel mLevel = new MLevel();
    private DatabaseHelper database;
    private RecyclerView recyclerView;
    private TextView txtLevelName;
    private String lName;
    private int STORAGE_PERMISSION_CODE = 23;
    private Button back;
    private LinearLayout changeColor;
    private ImageView imageView;
    String uriGreen = "@drawable/green_panel";
    String uriYellow = "@drawable/yellow_panel";
    String uriRed = "@drawable/red_panel";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_level_activity);

        Global.levelName = getIntent().getStringExtra("name");
        Global.levelId = getIntent().getIntExtra("id", 0);
        value=Global.levelId;
        lName=Global.levelName;
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
        Global.parentName=mSubLevels;
        mLevels = database.getLevelData(mLevel.getLid());
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
        imageView = (ImageView) findViewById(R.id.imageView);
        changeColor = (LinearLayout) findViewById(R.id.changeColor);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);
        Utils.levels = new ArrayList<>();
        mLevels = new ArrayList<>();
        database = new DatabaseHelper(this);
        txtLevelName = (TextView) findViewById(R.id.txtLevelName);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        subLevelAdapter = new SubLevelAdapter(this);


    }

    private void prepareDisplay() {

        int imageResourceGreen = getResources().getIdentifier(uriGreen, null, getPackageName());
        Drawable resGreen = getResources().getDrawable(imageResourceGreen);
        int imageResourceYellow = getResources().getIdentifier(uriYellow, null, getPackageName());
        Drawable resYellow = getResources().getDrawable(imageResourceYellow);
        int imageResourceRed = getResources().getIdentifier(uriRed, null, getPackageName());
        Drawable resRed = getResources().getDrawable(imageResourceRed);
        if (value == 1) {
            imageView.setImageResource(R.drawable.grren_coins);
            changeColor.setBackground(resGreen);
            txtLevelName.setTextColor(0xff00ff00);
        } else if (value == 2) {
            imageView.setImageResource(R.drawable.yellow_coins);
            changeColor.setBackground(resYellow);
            txtLevelName.setTextColor(0xffffff00);
        }else if (value == 3) {
            imageView.setImageResource(R.drawable.red_coins);
            changeColor.setBackground(resRed);
            txtLevelName.setTextColor(0xffff0000);
        }else if (value == 5) {
            imageView.setImageResource(R.drawable.red_coins);
            changeColor.setBackground(resRed);
            txtLevelName.setTextColor(0xffff0000);
        }
        int item = Utils.getScreenSize(this, 80);
        txtLevelName.setText(lName);
        recyclerView.setLayoutManager(new GridLayoutManager(this, item));
        recyclerView.setAdapter(subLevelAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back) {
            finish();
        }
    }
}
