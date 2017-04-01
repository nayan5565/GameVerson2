package com.example.nayan.gameverson2.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.model.MAllContent;
import com.example.nayan.gameverson2.model.MLevel;
import com.example.nayan.gameverson2.model.MPost;
import com.example.nayan.gameverson2.model.MQuestions;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.model.MWords;
import com.example.nayan.gameverson2.tools.DatabaseHelper;
import com.example.nayan.gameverson2.tools.DialogSoundOnOff;
import com.example.nayan.gameverson2.tools.FilesDownload;
import com.example.nayan.gameverson2.tools.Global;
import com.example.nayan.gameverson2.tools.Utils;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String PARENT = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "MatchGame";
    public static String image = PARENT + File.separator + "Image";

    public static String sounds = PARENT + File.separator + "Sound";

    MPost mPost = new MPost();
    int lPopUp, lPopUp2, lPopUp3, lPopUp4;
    private ArrayList<MLevel> levelsDatas;
    private String B_URL = Global.BASE_URL;
    private MQuestions mQuestions;
    private Button btnSetting, btnResult;
    private ImageView cloud1, cloud2, btnBangla, btnEnglish, btnMath, btnBanglaMath, btnDrawing;
    private MLevel mLevel;
    private DatabaseHelper database;
    private TextView txtSub, txtMath, txtDrawing, txtEnglish, txtBanglaMath, textName, txtEnglisg, txtMatht;
    private String image1;
    private int STORAGE_PERMISSION_CODE = 23;
    private Gson gson = new Gson();

    public static String getPath(String fileName) {
        String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Match Game";
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();

        }
        return dir + File.separator + fileName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        requestStoragePermissionToMashmallow();
//        mPost.setSubLevel(database.getSubLevelData(1));
        mPost.setDeviceId(Utils.getDeviceId(this));
        mPost.setUserEmail(Utils.getPhoneGmailAcc(this));
        Utils.postDataFromDatabase(mPost);
        Utils.logIn(mPost.getUserEmail(), "123456", mPost.getDeviceId());
        getOnlineData();

        getLocalData();


    }

    private void init() {
        mQuestions = new MQuestions();
        btnResult = (Button) findViewById(R.id.btnResult);
        btnResult.setOnClickListener(this);
        levelsDatas = new ArrayList<>();
        textName = (TextView) findViewById(R.id.txtGameNames);
        Utils.setFont(this, "carterone", textName);
        database = new DatabaseHelper(this);
        btnSetting = (Button) findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(this);
        mLevel = new MLevel();
        btnBangla = (ImageView) findViewById(R.id.btnBangla);
        btnBangla.setOnClickListener(this);
        btnEnglish = (ImageView) findViewById(R.id.btnEnglish);
        btnEnglish.setOnClickListener(this);
        btnMath = (ImageView) findViewById(R.id.btnMath);
        btnMath.setOnClickListener(this);
        txtSub = (TextView) findViewById(R.id.txtSub);
        txtMath = (TextView) findViewById(R.id.txtMath);
        txtEnglish = (TextView) findViewById(R.id.txtEnglish);
        txtBanglaMath = (TextView) findViewById(R.id.txtBanglaMath);
        Global.levels = new ArrayList<>();
        btnBanglaMath = (ImageView) findViewById(R.id.btnBanglaMath);
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
        image1 = DialogSoundOnOff.getPREF(this, DialogSoundOnOff.KEY_IMAGE);
        if (image1.equals(1 + "")) {
            Utils.isSoundPlay = true;

        } else if (image1.equals(0 + "")) {
            Utils.isSoundPlay = false;

        }


    }


    private void ongkoImageDownloadFromWords() {
        int size = 0;
        if (Global.BANGLA_MATH_words.size() < Global.LEVEL_DOWNLOAD) {
            size = Global.BANGLA_MATH_words.size();
        } else {
            size = Global.LEVEL_DOWNLOAD;
        }
        for (int i = 0; i < size; i++) {
            FilesDownload.getInstance(this, image).addUrl(Global.IMAGE_URL + Global.BANGLA_MATH_words.get(i).getWimg());

        }
    }

    private void englisImageDownloadFromWords() {
        int size = 0;
        if (Global.English_words.size() < Global.LEVEL_DOWNLOAD) {
            size = Global.English_words.size();
        } else {
            size = Global.LEVEL_DOWNLOAD;
        }
        for (int i = 0; i < size; i++) {
            FilesDownload.getInstance(this, image).addUrl(Global.IMAGE_URL + Global.English_words.get(i).getWimg());

        }
    }

    private void mathImageDownloadFromWords() {
        int size = 0;
        if (Global.MATH_words.size() < Global.LEVEL_DOWNLOAD) {
            size = Global.MATH_words.size();
        } else {
            size = Global.LEVEL_DOWNLOAD;
        }
        FilesDownload filesDownload = FilesDownload.getInstance(this, image);
        for (int i = 0; i < size; i++) {
            filesDownload.addUrl(Global.IMAGE_URL + Global.MATH_words.get(i).getWimg());

        }
    }

    private void requestStoragePermissionToMashmallow() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.GET_ACCOUNTS, Manifest.permission.INTERNET}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                downloadAssets();
                //Displaying a toast
