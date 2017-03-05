package com.example.nayan.gameverson2.activity;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.adapter.GameAdapter;
import com.example.nayan.gameverson2.model.MAllContent;
import com.example.nayan.gameverson2.model.MLevel;
import com.example.nayan.gameverson2.model.MLock;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.model.MWords;
import com.example.nayan.gameverson2.utils.DatabaseHelper;
import com.example.nayan.gameverson2.utils.GameLogic;
import com.example.nayan.gameverson2.utils.Global;
import com.example.nayan.gameverson2.utils.SpacesItemDecoration;
import com.example.nayan.gameverson2.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by NAYAN on 11/24/2016.
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private static int levelId;
    private static GameActivity gameActivity;
    private static MLevel mLevel;
    private static MAllContent mContents;
    private MSubLevel mSubLevel = new MSubLevel();
    private ArrayList<MAllContent> imageArrayList1;
    private ArrayList<MWords> wordsList;
    private ImageView imgSetting, imageView;
    private RecyclerView recyclerView;
    //    private Context context;
    private GameAdapter gameAdapter;
    private DatabaseHelper database;
    public String subLevelName;
    public String parentName;
    public TextView txtName, txtTotalPoint;
    private MLock mLock;
    String uriGreen = "@drawable/green_panel";
    String uriYellow = "@drawable/yellow_panel";
    String uriRed = "@drawable/red_panel";

    //    private GameActivity(){
//
//    }
    public static GameActivity getInstance() {
        return gameActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_activity);
