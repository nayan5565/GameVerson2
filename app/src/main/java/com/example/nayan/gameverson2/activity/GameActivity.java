package com.example.nayan.gameverson2.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.adapter.GameAdapter;
import com.example.nayan.gameverson2.model.MAllContent;
import com.example.nayan.gameverson2.model.MLevel;
import com.example.nayan.gameverson2.model.MLock;
import com.example.nayan.gameverson2.model.MQuestions;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.model.MWords;
import com.example.nayan.gameverson2.tools.DatabaseHelper;
import com.example.nayan.gameverson2.tools.Global;
import com.example.nayan.gameverson2.tools.MyGoogleAnalytics;
import com.example.nayan.gameverson2.tools.SpacesItemDecoration;
import com.example.nayan.gameverson2.tools.Utils;

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
    public String subLevelName, how;
    public String parentName;
    public TextView txtName, txtTotalPoint, txtSubName;
    LinearLayout popUI;
    int popUp, popUp2, popUp3, popUp4, popUp5, popUp6, popUp7, popUp8, popUp9, popUp10, popUp11;
    int one, two, three, four;
    private MSubLevel mSubLevel = new MSubLevel();
    private ArrayList<MAllContent> imageArrayList1;
    private ArrayList<MWords> wordsList;
    private ImageView imgSetting, imageView, imgHelp;
    private RecyclerView recyclerView;
    //    private Context context;
    private GameAdapter gameAdapter;
    private DatabaseHelper database;
    private MQuestions mQuestions;
    private MLock mLock;

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
        MyGoogleAnalytics.getInstance().setupAnalytics("Game Activity");
        getLocalData();
        prepareDisplay();
        getPopUp();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


    public void init() {
        mQuestions = new MQuestions();
        imgHelp = (ImageView) findViewById(R.id.imgHelp);
        imgHelp.setOnClickListener(this);
        gameActivity = this;
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(this);
        txtName = (TextView) findViewById(R.id.txtNameGame);
        txtSubName = (TextView) findViewById(R.id.txtNameGameSub);
        txtTotalPoint = (TextView) findViewById(R.id.txtTotalPoint);
        database = new DatabaseHelper(this);
        imgSetting = (ImageView) findViewById(R.id.imgseting);
        imgSetting.setOnClickListener(this);
        imageArrayList1 = new ArrayList<>();
        wordsList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.addItemDecoration(new SpacesItemDecoration(7));
        Global.subLevelName = getIntent().getStringExtra("subLevelName");
        Global.logic = getIntent().getIntExtra("SLogic", 0);
        Global.how_to_play = getIntent().getStringExtra("how");
        how = Global.how_to_play;
        subLevelName = Global.subLevelName;
        Global.parentLevelName = getIntent().getStringExtra("parentLevelName");
        parentName = Global.parentLevelName;
        mQuestions = database.getQuesData();
//        Global.popUp=database.getPopUp();
        Global.popUp = mQuestions.getPopUp();
        Global.popUp2 = mQuestions.getPopUp2();
        Global.popUp3 = mQuestions.getPopUp3();
        Global.popUp4 = mQuestions.getPopUp4();
        Global.popUp5 = mQuestions.getPopUp5();
        Global.popUp6 = mQuestions.getPopUp6();
        Global.popUp7 = mQuestions.getPopUp7();
        Global.popUp8 = mQuestions.getPopUp8();
        Global.popUp9 = mQuestions.getPopUp9();
        Global.popUp10 = mQuestions.getPopUp10();
        Global.popUp11 = mQuestions.getPopUp11();

        Log.e("popUp", "opp1  " + Global.popUp);
        Log.e("popUp", "opp2  " + Global.popUp2);
        Log.e("popUp", "opp3  " + Global.popUp3);
        Log.e("popUp", "opp4 " + Global.popUp4);
        Log.e("popUp", "opp5 " + Global.popUp5);
        Log.e("popUp", "opp6 " + Global.popUp6);
        Log.e("popUp", "opp7 " + Global.popUp7);
        Log.e("popUp", "opp8 " + Global.popUp8);
        Log.e("popUp", "opp9 " + Global.popUp9);
        Log.e("popUp", "opp10 " + Global.popUp10);
        Log.e("popUp", "opp11 " + Global.popUp11);

        Global.SUB_INDEX_POSITION = getIntent().getIntExtra("index", 0);
        Global.subLevelId = getIntent().getIntExtra("Sid", 0);
//        popUp = getIntent().getIntExtra("one", 0);
//        popUp2 = getIntent().getIntExtra("two", 0);
//        popUp3 = getIntent().getIntExtra("three", 0);
//        popUp4 = getIntent().getIntExtra("four", 0);
//        Global.popUp = getIntent().getIntExtra("one", 0);
//        if (Global.subLevelId == 1) {
//
//        } else if (Global.subLevelId == 2) {
//
//        } else if (Global.subLevelId == 3) {
//
//        } else if (Global.subLevelId == 4) {
//
//        }

        gameAdapter = new GameAdapter(this);

    }

    public void getPopUp() {
        if (Global.subLevelId == 1) {
            popUp = Global.popUp;
            popUp++;
            mQuestions.setPopUp(popUp);
            database.addQuesData(mQuestions);
            Log.e("popUp", "one " + popUp);
            if (Global.popUp == 0) {
                diaRulesOfPlay(how);
            }
        } else if (Global.subLevelId == 2) {
            popUp2 = Global.popUp2;
            popUp2++;
            mQuestions.setPopUp2(popUp2);
            database.addQuesData(mQuestions);
            Log.e("popUp", "two " + popUp2);
            if (Global.popUp2 == 0) {
                diaRulesOfPlay(how);
            }
        } else if (Global.subLevelId == 3) {
            popUp3 = Global.popUp3;
            popUp3++;
            mQuestions.setPopUp3(popUp3);
            database.addQuesData(mQuestions);
            Log.e("popUp", "three " + popUp3);
            if (Global.popUp3 == 0) {
                diaRulesOfPlay(how);
            }
        } else if (Global.subLevelId == 4) {
            popUp4 = Global.popUp4;
            popUp4++;
            mQuestions.setPopUp4(popUp4);
            database.addQuesData(mQuestions);
            Log.e("popUp", "four " + popUp4);
            if (Global.popUp4 == 0) {
                diaRulesOfPlay(how);
            }
        } else if (Global.subLevelId == 5) {
            popUp5 = Global.popUp5;
            popUp5++;
            mQuestions.setPopUp5(popUp5);
            database.addQuesData(mQuestions);
            Log.e("popUp", "five " + popUp5);
            if (Global.popUp5 == 0) {
                diaRulesOfPlay(how);
            }

        } else if (Global.subLevelId == 6) {
            popUp6 = Global.popUp6;
            popUp6++;
            mQuestions.setPopUp6(popUp6);
            database.addQuesData(mQuestions);
            Log.e("popUp", "six " + popUp6);
            if (Global.popUp6 == 0) {
                diaRulesOfPlay(how);
            }

        } else if (Global.subLevelId == 8) {
            popUp7 = Global.popUp7;
            popUp7++;
            mQuestions.setPopUp7(popUp7);
            database.addQuesData(mQuestions);
            Log.e("popUp", "seven " + popUp7);
            if (Global.popUp7 == 0) {
                diaRulesOfPlay(how);
            }

        } else if (Global.subLevelId == 9) {
            popUp8 = Global.popUp8;
            popUp8++;
            mQuestions.setPopUp8(popUp8);
            database.addQuesData(mQuestions);
            Log.e("popUp", "eight " + popUp8);
            if (Global.popUp8 == 0) {
                diaRulesOfPlay(how);
            }

        } else if (Global.subLevelId == 13) {
            popUp9 = Global.popUp9;
            popUp9++;
            mQuestions.setPopUp9(popUp9);
            database.addQuesData(mQuestions);
            Log.e("popUp", "nine " + popUp9);
            if (Global.popUp9 == 0) {
                diaRulesOfPlay(how);
            }

        } else if (Global.subLevelId == 14) {
            popUp10 = Global.popUp10;
            popUp10++;
            mQuestions.setPopUp10(popUp10);
            database.addQuesData(mQuestions);
            Log.e("popUp", "ten " + popUp10);
            if (Global.popUp10 == 0) {
                diaRulesOfPlay(how);
            }

        } else if (Global.subLevelId == 15) {
            popUp11 = Global.popUp11;
            popUp11++;
            mQuestions.setPopUp11(popUp11);
            database.addQuesData(mQuestions);
            Log.e("popUp", "eleven " + popUp11);
            if (Global.popUp11 == 0) {
                diaRulesOfPlay(how);
            }
        }
    }


    public void getLocalData() {


        mLock = database.getLocalData(Global.levelId, Global.subLevelId);

        Log.e("TEST", Global.levelId + ":" + Global.subLevelId + ":" + Global.totalPoint);

        if (Global.logic == 1) {

            if (Global.levelId == 1) {
                imageArrayList1 = database.getBanglaContentsContentsData();
            } else if (Global.levelId == 2) {
                imageArrayList1 = database.getBanglaMathContentsContentsData();
            } else if (Global.levelId == 3) {
                imageArrayList1 = database.getEnglishContentsContentsData();
            } else if (Global.levelId == 4) {
                imageArrayList1 = database.getMathContentsContentsData();
            }

            Collections.shuffle(imageArrayList1);
        } else if (Global.logic == 2) {

            ArrayList<MAllContent> realAssets = new ArrayList<>();
            if (Global.levelId == 1) {
                realAssets = database.getBanglaContentsContentsData();
            } else if (Global.levelId == 2) {
                realAssets = database.getBanglaMathContentsContentsData();
            } else if (Global.levelId == 3) {
                realAssets = database.getEnglishContentsContentsData();
            } else if (Global.levelId == 4) {
                realAssets = database.getMathContentsContentsData();
            }

            imageArrayList1 = generatesTxtImg(realAssets);
            Collections.shuffle(imageArrayList1);
        } else if (Global.logic == 3) {

            if (Global.levelId == 1) {
                imageArrayList1 = database.getBanglaContentsContentsData();
            } else if (Global.levelId == 2) {
                imageArrayList1 = database.getBanglaMathContentsContentsData();
            } else if (Global.levelId == 3) {
                imageArrayList1 = database.getEnglishContentsContentsData();
            } else if (Global.levelId == 4) {
                imageArrayList1 = database.getMathContentsContentsData();
            }
//            Collections.shuffle(imageArrayList1);

        } else if (Global.logic == 4) {

            ArrayList<MAllContent> realAssets = new ArrayList<>();
            if (Global.levelId == 1) {
                realAssets = database.getBanglaContentsContentsData();
            } else if (Global.levelId == 2) {
                realAssets = database.getBanglaMathContentsContentsData();
            } else if (Global.levelId == 3) {
                realAssets = database.getEnglishContentsContentsData();
            } else if (Global.levelId == 4) {
                realAssets = database.getMathContentsContentsData();
            }

            imageArrayList1 = generatesTxtImg(realAssets);
            Collections.shuffle(imageArrayList1);
        }


    }

    public void diaRulesOfPlay(String s) {
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.game_instruction);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        popUI = (LinearLayout) dialog.findViewById(R.id.popUpUI);
        if (Global.levelId == 1) {
            Utils.changeUIcolor(this, Global.uriBangla, popUI);
//            txtLevelName.setTextColor(0xff00ff00);
        } else if (Global.levelId == 2) {
            Utils.changeUIcolor(this, Global.uriOngko, popUI);
//            txtLevelName.setTextColor(0xffffff00);
        } else if (Global.levelId == 3) {
            Utils.changeUIcolor(this, Global.uriEnglish, popUI);
        } else if (Global.levelId == 4) {
//            imageView.setImageResource(R.drawable.red_coins);
            Utils.changeUIcolor(this, Global.uriMath, popUI);
        }
        TextView txtRule = (TextView) dialog.findViewById(R.id.txtRules);
        txtRule.setText(s);
        Button close = (Button) dialog.findViewById(R.id.btnDismiss);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
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

    private ArrayList<MAllContent> generatesTxtImg(ArrayList<MAllContent> realTxtSen) {
        int count = realTxtSen.size();
        ArrayList<MAllContent> tempTxtSen = new ArrayList<>();
        for (MAllContent mContents : realTxtSen) {
            tempTxtSen.add(mContents);
            count++;
            MAllContent contents = new MAllContent();
            contents.setImg(mContents.getImg());
            contents.setMid(count);
//            contents.setPresentId(mContents.getPresentId());
            contents.setAud(mContents.getAud());
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
        how = Global.parentName.get(index).getHowto();
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
//        txtName.setText(parentName + "(" + subLevelName + ")");
        txtSubName.setText(" -" + subLevelName + "");

        int item = Utils.getScreenSize(this, 90);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(gameAdapter);
        gameAdapter.setData(imageArrayList1);

        if (Global.levelId == 1) {
            imageView.setImageResource(R.drawable.grren_coins);
            txtName.setBackgroundResource(R.drawable.bangla);


        } else if (Global.levelId == 2) {
            imageView.setImageResource(R.drawable.yellow_coins);
            txtName.setBackgroundResource(R.drawable.ongko);

        } else if (Global.levelId == 3) {
            imageView.setImageResource(R.drawable.red_coins);
            txtName.setBackgroundResource(R.drawable.english);

        } else if (Global.levelId == 4) {
            imageView.setImageResource(R.drawable.violet_coins);
            txtName.setBackgroundResource(R.drawable.math);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgHelp || v.getId() == R.id.imageView) {
            if (Global.subLevelId == 1) {
                Log.e("levelID", "opop  " + Global.levelId);
                diaRulesOfPlay(how);

            } else if (Global.subLevelId == 2) {
                Log.e("levelID", "opop  " + Global.levelId);
                diaRulesOfPlay(how);
                Log.e("levelID", "opop  " + Global.levelId);
            } else if (Global.subLevelId == 3) {
                Log.e("levelID", "opop  " + Global.levelId);
                diaRulesOfPlay(how);

            } else if (Global.subLevelId == 4) {

                diaRulesOfPlay(how);
            } else if (Global.subLevelId == 5) {
                Log.e("levelID", "opop  " + Global.levelId);
                diaRulesOfPlay(how);
            } else if (Global.subLevelId == 6) {

                diaRulesOfPlay(how);
            } else if (Global.subLevelId == 8) {
                diaRulesOfPlay(how);

            } else if (Global.subLevelId == 9) {

                diaRulesOfPlay(how);
            } else if (Global.subLevelId == 13) {

                diaRulesOfPlay(how);
            } else if (Global.subLevelId == 14) {

                diaRulesOfPlay(how);

            } else if (Global.subLevelId == 15) {

                diaRulesOfPlay(how);
            }
        }


    }
}
