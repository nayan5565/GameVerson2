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
import com.example.nayan.gameverson2.model.MAllContent;
import com.example.nayan.gameverson2.model.MLevel;
import com.example.nayan.gameverson2.model.MLock;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.tools.DatabaseHelper;
import com.example.nayan.gameverson2.tools.DialogSoundOnOff;
import com.example.nayan.gameverson2.tools.Global;
import com.example.nayan.gameverson2.tools.Utils;

import java.util.ArrayList;

/**
 * Created by NAYAN on 11/24/2016.
 */
public class SubLevelActivity extends AppCompatActivity implements View.OnClickListener {

    private static int value;
    private static SubLevelAdapter subLevelAdapter;
    public static ArrayList<MSubLevel> mSubLevels;
    private static ArrayList<MLevel> mLevels;
    private static MSubLevel mSubLevel = new MSubLevel();
    private static MLevel mLevel = new MLevel();
    private DatabaseHelper database;
    private RecyclerView recyclerView;
    private TextView txtLevelName, txtAllTotal_ponts, txtLevelSelect;
    MAllContent mAllContent = new MAllContent();
    private String lName;
    private MLock mLock;
    private int STORAGE_PERMISSION_CODE = 23;
    private Button back, btnSubSetting;
    private LinearLayout changeColor;
    private ImageView imageView;
    int totalPoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_level_activity);

        Global.levelName = getIntent().getStringExtra("name");
        Global.levelId = getIntent().getIntExtra("id", 0);
        value = Global.levelId;
        lName = Global.levelName;
        Log.e("log", "is" + value);

        init();
        getLocalData();
        prepareDisplay();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getLocalData();
        prepareDisplay();
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
                Utils.toastMassage(this, "Permission granted now you can read the storage");
            } else {
                //Displaying another toast if permission is not granted
                Utils.toastMassage(this, "Oops you just denied the permission");
            }
        }
    }

    private void downloadAssets() {
//        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
//            ArrayList<String> uniqueImage = new ArrayList<>();
//            for (int i = 0; i < Utils.BANGLA.size(); i++) {
//                ArrayList<MWords> assetArrayList = database.getBanglaWordsData(Utils.BANGLA.get(i).getMid());
//                for (int j = 0; j < assetArrayList.size(); j++) {
//                    if (!uniqueImage.contains(assetArrayList.get(j).getWimg())) {
//                        uniqueImage.add(assetArrayList.get(j).getWimg());
//                    }
//                }
//            }
//            for (int i = 0; i < uniqueImage.size(); i++) {
//                Log.e("DOWNLOAD_PATH", "Path:" + MainActivity.getPath(uniqueImage.get(i)));
//                File file = new File(MainActivity.getPath(uniqueImage.get(i)));
//                if (!file.exists()) {
//                    new DownLoadAsyncTask(this, MainActivity.getPath(uniqueImage.get(i))).execute(IMAGE_URL + uniqueImage.get(i));
//                }
//
//            }
//        } else requestStoragePermission();


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


    private void init() {
        txtLevelSelect = (TextView) findViewById(R.id.levelSelect);
        mLock = new MLock();
        imageView = (ImageView) findViewById(R.id.imageView);
        changeColor = (LinearLayout) findViewById(R.id.changeColor);
        back = (Button) findViewById(R.id.back);
        btnSubSetting = (Button) findViewById(R.id.btnSubSetting);
        back.setOnClickListener(this);
        btnSubSetting.setOnClickListener(this);
        Global.levels = new ArrayList<>();
        mLevels = new ArrayList<>();
        mSubLevels = new ArrayList<>();
        database = new DatabaseHelper(this);
        txtLevelName = (TextView) findViewById(R.id.txtLevelName);
        txtAllTotal_ponts = (TextView) findViewById(R.id.txtAllTotalPoints);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        subLevelAdapter = new SubLevelAdapter(this);


    }

    private void getLocalData() {
//        mLock = database.getLocalData(Global.levelId, Global.subLevelId);
        mSubLevels = database.getSubLevelData(value);
        if (mSubLevels.size() < 1) {
            Utils.toastMassage(this, "Empty Data");
            return;
        }
        Global.parentName = mSubLevels;
        mLevels = database.getLevelData(mLevel.getLid());
        Log.e("getDb", "sublevel : " + mSubLevels.size());

        mSubLevels.get(0).setUnlockNextLevel(1);
        subLevelAdapter.setData(mSubLevels);
        totalPoint = database.getLockTotalPointData(Global.levelId);

    }

    private void prepareDisplay() {
        Utils.setFont(this, "carterone", txtAllTotal_ponts, txtLevelName, txtLevelSelect);
        if (value == 1) {
            imageView.setImageResource(R.drawable.grren_coins);
            Utils.changeUIcolor(this,Global.uriGreen,changeColor);
            txtLevelName.setTextColor(0xff00ff00);
        } else if (value == 2) {
            imageView.setImageResource(R.drawable.yellow_coins);
            Utils.changeUIcolor(this,Global.uriYellow,changeColor);
            txtLevelName.setTextColor(0xffffff00);
        } else if (value == 3) {
            imageView.setImageResource(R.drawable.red_coins);
            Utils.changeUIcolor(this,Global.uriRed,changeColor);
            txtLevelName.setTextColor(0xffff0000);
        } else if (value == 5) {
            imageView.setImageResource(R.drawable.red_coins);
            Utils.changeUIcolor(this,Global.uriGreen,changeColor);
            txtLevelName.setTextColor(0xffff0000);
        }
        int item = Utils.getScreenSize(this, 90);
//        DatabaseHelper helper=new DatabaseHelper(this);
//        MLock lock=new MLock();
//        lock=helper.getLocalData(Global.subLevelId);
//        Global.totalPoint=lock.getTotal_pont();

        Global.ALL_TOTAL_POINT = mLock.getAll_total_point();
        Log.e("all", "point is: " + Global.ALL_TOTAL_POINT);
        txtAllTotal_ponts.setText(totalPoint + "");

        txtLevelName.setText(lName);
        recyclerView.setLayoutManager(new GridLayoutManager(this, item));
        recyclerView.setAdapter(subLevelAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back) {
            finish();
        } else if (v.getId() == R.id.btnSubSetting) {
            DialogSoundOnOff.dialogShow(SubLevelActivity.this);
        }
    }
}