//        VungleAdManager.getInstance(this).play();


        init();
        getLocalData();
        prepareDisplay();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void init() {

        gameActivity = this;
        imageView = (ImageView) findViewById(R.id.imageView);
        txtName = (TextView) findViewById(R.id.txtName);
        txtTotalPoint = (TextView) findViewById(R.id.txtTotalPoint);
        database = new DatabaseHelper(this);
        imgSetting = (ImageView) findViewById(R.id.imgseting);
        imgSetting.setOnClickListener(this);
        imageArrayList1 = new ArrayList<>();
        wordsList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.addItemDecoration(new SpacesItemDecoration(7));
        Global.subLevelName = getIntent().getStringExtra("subLevelName");
        subLevelName = Global.subLevelName;
        Global.parentLevelName = getIntent().getStringExtra("parentLevelName");
        parentName = Global.parentLevelName;

        Global.SUB_INDEX_POSITION = getIntent().getIntExtra("index", 0);
        Global.subLevelId = getIntent().getIntExtra("Sid", 0);


        gameAdapter = new GameAdapter(this);

    }

    public void getLocalData() {


        mLock = database.getLocalData(Global.levelId, Global.subLevelId);

        Log.e("TEST", Global.levelId + ":" + Global.subLevelId + ":" + Global.totalPoint);

        if (Global.subLevelId == 1) {
            imageArrayList1 = database.getBanglaContentsContentsData();
            Collections.shuffle(imageArrayList1);
        } else if (Global.subLevelId == 2) {
            ArrayList<MAllContent> realAssets = new ArrayList<>();
            realAssets = database.getBanglaContentsContentsData();
            imageArrayList1 = generatesTxtSen(realAssets);
            Collections.shuffle(imageArrayList1);
        } else if (Global.subLevelId == 3) {
            imageArrayList1 = database.getBanglaContentsContentsData();
//            Collections.shuffle(imageArrayList1);

        } else if (Global.subLevelId == 4) {
            imageArrayList1 = database.getMathContentsContentsData();
            Collections.shuffle(imageArrayList1);
        } else if (Global.subLevelId == 5) {
            ArrayList<MAllContent> realAssets = new ArrayList<>();
            realAssets = database.getMathContentsContentsData();
            imageArrayList1 = generateAssets(realAssets);
            Collections.shuffle(imageArrayList1);
        } else if (Global.subLevelId == 6) {
            imageArrayList1 = database.getMathContentsContentsData();
            Collections.shuffle(imageArrayList1);
        } else if (Global.subLevelId == 8) {
            imageArrayList1 = database.getEnglishContentsContentsData();
            Collections.shuffle(imageArrayList1);
        } else if (Global.subLevelId == 9) {
            imageArrayList1 = database.getEnglishContentsContentsData();
            Collections.shuffle(imageArrayList1);
        } else if (Global.subLevelId == 13) {
            ArrayList<MAllContent> realAssets = new ArrayList<>();
            realAssets = database.getEnglishContentsContentsData();
            imageArrayList1 = generateAssets(realAssets);
            Collections.shuffle(imageArrayList1);
        } else if (Global.subLevelId == 14) {
            ArrayList<MAllContent> realAssets = new ArrayList<>();
            realAssets = database.getEnglishContentsContentsData();
            imageArrayList1 = generateAssets(realAssets);
            Collections.shuffle(imageArrayList1);
        } else if (Global.subLevelId == 15) {
            ArrayList<MAllContent> realAssets = new ArrayList<>();
            realAssets = database.getEnglishContentsContentsData();
            imageArrayList1 = generateAssets(realAssets);
            Collections.shuffle(imageArrayList1);
        } else if (Global.subLevelId == 19) {
            ArrayList<MAllContent> realAssets = new ArrayList<>();
            realAssets = database.getEnglishContentsContentsData();
            imageArrayList1 = generateAssets(realAssets);
            Collections.shuffle(imageArrayList1);
        }


    }

    private ArrayList<MAllContent> generateAssets(ArrayList<MAllContent> realAssets) {
        int count = realAssets.size();
        ArrayList<MAllContent> tempAsset = new ArrayList<>();
        for (MAllContent mContents : realAssets) {
            tempAsset.add(mContents);
            count++;
            MAllContent asset1 = new MAllContent();
            asset1.setPresentType(mContents.getPresentType());
            asset1.setTxt(mContents.getTxt());
            asset1.setMid(count);
            tempAsset.add(asset1);
        }
        return tempAsset;
    }

    private ArrayList<MAllContent> generatesTxtSen(ArrayList<MAllContent> realTxtSen) {
        int count = realTxtSen.size();
        ArrayList<MAllContent> tempTxtSen = new ArrayList<>();
        for (MAllContent mContents : realTxtSen) {
            tempTxtSen.add(mContents);
            count++;
            MAllContent contents = new MAllContent();
            contents.setSen(mContents.getSen());
            contents.setMid(count);
//            contents.setPresentId(mContents.getPresentId());
            contents.setPresentType(mContents.getPresentType());
            tempTxtSen.add(contents);
        }
        return tempTxtSen;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    public void refresh(int index) {
        subLevelName = Global.parentName.get(index).getName();
        Log.e("sublevel name", "s  n :" + subLevelName);
        parentName = Global.parentName.get(index).getParentName();
        Log.e("index ", "posi =" + Global.SUB_INDEX_POSITION);
        getLocalData();
        prepareDisplay();
    }

    public void prepareDisplay() {
        Utils.setFont(this, "carterone", txtName, txtTotalPoint);
        Global.totalPoint = mLock.getTotal_pont();

        txtTotalPoint.setText(Global.totalPoint + "");
        txtName.setText(parentName + "(" + subLevelName + ")");

        int item = Utils.getScreenSize(this, 90);
        recyclerView.setLayoutManager(new GridLayoutManager(this, item));
        recyclerView.setAdapter(gameAdapter);
        gameAdapter.setData(imageArrayList1);
//        int imageResourceGreen = getResources().getIdentifier(uriGreen, null, getPackageName());
//        Drawable resGreen = getResources().getDrawable(imageResourceGreen);
//        int imageResourceYellow = getResources().getIdentifier(uriYellow, null, getPackageName());
//        Drawable resYellow = getResources().getDrawable(imageResourceYellow);
//        int imageResourceRed = getResources().getIdentifier(uriRed, null, getPackageName());
//        Drawable resRed = getResources().getDrawable(imageResourceRed);
        if (Global.levelId == 1) {
            imageView.setImageResource(R.drawable.grren_coins);

        } else if (Global.levelId == 2) {
            imageView.setImageResource(R.drawable.yellow_coins);

        } else if (Global.levelId == 3) {
            imageView.setImageResource(R.drawable.red_coins);

        } else if (Global.levelId == 4) {
            imageView.setImageResource(R.drawable.red_coins);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgseting) {
            final Dialog dialog = new Dialog(this);
            dialog.setTitle("Game Information");
            dialog.requestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY);
            dialog.setContentView(R.layout.dialog_setting);
            Button btnWin = (Button) dialog.findViewById(R.id.btnBack);
            btnWin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    GameLogic.getInstance(GameActivity.this).showHistory();
                }
            });

            dialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        GameLogic.dialog.dismiss();
        super.onBackPressed();
    }
}
