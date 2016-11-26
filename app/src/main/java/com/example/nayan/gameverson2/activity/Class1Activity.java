package com.example.nayan.gameverson2.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.adapter.Class1AdapterOfBangla;
import com.example.nayan.gameverson2.model.MContents;
import com.example.nayan.gameverson2.model.MLevel;
import com.example.nayan.gameverson2.utils.Global;
import com.example.nayan.gameverson2.utils.DatabaseHelper1;
import com.example.nayan.gameverson2.utils.NLogic;
import com.example.nayan.gameverson2.utils.SpacesItemDecoration;
import com.example.nayan.gameverson2.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by NAYAN on 11/24/2016.
 */
public class Class1Activity extends AppCompatActivity implements View.OnClickListener {
    private static int levelId;
    private static MLevel mLevel;
    private static MContents mContents;
    private ArrayList<MContents> imageArrayList1;
    private ImageView imgSetting;
    private RecyclerView recyclerView;
    private Class1AdapterOfBangla class1AdapterOfBangla;
    private DatabaseHelper1 database;
    public String subLevel;
    public String parentName;
    private TextView txtName;

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

    private void getLocalData() {
        if (Global.SUB_LEVEL_ID == 1) {
            imageArrayList1 = database.getContentsData();
            Collections.shuffle(imageArrayList1);
        } else if (Global.SUB_LEVEL_ID == 2) {
            ArrayList<MContents> realAssets = new ArrayList<>();
            realAssets = database.getContentsData();
            imageArrayList1 = generateAssets(realAssets);
            Collections.shuffle(imageArrayList1);
        } else if (Global.SUB_LEVEL_ID == 3) {
            ArrayList<MContents> realTxtSen = new ArrayList<>();
            realTxtSen = database.getContentsData();
            imageArrayList1 = generatesTxtSen(realTxtSen);
        }


    }

    private ArrayList<MContents> generateAssets(ArrayList<MContents> realAssets) {
        int count = 20;
        ArrayList<MContents> tempAsset = new ArrayList<>();
        for (MContents mContents : realAssets) {
            tempAsset.add(mContents);
            count++;
            MContents asset1 = new MContents();
            asset1.setPresentType(count);
            asset1.setTxt(mContents.getTxt());
            asset1.setMid(mContents.getMid());
            tempAsset.add(asset1);
        }
        return tempAsset;
    }

    private ArrayList<MContents> generatesTxtSen(ArrayList<MContents> realTxtSen) {
        int count = 20;
        ArrayList<MContents> tempTxtSen = new ArrayList<>();
        for (MContents mContents : realTxtSen) {
            tempTxtSen.add(mContents);
            count++;
            MContents contents = new MContents();
            contents.setSen(mContents.getSen());
            contents.setMid(mContents.getMid());
            contents.setPresentType(count);
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

        txtName = (TextView) findViewById(R.id.txtName);
        database = new DatabaseHelper1(this);
        imgSetting = (ImageView) findViewById(R.id.imgseting);
        imgSetting.setOnClickListener(this);
        imageArrayList1 = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.addItemDecoration(new SpacesItemDecoration(7));

        subLevel = getIntent().getStringExtra("subLevel");
        Global.INDEX_POSISION = getIntent().getIntExtra("index", 0);
        Global.SUB_LEVEL_ID = getIntent().getIntExtra("Sid", 0);
        parentName = getIntent().getStringExtra("parentLevel");
        txtName.setText(parentName + "(" + subLevel + ")");

        class1AdapterOfBangla = new Class1AdapterOfBangla(this);

    }

    private void prepareDisplay() {
        int item = Utils.getScreenSize(this, 100);
        recyclerView.setLayoutManager(new GridLayoutManager(this, item));
        recyclerView.setAdapter(class1AdapterOfBangla);
        class1AdapterOfBangla.setData(imageArrayList1);
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
                    NLogic.getInstance(Class1Activity.this).showHistory();
                }
            });

            dialog.show();
        }
    }
}
