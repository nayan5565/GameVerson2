package com.example.nayan.gameverson2.activity;

import android.os.Bundle;
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

    public static ArrayList<MSubLevel> mSubLevels;
    private static int value;
    private static SubLevelAdapter subLevelAdapter;
    private static ArrayList<MLevel> mLevels;
    private static MSubLevel mSubLevel = new MSubLevel();
    private static MLevel mLevel = new MLevel();
    int totalPoint;
    private DatabaseHelper database;
    private RecyclerView recyclerView;
    private TextView txtLevelName, txtAllTotal_ponts, txtLevelSelect;
    private MAllContent mAllContent = new MAllContent();
    private String lName;
    private MLock mLock;
    private Button back, btnSubSetting;
    private LinearLayout changeColor;
    private ImageView imageView, imgLevelName;

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
        txtLevelName = (TextView) findViewById(R.id.imgLevelName);
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
            Utils.changeUIcolor(this, Global.uriBangla, changeColor);
            txtLevelName.setBackgroundResource(R.drawable.bangla);
//            txtLevelName.setTextColor(0xff00ff00);
        } else if (value == 2) {
            imageView.setImageResource(R.drawable.yellow_coins);
            Utils.changeUIcolor(this, Global.uriOngko, changeColor);
            txtLevelName.setBackgroundResource(R.drawable.ongko);
//            txtLevelName.setTextColor(0xffffff00);
        } else if (value == 3) {
            imageView.setImageResource(R.drawable.red_coins);
            Utils.changeUIcolor(this, Global.uriEnglish, changeColor);
            txtLevelName.setBackgroundResource(R.drawable.english);
        } else if (value == 4) {
//            imageView.setImageResource(R.drawable.red_coins);
            Utils.changeUIcolor(this, Global.uriMath, changeColor);
            txtLevelName.setBackgroundResource(R.drawable.math);
        }
        int item = Utils.getScreenSize(this, 180);
//        DatabaseHelper helper=new DatabaseHelper(this);
//        MLock lock=new MLock();
//        lock=helper.getLocalData(Global.subLevelId);
//        Global.totalPoint=lock.getTotal_pont();

        Global.ALL_TOTAL_POINT = mLock.getAll_total_point();
        Log.e("all", "item: " + item);
        txtAllTotal_ponts.setText(totalPoint + "");

//        txtLevelName.setText(lName);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
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
