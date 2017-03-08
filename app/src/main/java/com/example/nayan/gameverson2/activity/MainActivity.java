package com.example.nayan.gameverson2.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.model.MAllContent;
import com.example.nayan.gameverson2.model.MContents;
import com.example.nayan.gameverson2.model.MLevel;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.model.MWords;
import com.example.nayan.gameverson2.tools.DatabaseHelper;
import com.example.nayan.gameverson2.tools.DialogSoundOnOff;
import com.example.nayan.gameverson2.tools.Global;
import com.example.nayan.gameverson2.tools.Utils;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSetting, special, btnBangla, btnEnglish, btnMath, btnBanglaMath, btnDrawing;
    private ImageView cloud1, cloud2;
    private MLevel mLevel;
    private MAllContent mAllContent;
    private MWords mWords;
    //    private static LevelAdapter levelAdapter;
    private static ArrayList<MLevel> levels;
    private static ArrayList<MLevel> levelsMath;
    private static ArrayList<MLevel> levelsBangla;
    private static ArrayList<MLevel> levelsDrawing;
    private static ArrayList<MLevel> levelsEnglish;
    private static ArrayList<MLevel> levelsBanglaMath;
    private DatabaseHelper database;
    private TextView txtSub, txtMath, txtDrawing, txtEnglish, txtBanglaMath, textName;
    private String image;
    private static String B_URL = Global.BASE_URL;

    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        getOnlineData();
        getEnglishContentData();
        getMathContentData();
        getBanglaContentData();
        getLocalData();

    }


    public static String getPath(String fileName) {
        String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Match Game";
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();

        }
        return dir + File.separator + fileName;
    }

    private void init() {
        textName = (TextView) findViewById(R.id.txtGameNames);
//        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/carterone.ttf");
//
//        textName.setTypeface(custom_font);
//        Utils.changeFontAnotherWay(textName, MainActivity.this);
        Utils.setFont(this, "carterone", textName);
        database = new DatabaseHelper(this);
        btnSetting = (Button) findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(this);
        mLevel = new MLevel();
        btnBangla = (Button) findViewById(R.id.btnBangla);
        btnBangla.setOnClickListener(this);
        btnEnglish = (Button) findViewById(R.id.btnEnglish);
        btnEnglish.setOnClickListener(this);
        btnMath = (Button) findViewById(R.id.btnMath);
        btnMath.setOnClickListener(this);
        btnDrawing = (Button) findViewById(R.id.btnDrawing);
        btnDrawing.setOnClickListener(this);
        txtSub = (TextView) findViewById(R.id.txtSub);
        txtMath = (TextView) findViewById(R.id.txtMath);
        txtEnglish = (TextView) findViewById(R.id.txtEnglish);
        txtDrawing = (TextView) findViewById(R.id.txtDrawing);
        txtBanglaMath = (TextView) findViewById(R.id.txtBanglaMath);
        Utils.levels = new ArrayList<>();
        btnBanglaMath = (Button) findViewById(R.id.btnBanglaMath);
        btnBanglaMath.setOnClickListener(this);
        cloud1 = (ImageView) findViewById(R.id.imgCloud1);
        cloud2 = (ImageView) findViewById(R.id.imgCloud2);
        cloud1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Utils.getScreenSize(MainActivity.this);
                Utils.moveAnimation(cloud1, cloud2);
                Utils.move(cloud1, cloud2);
            }
        }, 100);
        image = DialogSoundOnOff.getPREF(this, DialogSoundOnOff.KEY_IMAGE);
        if (image.equals(1 + "")) {
            Utils.isSoundPlay = true;

        } else if (image.equals(0 + "")) {
            Utils.isSoundPlay = false;

        }


    }


    private void getLocalData() {
        saveLevelToDb();
        levelsBangla = database.getLevelData(1);
        txtSub.setText(levelsBangla.get(0).getTotal_slevel());
        levelsBanglaMath = database.getLevelData(2);
        txtBanglaMath.setText(levelsBanglaMath.get(0).getTotal_slevel());
        levelsEnglish = database.getLevelData(3);
        txtEnglish.setText(levelsEnglish.get(0).getTotal_slevel());
        levelsMath = database.getLevelData(4);
        txtMath.setText(levelsMath.get(0).getTotal_slevel());
        levelsDrawing = database.getLevelData(5);
        txtDrawing.setText(levelsDrawing.get(0).getTotal_slevel());


        Log.e("subLevel", "size");
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

        }

        if (id == R.id.action_settings) {

            DialogSoundOnOff.dialogShow(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getOnlineData() {
        if (!Utils.isInternetOn(this)) {
            Utils.toastMassage(this, "Lost Internet Connection");
            return;
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(B_URL + Global.API_LEVELS, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        Utils.levels = new ArrayList<MLevel>();
                        Utils.mSubLevelArrayList = new ArrayList<MSubLevel>();
                        try {
                            MLevel[] levelData = gson.fromJson(response.getJSONObject("puzzle").getJSONArray("level").toString(), MLevel[].class);
                            Utils.levels = new ArrayList<MLevel>(Arrays.asList(levelData));
                            for (int i = 0; i < Utils.levels.size(); i++) {
                                for (int j = 0; j < Utils.levels.get(i).getSub().size(); j++) {

                                    MSubLevel subLevel = Utils.levels.get(i).getSub().get(j);
                                    subLevel.setParentId(Utils.levels.get(i).getLid());
                                    subLevel.setParentName(Utils.levels.get(i).getName());

                                    Utils.mSubLevelArrayList.add(subLevel);
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        saveLevelToDb();
                        saveSubLevelToDb();
                        getLocalData();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        B_URL = Global.ALTER_URL;
                        getOnlineData();
                        Log.e("json", "onfailer :" + responseString);
                    }
                }
        );
    }

    private void getEnglishContentData() {
        if (!Utils.isInternetOn(this)) {
            Utils.toastMassage(this, "Lost Internet Connection");
            return;
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(B_URL + Global.API_ENGLISH, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Log.e("json data", " is response :" + response);


                        Utils.English_words = new ArrayList<MWords>();

                        try {
                            MAllContent[] data = gson.fromJson(response.getJSONArray("contents").toString(), MAllContent[].class);
                            Utils.English = new ArrayList<MAllContent>(Arrays.asList(data));
                            for (int i = 0; i < Utils.English.size(); i++) {
//                                Utils.English.get(i).setPresentId(i+1);
                                Utils.English.get(i).setPresentType(i + 1);
                                for (int j = 0; j < Utils.English.get(i).getWords().size(); j++) {
                                    MWords mWords = Utils.English.get(i).getWords().get(j);
                                    mWords.setContentId(Utils.English.get(i).getMid());
                                    Utils.English_words.add(mWords);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        saveEnglishContentsOfAllLevelToDb();
                        saveWordsToDb();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        B_URL = Global.ALTER_URL;
                        getEnglishContentData();
                        Log.e("json", "onfailer :" + responseString);
                    }
                }
        );
    }

    private void getBanglaContentData() {
        if (!Utils.isInternetOn(this)) {
            Utils.toastMassage(this, "Lost Internet Connection");
            return;
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(B_URL + Global.API_BANGLA, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Log.e("json data", " is response :" + response);


                        Utils.BANGLA_words = new ArrayList<MWords>();

                        try {
                            MAllContent[] data = gson.fromJson(response.getJSONArray("contents").toString(), MAllContent[].class);
                            Utils.BANGLA = new ArrayList<MAllContent>(Arrays.asList(data));
                            for (int i = 0; i < Utils.BANGLA.size(); i++) {
//                                Utils.English.get(i).setPresentId(i+1);
                                Utils.BANGLA.get(i).setPresentType(i + 1);
                                for (int j = 0; j < Utils.BANGLA.get(i).getWords().size(); j++) {
                                    MWords mWords = Utils.BANGLA.get(i).getWords().get(j);
                                    mWords.setContentId(Utils.BANGLA.get(i).getMid());
                                    Utils.BANGLA_words.add(mWords);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        saveBanglaContentsOfAllLevelToDb();
                        saveBanglaWordsToDb();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        B_URL = Global.ALTER_URL;
                        getBanglaContentData();
                        Log.e("json", "onfailer :" + responseString);
                    }
                }
        );
    }

    private void getMathContentData() {
        if (!Utils.isInternetOn(this)) {
            Utils.toastMassage(this, "Lost Internet Connection");
            return;
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(B_URL + Global.API_MATH, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Log.e("json data", " is response :" + response);


                        Utils.MATH_words = new ArrayList<MWords>();

                        try {
                            MAllContent[] data = gson.fromJson(response.getJSONArray("contents").toString(), MAllContent[].class);
                            Utils.Maths = new ArrayList<MAllContent>(Arrays.asList(data));
                            for (int i = 0; i < Utils.Maths.size(); i++) {
                                Utils.Maths.get(i).setPresentType(i + 1);
                                for (int j = 0; j < Utils.Maths.get(i).getWords().size(); j++) {
                                    MWords mWords = Utils.Maths.get(i).getWords().get(j);
                                    mWords.setContentId(Utils.Maths.get(i).getMid());
                                    Utils.MATH_words.add(mWords);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        saveMathContentsOfAllLevelToDb();
                        saveMathWordsToDb();
                        Log.e("mathList", "is : " + Utils.MATH_words.size());
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        B_URL = Global.ALTER_URL;
                        getMathContentData();
                        Log.e("json", "onfailer :" + responseString);
                    }
                }
        );
    }

    private void getOnlineDataByGson() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(Global.API_LEVELS, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject puzzle = response.getJSONObject("puzzle");
                    JSONArray level = puzzle.getJSONArray("level");
                    MLevel[] mLevels = gson.fromJson(level.toString(), MLevel[].class);

                    levels = new ArrayList<MLevel>(Arrays.asList(mLevels));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getOnlineBanglaContentsData() {
        if (!Utils.isInternetOn(this)) {
            Utils.toastMassage(this, "Lost Internet Connection");
            return;
        }

        AsyncHttpClient httpClient = new AsyncHttpClient();

        httpClient.post(B_URL + Global.API_CONTENTS, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Utils.contents = new ArrayList<MContents>();
                try {
                    JSONArray content = response.getJSONArray("contents");
                    MContents mContents;
                    int count = 0;
                    for (int i = 0; i < content.length(); i++) {
                        JSONObject jsonObject = content.getJSONObject(i);
                        count++;
                        mContents = new MContents();
                        mContents.setMid(jsonObject.getInt("mid"));
                        mContents.setLid(jsonObject.getInt("lid"));
                        mContents.setImg(jsonObject.getString("img"));
                        mContents.setAud(jsonObject.getString("aud"));
                        mContents.setTxt(jsonObject.getString("txt"));
                        mContents.setVid(jsonObject.getString("vid"));
                        mContents.setSen(jsonObject.getString("sen"));
                        mContents.setPresentId(count);
                        mContents.setPresentType(count);


                        Utils.contents.add(mContents);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                saveContentsToDb();
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                B_URL = Global.ALTER_URL;
                getOnlineBanglaContentsData();
            }
        });
    }
    // it will help to know phone is connected to internet or not
    // in this case ACCESS_NETWORK_STATE is needed as permission


    private void saveLevelToDb() {
        Log.e("SAVE", "Level size:" + Utils.levels.size());
        for (MLevel data : Utils.levels) {
            database.addLevelFromJson(data);
        }
    }

    private void saveSubLevelToDb() {
        Log.e("SAVE db", "mSubLevel size:" + Utils.mSubLevelArrayList.size());
        for (MSubLevel data2 : Utils.mSubLevelArrayList) {
            database.addSubFromJsom(data2);
        }
    }

    private void saveContentsToDb() {
        for (MContents data2 : Utils.contents) {
            database.addContentsFromJsom(data2);
        }
    }

    private void saveEnglishContentsOfAllLevelToDb() {
        for (MAllContent mAllContent : Utils.English) {
            database.addEnglishContentsFromJsom(mAllContent);
        }
    }

    private void saveBanglaContentsOfAllLevelToDb() {
        for (MAllContent mAllContent : Utils.BANGLA) {
            database.addBanglaContentsFromJsom(mAllContent);
        }
    }

    private void saveMathContentsOfAllLevelToDb() {
        for (MAllContent mAllContent : Utils.Maths) {
            database.addMathContentsFromJsom(mAllContent);
        }
    }

    private void saveWordsToDb() {
        for (MWords mWords : Utils.English_words) {
            database.addWordsFromJsom(mWords);
        }
    }

    private void saveBanglaWordsToDb() {
        for (MWords mWords : Utils.BANGLA_words) {
            database.addBanglaWordsFromJsom(mWords);
        }
    }


    private void saveMathWordsToDb() {
        for (MWords mWords : Utils.MATH_words) {
            database.addMathWordsFromJsom(mWords);
            Log.e("math", " words");
        }
    }

    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_game_exit);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final Button btnYes = (Button) dialog.findViewById(R.id.btnYes);
        final Button btnNO = (Button) dialog.findViewById(R.id.btnNo);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnYes.setBackgroundColor(0xff444444);
                finish();

            }
        });

        btnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNO.setBackgroundColor(0xff00ff00);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void exitYes() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnBangla) {

            Intent intent = new Intent(MainActivity.this, SubLevelActivity.class);
            intent.putExtra("id", 1);
            intent.putExtra("name", "বাংলা  ");
            startActivity(intent);
        } else if (v.getId() == R.id.btnBanglaMath) {
            Intent intent = new Intent(MainActivity.this, SubLevelActivity.class);
            intent.putExtra("id", 2);
            intent.putExtra("name", "অংক ");
            startActivity(intent);
        } else if (v.getId() == R.id.btnEnglish) {
            Intent intent = new Intent(MainActivity.this, SubLevelActivity.class);
            intent.putExtra("id", 3);
            intent.putExtra("name", "English ");
            startActivity(intent);
        } else if (v.getId() == R.id.btnMath) {
            Intent intent = new Intent(MainActivity.this, SubLevelActivity.class);
            intent.putExtra("id", 4);
            intent.putExtra("name", "Math ");
            startActivity(intent);
        } else if (v.getId() == R.id.btnDrawing) {
            Intent intent = new Intent(MainActivity.this, SubLevelActivity.class);
            intent.putExtra("id", 5);
            intent.putExtra("name", "Drawing ");
            startActivity(intent);
        } else if (v.getId() == R.id.btnSetting) {
            DialogSoundOnOff.dialogShow(this);

        }

    }
}
