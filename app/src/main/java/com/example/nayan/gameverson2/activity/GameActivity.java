package com.example.nayan.gameverson2.activity;

import android.app.Dialog;
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
import com.example.nayan.gameverson2.model.MContents;
import com.example.nayan.gameverson2.model.MLevel;
import com.example.nayan.gameverson2.model.MSubLevel;
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
    private static MContents mContents;
    private MSubLevel mSubLevel=new MSubLevel();
    private ArrayList<MContents> imageArrayList1;
    private ImageView imgSetting;
    private RecyclerView recyclerView;
//    private Context context;
    private GameAdapter gameAdapter;
    private DatabaseHelper database;
    public String subLevelName;
    public String parentName;
    private TextView txtName;

//    private GameActivity(){
//
//    }
    public static GameActivity getInstance(){
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

    public void getLocalData() {
        if (Global.SUB_LEVEL_ID == 1  ) {
            imageArrayList1 = database.getBanglaContentsData();
            Collections.shuffle(imageArrayList1);
        } else if (Global.SUB_LEVEL_ID == 2) {
            ArrayList<MContents> realAssets = new ArrayList<>();
            realAssets = database.getBanglaContentsData();
            imageArrayList1 = generateAssets(realAssets);
            Collections.shuffle(imageArrayList1);
        } else if (Global.SUB_LEVEL_ID == 3) {
            imageArrayList1 = database.getBanglaContentsData();
            Collections.shuffle(imageArrayList1);

        } else if (Global.SUB_LEVEL_ID == 4) {
            ArrayList<MContents> realAssets = new ArrayList<>();
            realAssets = database.getBanglaContentsData();
            imageArrayList1 = generateAssets(realAssets);
            Collections.shuffle(imageArrayList1);
        } else if (Global.SUB_LEVEL_ID == 5) {
            ArrayList<MContents> realAssets = new ArrayList<>();
            realAssets = database.getBanglaContentsData();
            imageArrayList1 = generateAssets(realAssets);
            Collections.shuffle(imageArrayList1);
        } else if (Global.SUB_LEVEL_ID == 6) {
            ArrayList<MContents> realAssets = new ArrayList<>();
            realAssets = database.getBanglaContentsData();
            imageArrayList1 = generateAssets(realAssets);
            Collections.shuffle(imageArrayList1);
        } else if (Global.SUB_LEVEL_ID == 8) {
            ArrayList<MContents> realAssets = new ArrayList<>();
            realAssets = database.getBanglaContentsData();
            imageArrayList1 = generateAssets(realAssets);
            Collections.shuffle(imageArrayList1);
        } else if (Global.SUB_LEVEL_ID == 9) {
            ArrayList<MContents> realAssets = new ArrayList<>();
            realAssets = database.getBanglaContentsData();
            imageArrayList1 = generateAssets(realAssets);
            Collections.shuffle(imageArrayList1);
        } else if (Global.SUB_LEVEL_ID == 13) {
            ArrayList<MContents> realAssets = new ArrayList<>();
            realAssets = database.getBanglaContentsData();
            imageArrayList1 = generateAssets(realAssets);
            Collections.shuffle(imageArrayList1);
        } else if (Global.SUB_LEVEL_ID == 14) {
            ArrayList<MContents> realAssets = new ArrayList<>();
            realAssets = database.getBanglaContentsData();
            imageArrayList1 = generateAssets(realAssets);
            Collections.shuffle(imageArrayList1);
        }else if (Global.SUB_LEVEL_ID == 15) {
            ArrayList<MContents> realAssets = new ArrayList<>();
            realAssets = database.getBanglaContentsData();
            imageArrayList1 = generateAssets(realAssets);
            Collections.shuffle(imageArrayList1);
        }else if (Global.SUB_LEVEL_ID == 19) {
            ArrayList<MContents> realAssets = new ArrayList<>();
            realAssets = database.getBanglaContentsData();
            imageArrayList1 = generateAssets(realAssets);
            Collections.shuffle(imageArrayList1);
        }


    }

    private ArrayList<MContents> generateAssets(ArrayList<MContents> realAssets) {
        int count = realAssets.size();
        ArrayList<MContents> tempAsset = new ArrayList<>();
        for (MContents mContents : realAssets) {
            tempAsset.add(mContents);
            count++;
            MContents asset1 = new MContents();
            asset1.setPresentType(mContents.getPresentType());
            asset1.setTxt(mContents.getTxt());
            asset1.setMid(count);
            tempAsset.add(asset1);
        }
        return tempAsset;
    }

    private ArrayList<MContents> generatesTxtSen(ArrayList<MContents> realTxtSen) {
        int count = realTxtSen.size();
        ArrayList<MContents> tempTxtSen = new ArrayList<>();
        for (MContents mContents : realTxtSen) {
            tempTxtSen.add(mContents);
            count++;
            MContents contents = new MContents();
            contents.setSen(mContents.getSen());
            contents.setMid(count);
            contents.setPresentId(mContents.getPresentId());
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

    private void init() {

        gameActivity=this;
        txtName = (TextView) findViewById(R.id.txtName);
        database = new DatabaseHelper(this);
        imgSetting = (ImageView) findViewById(R.id.imgseting);
        imgSetting.setOnClickListener(this);
        imageArrayList1 = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.addItemDecoration(new SpacesItemDecoration(7));
        Global.subLevelName=getIntent().getStringExtra("subLevelName");
        subLevelName =Global.subLevelName ;
        Global.parentLevelName= getIntent().getStringExtra("parentLevelName");
        parentName =Global.parentLevelName;

        Global.INDEX_POSISION = getIntent().getIntExtra("index", 0);
        Global.SUB_LEVEL_ID = getIntent().getIntExtra("Sid", 0);



        gameAdapter = new GameAdapter(this);

    }

    public void refresh(int index){
        subLevelName=Global.parentName.get(index).getName();
        Log.e("sublevel name","s  n :"+subLevelName);
        parentName=Global.parentName.get(index).getParentName();
        getLocalData();
        prepareDisplay();
    }
    public void prepareDisplay() {
        txtName.setText(parentName + "(" + subLevelName + ")");
        int item = Utils.getScreenSize(this, 100);
        recyclerView.setLayoutManager(new GridLayoutManager(this, item));
        recyclerView.setAdapter(gameAdapter);
        gameAdapter.setData(imageArrayList1);
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
}