//                Utils.toastMassage(this, "Permission granted now you can read the storage");
            } else {
                //Displaying another toast if permission is not granted
                Utils.toastMassage(this, "Oops you just denied the permission");
            }
        }
    }


    private void getLocalData() {
//        saveLevelToDb();
        levelsDatas = database.getLevelAllData();
        Utils.log("levels ", "Alldata : " + levelsDatas.size());
        if (levelsDatas.size() == 0) {
            return;

        } else {
            txtSub.setText(levelsDatas.get(0).getTotal_slevel());
            txtBanglaMath.setText(levelsDatas.get(1).getTotal_slevel());
            txtEnglish.setText(levelsDatas.get(2).getTotal_slevel());
            txtMath.setText(levelsDatas.get(3).getTotal_slevel());
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

        }

        if (id == R.id.action_settings) {

            DialogSoundOnOff.dialogShow(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getOnlineData() {
        if (!Utils.isInternetOn(this)) {
            Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dia_internet_alert);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView txtInternet = (TextView) dialog.findViewById(R.id.txtInternet);
            txtInternet.setText(Global.internetAlert);
            dialog.show();
            return;
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(B_URL + Global.API_LEVELS, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        Global.levels = new ArrayList<MLevel>();
                        Global.mSubLevelArrayList = new ArrayList<MSubLevel>();
                        try {
                            MLevel[] levelData = gson.fromJson(response.getJSONObject("puzzle").getJSONArray("level").toString(), MLevel[].class);
                            Global.levels = new ArrayList<MLevel>(Arrays.asList(levelData));
                            for (int i = 0; i < Global.levels.size(); i++) {
                                for (int j = 0; j < Global.levels.get(i).getSub().size(); j++) {

                                    MSubLevel subLevel = Global.levels.get(i).getSub().get(j);
                                    subLevel.setParentId(Global.levels.get(i).getLid());
                                    subLevel.setParentName(Global.levels.get(i).getName());

                                    Global.mSubLevelArrayList.add(subLevel);
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        saveLevelToDb();
                        saveSubLevelToDb();
                        getLocalData();

                        getEnglishContentData();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        B_URL = Global.ALTER_URL;
                        getOnlineData();
                        getEnglishContentData();
                    }
                }
        );
    }

    private void getEnglishContentData() {
        if (!Utils.isInternetOn(this)) {
            Dialog dialog = new Dialog(this);
            dialog.setCancelable(false);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dia_internet_alert);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView txtInternet = (TextView) dialog.findViewById(R.id.txtInternet);
            txtInternet.setText(Global.internetAlert);
            return;
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(B_URL + Global.API_ENGLISH, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);


                        Global.English_words = new ArrayList<MWords>();

                        try {
                            MAllContent[] data = gson.fromJson(response.getJSONArray("contents").toString(), MAllContent[].class);
                            Global.English = new ArrayList<MAllContent>(Arrays.asList(data));
                            for (int i = 0; i < Global.English.size(); i++) {
//                                Utils.english.get(i).setPresentId(i+1);
                                Global.English.get(i).setPresentType(i + 1);
                                for (int j = 0; j < Global.English.get(i).getWords().size(); j++) {
                                    MWords mWords = Global.English.get(i).getWords().get(j);
                                    mWords.setContentId(Global.English.get(i).getMid());
                                    Global.English_words.add(mWords);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        saveEnglishContentsOfAllLevelToDb();
                        saveEnglishWordsToDb();
                        englishImageDownload();
                        englisImageDownloadFromWords();
                        mainEnglishSoundDownload();
                        EnglishSoundDownloadFromWords();

                        getMathContentData();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
//                        B_URL = Global.ALTER_URL;
//                        getEnglishContentData();
                        getMathContentData();
                    }
                }
        );
    }

    private void getBanglaContentData() {
        Utils.log("bangla", "step1");
        if (!Utils.isInternetOn(this)) {
            Dialog dialog = new Dialog(this);
            dialog.setCancelable(false);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dia_internet_alert);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView txtInternet = (TextView) dialog.findViewById(R.id.txtInternet);
            txtInternet.setText(Global.internetAlert);
            return;
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(B_URL + Global.API_BANGLA, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        super.onSuccess(statusCode, headers, response);
                        Utils.log("bangla", "step2");


                        Global.BANGLA_words = new ArrayList<MWords>();

                        try {
                            Utils.log("bangla", "step3");
                            MAllContent[] data = gson.fromJson(response.getJSONArray("contents").toString(), MAllContent[].class);
                            Global.BANGLA = new ArrayList<MAllContent>(Arrays.asList(data));
                            Utils.log("bangla", "size" + Global.BANGLA.size());
                            for (int i = 0; i < Global.BANGLA.size(); i++) {
//                                Utils.english.get(i).setPresentId(i+1);
                                Global.BANGLA.get(i).setPresentType(i + 1);
                                for (int j = 0; j < Global.BANGLA.get(i).getWords().size(); j++) {
                                    MWords mWords = Global.BANGLA.get(i).getWords().get(j);
                                    mWords.setContentId(Global.BANGLA.get(i).getMid());
                                    Global.BANGLA_words.add(mWords);
                                }
                            }
                            Utils.log("bangla", "Wsize" + Global.BANGLA_words.size());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Utils.log("bangla", e.toString());
                        }
                        Utils.log("bangla", "step4");

                        saveBanglaContentsOfAllLevelToDb();
                        saveBanglaWordsToDb();
                        banglaImageDownload();
                        mainBanglaSoundDownload();
                        banglaImageDownloadFromWords();
                        banglaSoundDownloadFromWords();

                        getBanglaMathContentData();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        B_URL = Global.ALTER_URL;
                        Utils.log("bangla", "step5Errror");
                        getBanglaMathContentData();
                    }
                }
        );
    }

    private void getMathContentData() {
        if (!Utils.isInternetOn(this)) {

            return;
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(B_URL + Global.API_MATH, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);


                        Global.MATH_words = new ArrayList<MWords>();

                        try {
                            MAllContent[] data = gson.fromJson(response.getJSONArray("contents").toString(), MAllContent[].class);
                            Global.Maths = new ArrayList<MAllContent>(Arrays.asList(data));
                            for (int i = 0; i < Global.Maths.size(); i++) {
                                Global.Maths.get(i).setPresentType(i + 1);
                                for (int j = 0; j < Global.Maths.get(i).getWords().size(); j++) {
                                    MWords mWords = Global.Maths.get(i).getWords().get(j);
                                    mWords.setContentId(Global.Maths.get(i).getMid());
                                    Global.MATH_words.add(mWords);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        saveMathContentsOfAllLevelToDb();
                        saveMathWordsToDb();
                        mathImageDownload();
                        mainMathSoundDownload();
                        MathSoundDownloadFromWords();
                        mathImageDownloadFromWords();

                        getBanglaContentData();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        B_URL = Global.ALTER_URL;
                        getMathContentData();
                        getBanglaContentData();
                    }
                }
        );
    }

    private void getBanglaMathContentData() {
        if (!Utils.isInternetOn(this)) {

            return;
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(B_URL + Global.API_BANGLA_MATH, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);


                        Global.BANGLA_MATH_words = new ArrayList<MWords>();

                        try {
                            MAllContent[] data = gson.fromJson(response.getJSONArray("contents").toString(), MAllContent[].class);
                            Global.BANGLA_Maths = new ArrayList<MAllContent>(Arrays.asList(data));
                            for (int i = 0; i < Global.BANGLA_Maths.size(); i++) {
                                Global.BANGLA_Maths.get(i).setPresentType(i + 1);
                                for (int j = 0; j < Global.BANGLA_Maths.get(i).getWords().size(); j++) {
                                    MWords mWords = Global.BANGLA_Maths.get(i).getWords().get(j);
                                    mWords.setContentId(Global.BANGLA_Maths.get(i).getMid());
                                    Global.BANGLA_MATH_words.add(mWords);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        saveBanglaMathContentsOfAllLevelToDb();
                        saveBanglaMathWordsToDb();
                        ongkoImageDownload();
                        ongkoImageDownloadFromWords();
                        mainOngkoSoundDownload();
                        OngkoSoundDownloadFromWords();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        B_URL = Global.ALTER_URL;
                        FilesDownload.getInstance(MainActivity.this, "").start();
                        Utils.log("s", "s");
                    }
                }
        );
    }

    // it will help to know phone is connected to internet or not
    // in this case ACCESS_NETWORK_STATE is needed as permission


    private void saveLevelToDb() {
        for (MLevel data : Global.levels) {
            database.addLevelFromJson(data);
        }
    }

    private void saveSubLevelToDb() {
        for (MSubLevel data2 : Global.mSubLevelArrayList) {
            database.addSubFromJsom(data2);
        }
    }

    private void saveEnglishContentsOfAllLevelToDb() {
        for (MAllContent mAllContent : Global.English) {
            database.addEnglishContentsFromJsom(mAllContent);
        }
    }

    private void saveBanglaContentsOfAllLevelToDb() {
        for (MAllContent mAllContent : Global.BANGLA) {
            database.addBanglaContentsFromJsom(mAllContent);
        }
    }

    private void saveMathContentsOfAllLevelToDb() {
        for (MAllContent mAllContent : Global.Maths) {
            database.addMathContentsFromJsom(mAllContent);
        }
    }

    private void saveBanglaMathContentsOfAllLevelToDb() {
        for (MAllContent mAllContent : Global.BANGLA_Maths) {
            database.addBanglaMathContentsFromJsom(mAllContent);
        }
    }

    private void saveEnglishWordsToDb() {
        for (MWords mWords : Global.English_words) {
            database.addEnglishWordsFromJsom(mWords);
        }
    }

    private void saveBanglaWordsToDb() {
        for (MWords mWords : Global.BANGLA_words) {
            database.addBanglaWordsFromJsom(mWords);
        }
    }


    private void saveMathWordsToDb() {
        for (MWords mWords : Global.MATH_words) {
            database.addMathWordsFromJsom(mWords);
        }
    }

    private void saveBanglaMathWordsToDb() {
        for (MWords mWords : Global.BANGLA_MATH_words) {
            database.addBanglaMathWordsFromJsom(mWords);
        }
    }

    private void banglaImageDownload() {
        int size = 0;
        if (Global.BANGLA.size() < Global.LEVEL_DOWNLOAD) {
            size = Global.BANGLA.size();
        } else {
            size = Global.LEVEL_DOWNLOAD;
        }

        FilesDownload filesDownload = FilesDownload.getInstance(this, image);
        for (int i = 0; i < size; i++) {
            filesDownload.addUrl(Global.IMAGE_URL + Global.BANGLA.get(i).getImg());

        }
    }

    private void mathImageDownload() {
        int size = 0;
        if (Global.Maths.size() < Global.LEVEL_DOWNLOAD) {
            size = Global.Maths.size();
        } else {
            size = Global.LEVEL_DOWNLOAD;
        }
        FilesDownload filesDownload = FilesDownload.getInstance(this, image);
        for (int i = 0; i < size; i++) {
            filesDownload.addUrl(Global.IMAGE_URL + Global.Maths.get(i).getImg());

        }
    }

    private void englishImageDownload() {
        int size = 0;
        if (Global.English.size() < Global.LEVEL_DOWNLOAD) {
            size = Global.English.size();
        } else {
            size = Global.LEVEL_DOWNLOAD;
        }
        for (int i = 0; i < size; i++) {
            FilesDownload.getInstance(this, image).addUrl(Global.IMAGE_URL + Global.English.get(i).getImg());

        }
    }


    private void ongkoImageDownload() {
        int size = 0;
        if (Global.BANGLA_Maths.size() < Global.LEVEL_DOWNLOAD) {
            size = Global.BANGLA_Maths.size();
        } else {
            size = Global.LEVEL_DOWNLOAD;
        }
        FilesDownload filesDownload = FilesDownload.getInstance(this, image);
        for (int i = 0; i < size; i++) {
            filesDownload.addUrl(Global.IMAGE_URL + Global.BANGLA_Maths.get(i).getImg());

        }
    }

    private void banglaSoundDownloadFromWords() {
        FilesDownload filesDownload = FilesDownload.getInstance(this, sounds);
        for (int i = 0; i < Global.BANGLA_words.size(); i++) {
            filesDownload.addUrl(Global.BASE_SOUND_URL + Global.BANGLA_words.get(i).getWsound());

        }
    }

    private void MathSoundDownloadFromWords() {
        FilesDownload filesDownload = FilesDownload.getInstance(this, sounds);
        for (int i = 0; i < Global.MATH_words.size(); i++) {
            filesDownload.addUrl(Global.BASE_SOUND_URL + Global.MATH_words.get(i).getWsound());

        }
    }

    private void EnglishSoundDownloadFromWords() {
        FilesDownload filesDownload = FilesDownload.getInstance(this, sounds);
        for (int i = 0; i < Global.English_words.size(); i++) {
            filesDownload.addUrl(Global.BASE_SOUND_URL + Global.English_words.get(i).getWsound());

        }
    }

    private void banglaImageDownloadFromWords() {
        int size = 0;
        if (Global.BANGLA_words.size() < Global.LEVEL_DOWNLOAD) {
            size = Global.BANGLA_words.size();
        } else {
            size = Global.LEVEL_DOWNLOAD;
        }
        FilesDownload filesDownload = FilesDownload.getInstance(this, image);
        for (int i = 0; i < size; i++) {
            filesDownload.addUrl(Global.IMAGE_URL + Global.BANGLA_words.get(i).getWimg());

        }
    }

    private void OngkoSoundDownloadFromWords() {
        FilesDownload filesDownload = FilesDownload.getInstance(this, sounds);
        for (int i = 0; i < Global.BANGLA_MATH_words.size(); i++) {
            filesDownload.addUrl(Global.BASE_SOUND_URL + Global.BANGLA_MATH_words.get(i).getWsound());

        }
        FilesDownload.getInstance(this, sounds).start();
    }

    private void mainOngkoSoundDownload() {
        FilesDownload filesDownload = FilesDownload.getInstance(this, sounds);
        for (int i = 0; i < Global.BANGLA_Maths.size(); i++) {
            filesDownload.addUrl(Global.BASE_SOUND_URL + Global.BANGLA_Maths.get(i).getAud());

        }
//        filesDownload.start();
    }

    private void mainBanglaSoundDownload() {
        FilesDownload filesDownload = FilesDownload.getInstance(this, sounds);
        for (int i = 0; i < Global.BANGLA.size(); i++) {
            filesDownload.addUrl(Global.BASE_SOUND_URL + Global.BANGLA.get(i).getAud());

        }
    }

    private void mainEnglishSoundDownload() {
        FilesDownload filesDownload = FilesDownload.getInstance(this, sounds);
        for (int i = 0; i < Global.English.size(); i++) {
            filesDownload.addUrl(Global.BASE_SOUND_URL + Global.English.get(i).getAud());

        }
    }

    private void mainMathSoundDownload() {
        FilesDownload filesDownload = FilesDownload.getInstance(this, sounds);
        for (int i = 0; i < Global.Maths.size(); i++) {
            filesDownload.addUrl(Global.BASE_SOUND_URL + Global.Maths.get(i).getAud());


        }
//        filesDownload.start();
    }

    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_game_exit);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final Button btnYes = (Button) dialog.findViewById(R.id.btnYes);
        final Button btnNO = (Button) dialog.findViewById(R.id.btnNo);
        TextView diaTxt = (TextView) dialog.findViewById(R.id.diaTxt);
        TextView diaTxt2 = (TextView) dialog.findViewById(R.id.diaTxt2);
        Utils.setFont(this, "carterone", btnNO, btnYes, diaTxt, diaTxt2);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        btnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        mQuestions = database.getQuesData();
        Global.lPopUp = mQuestions.getlPopUp();
        if (levelsDatas.size() == 0) {
            Utils.toastMassage(this, "No Data");
            return;

        }
        if (v.getId() == R.id.btnBangla) {
            lPopUp++;
            mQuestions.setlPopUp(lPopUp);
            Intent intent = new Intent(MainActivity.this, SubLevelActivity.class);
            intent.putExtra("id", 1);
            intent.putExtra("name", "বাংলা  ");
            intent.putExtra("lPop", lPopUp);
            startActivity(intent);
        } else if (v.getId() == R.id.btnBanglaMath) {
            lPopUp2++;
            Intent intent = new Intent(MainActivity.this, SubLevelActivity.class);
            intent.putExtra("id", 2);
            intent.putExtra("name", "অংক ");
            intent.putExtra("lPop", lPopUp2);
            startActivity(intent);
        } else if (v.getId() == R.id.btnEnglish) {
            lPopUp3++;
            Intent intent = new Intent(MainActivity.this, SubLevelActivity.class);
            intent.putExtra("id", 3);
            intent.putExtra("name", "english ");
            intent.putExtra("lPop", lPopUp3);
            startActivity(intent);
        } else if (v.getId() == R.id.btnMath) {
            lPopUp4++;
            Intent intent = new Intent(MainActivity.this, SubLevelActivity.class);
            intent.putExtra("id", 4);
            intent.putExtra("name", "Math ");
            intent.putExtra("lPop", lPopUp4);
            startActivity(intent);
        } else if (v.getId() == R.id.btnSetting) {
            DialogSoundOnOff.dialogShow(this);

        } else if (v.getId() == R.id.btnResult) {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dia_result);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView txtBanglaPoint = (TextView) dialog.findViewById(R.id.txtBanglaPoint);
            TextView txtEnglishPoint = (TextView) dialog.findViewById(R.id.txtEnglishPoint);
            TextView txtBMPoint = (TextView) dialog.findViewById(R.id.txtBanglaMathPoint);
            TextView txtMathPoint = (TextView) dialog.findViewById(R.id.txtMathPoint);
            TextView txtBangla = (TextView) dialog.findViewById(R.id.txtBangla);
            TextView txtEng = (TextView) dialog.findViewById(R.id.txtEnglish1);
            TextView txtBMath = (TextView) dialog.findViewById(R.id.txtBanglaMa);
            TextView txtMath = (TextView) dialog.findViewById(R.id.txtMath1);
            txtBangla.setTextColor(0xff00ff00);
            txtBanglaPoint.setTextColor(0xff00ff00);
            txtEng.setTextColor(0xffff0000);
            txtEnglishPoint.setTextColor(0xffff0000);
            txtBMath.setTextColor(0xffffff00);
            txtBMPoint.setTextColor(0xffffff00);
            txtMath.setTextColor(0xffff00ff);
            txtMathPoint.setTextColor(0xffff00ff);
            int totalBPoint = database.getLockTotalPointData(1);
            txtBanglaPoint.setText(totalBPoint + "");
            int totalEPoint = database.getLockTotalPointData(3);
            txtEnglishPoint.setText(totalEPoint + "");
            int totalMPoint = database.getLockTotalPointData(4);
            txtMathPoint.setText(totalMPoint + "");
            int totalBMPoint = database.getLockTotalPointData(2);
            txtBMPoint.setText(totalBMPoint + "");
            dialog.show();

        }

    }
}
