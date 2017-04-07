package com.example.nayan.gameverson2.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.nayan.gameverson2.model.MAllContent;
import com.example.nayan.gameverson2.model.MContents;
import com.example.nayan.gameverson2.model.MItem;
import com.example.nayan.gameverson2.model.MLevel;
import com.example.nayan.gameverson2.model.MLock;
import com.example.nayan.gameverson2.model.MPoint;
import com.example.nayan.gameverson2.model.MQuestions;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.model.MWords;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by NAYAN on 8/24/2016.
 */
public class DatabaseHelper {
    private static final String DATABASE_NAME = "game.db";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_LEVEL_TABLE = "level";
    private static final String DATABASE_CONTENTS_TABLE = "contents";
    private static final String DATABASE_LOCK_TABLE = "lock_tb";
    private static final String DATABASE_SUB_LEVEL_TABLE = "sub";
    private static final String DATABASE_POPUP_TABLE = "ques";
    private static final String DATABASE_OPTION_TABLE = "option";
    private static final String DATABASE_POINT_TABLE = "point_tb";
    private static final String DATABASE_WORDS_TABLE = "words_tb";
    private static final String DATABASE_WORDS_TABLE_BANGLA = "bangla_words_tb";
    private static final String DATABASE_WORDS_TABLE_MATH = "math_words_tb";
    private static final String DATABASE_WORDS_TABLE_BANGLA_MATH = "bangla_math_words_tb";
    private static final String DATABASE_ENGLISH_CONTENTS_TABLE = "english_contents_tb";
    private static final String DATABASE_BANGLA_CONTENTS_TABLE = "bangla_contents_tb";
    private static final String DATABASE_MATH_CONTENTS_TABLE = "math_contents_tb";
    private static final String DATABASE_BANGLA_MATH_CONTENTS_TABLE = "bangla_math_contents_tb";
    private static final String KEY_WORDS_ID = "words_id";
    private static final String KEY_WORDS_CONTENTS_ID = "words_contents_id";
    private static final String KEY_WORDS_LETTER = "words_letter";
    private static final String KEY_WORDS_Word = "words_word";
    private static final String KEY_WORDS_IMG = "words_img";
    private static final String KEY_WORDS_SOUND = "words_sound";
    private static final String KEY_POINT_ID = "point_id";
    private static final String KEY_HOW_TO = "how_to";
    private static final String KEY_POPUP = "pop_up";
    private static final String KEY_POPUP2 = "pop_up2";
    private static final String KEY_POPUP3 = "pop_up3";
    private static final String KEY_POPUP4 = "pop_up4";
    private static final String KEY_POPUP5 = "pop_up5";
    private static final String KEY_POPUP6 = "pop_up6";
    private static final String KEY_POPUP7 = "pop_up7";
    private static final String KEY_POPUP8 = "pop_up8";
    private static final String KEY_POPUP9 = "pop_up9";
    private static final String KEY_POPUP10 = "pop_up10";
    private static final String KEY_POPUP11 = "pop_up11";
    private static final String KEY_PRESENT_POINT = "present_point";
    private static final String KEY_UPDATE_DATE = "update_date";
    private static final String KEY_TOTAL_S_LEVEL = "total_slevel";
    private static final String KEY_DIFFICULTY = "difficulty";
    private static final String KEY_LEVEL_ID = "lid";
    private static final String KEY_SUB_LEVEL_ID = "s_lid";
    private static final String KEY_ITEM = "item";
    private static final String KEY_TAG = "tag";
    private static final String KEY_MODEL_ID = "mid";
    private static final String KEY_LOCK_ID = "loid";
    private static final String KEY_UNLOCK = "un_lock";
    private static final String KEY_TOTAL_POINT = "lock_total_point";
    private static final String KEY_ALL_TOTAL_POINT = "lock_all_total_point";
    private static final String KEY_Q_ID = "qid";
    private static final String KEY_OP_ID = "opid";
    private static final String KEY_PARENT_ID = "pid";
    private static final String KEY_PARENT_NAME = "pNm";
    private static final String KEY_QUES = "question";
    private static final String KEY_SEN = "sen";
    private static final String KEY_IMAGE = "img";
    private static final String KEY_SOUNDS = "aud";
    private static final String KEY_NAME = "name";
    private static final String KEY_VIDEO = "vid";
    private static final String KEY_TEXT = "txt";
    private static final String KEY_COINS_PRICE = "coins_price";
    private static final String KEY_NO_OF_COINS = "no_of_coins";
    private static final String KEY_LOGIC = "k_Logic";
    private static final String KEY_PRESENT_ID = "present_id";
    private static final String KEY_PRESENT_TYPE = "present_type";
    private static final String KEY_POINT = "best_point";
    private static final String KEY_LEVEL_WIN_COUNT = "win_count";
    private static final String DATABASE_CREATE_LEVEL_TABLE = "create table if not exists "
            + DATABASE_LEVEL_TABLE + "("
            + KEY_LEVEL_ID + " integer primary key, "
            + KEY_NAME + " text, "
            + KEY_UPDATE_DATE + " text, "
            + KEY_HOW_TO + " text, "
            + KEY_DIFFICULTY + " integer, "
            + KEY_POINT + " integer, "
            + KEY_LEVEL_WIN_COUNT + " integer, "
            + KEY_TOTAL_S_LEVEL + " text)";
    private static final String DATABASE_CREATE_CONTENTS_TABLE = "create table if not exists "
            + DATABASE_CONTENTS_TABLE + "("
            + KEY_LEVEL_ID + " integer, "
            + KEY_MODEL_ID + " integer primary key, "
            + KEY_PRESENT_ID + " integer, "
            + KEY_PRESENT_TYPE + " integer, "
            + KEY_IMAGE + " text, "
            + KEY_SEN + " text, "
            + KEY_TEXT + " text, "
            + KEY_VIDEO + " text, "
            + KEY_SOUNDS + " text)";
    private static final String DATABASE_CREATE_CONTENTS_OF_ENGLISH_TABLE = "create table if not exists "
            + DATABASE_ENGLISH_CONTENTS_TABLE + "("
            + KEY_LEVEL_ID + " integer, "
            + KEY_MODEL_ID + " integer primary key, "
            + KEY_PRESENT_ID + " integer, "
            + KEY_PRESENT_TYPE + " integer, "
            + KEY_IMAGE + " text, "
            + KEY_SEN + " text, "
            + KEY_TEXT + " text, "
            + KEY_VIDEO + " text, "
            + KEY_SOUNDS + " text)";
    private static final String DATABASE_CREATE_CONTENTS_OF_BANGLA_TABLE = "create table if not exists "
            + DATABASE_BANGLA_CONTENTS_TABLE + "("
            + KEY_LEVEL_ID + " integer, "
            + KEY_MODEL_ID + " integer primary key, "
            + KEY_PRESENT_ID + " integer, "
            + KEY_PRESENT_TYPE + " integer, "
            + KEY_IMAGE + " text, "
            + KEY_SEN + " text, "
            + KEY_TEXT + " text, "
            + KEY_VIDEO + " text, "
            + KEY_SOUNDS + " text)";
    private static final String DATABASE_CREATE_CONTENTS_OF_MATH_TABLE = "create table if not exists "
            + DATABASE_MATH_CONTENTS_TABLE + "("
            + KEY_LEVEL_ID + " integer, "
            + KEY_MODEL_ID + " integer primary key, "
            + KEY_PRESENT_ID + " integer, "
            + KEY_PRESENT_TYPE + " integer, "
            + KEY_IMAGE + " text, "
            + KEY_SEN + " text, "
            + KEY_TEXT + " text, "
            + KEY_VIDEO + " text, "
            + KEY_SOUNDS + " text)";
    private static final String DATABASE_CREATE_CONTENTS_OF_BANGLA_MATH_TABLE = "create table if not exists "
            + DATABASE_BANGLA_MATH_CONTENTS_TABLE + "("
            + KEY_LEVEL_ID + " integer, "
            + KEY_MODEL_ID + " integer primary key, "
            + KEY_PRESENT_ID + " integer, "
            + KEY_PRESENT_TYPE + " integer, "
            + KEY_IMAGE + " text, "
            + KEY_SEN + " text, "
            + KEY_TEXT + " text, "
            + KEY_VIDEO + " text, "
            + KEY_SOUNDS + " text)";
    private static final String DATABASE_CREATE_SUB_LEVEL_TABLE = "create table if not exists "
            + DATABASE_SUB_LEVEL_TABLE + "("
            + KEY_SUB_LEVEL_ID + " integer primary key, "
            + KEY_NAME + " text, "
            + KEY_PARENT_ID + " integer, "
            + KEY_LOGIC + " integer, "
            + KEY_UNLOCK + " integer, "
            + KEY_POINT + " integer, "
            + KEY_PARENT_NAME + " text, "
            + KEY_HOW_TO + " text, "
            + KEY_COINS_PRICE + " text, "
            + KEY_NO_OF_COINS + " text)";
    private static final String DATABASE_CREATE_WORDS_TABLE = "create table if not exists "
            + DATABASE_WORDS_TABLE + "("
            + KEY_WORDS_ID + " integer primary key, "
            + KEY_WORDS_CONTENTS_ID + " integer, "
            + KEY_WORDS_IMG + " text, "
            + KEY_WORDS_LETTER + " text, "
            + KEY_WORDS_SOUND + " text, "
            + KEY_WORDS_Word + " text)";
    private static final String DATABASE_CREATE_WORDS_TABLE_BANGLA = "create table if not exists "
            + DATABASE_WORDS_TABLE_BANGLA + "("
            + KEY_WORDS_ID + " integer primary key, "
            + KEY_WORDS_CONTENTS_ID + " integer, "
            + KEY_WORDS_IMG + " text, "
            + KEY_WORDS_LETTER + " text, "
            + KEY_WORDS_SOUND + " text, "
            + KEY_WORDS_Word + " text)";
    private static final String DATABASE_CREATE_WORDS_TABLE_MATH = "create table if not exists "
            + DATABASE_WORDS_TABLE_MATH + "("
            + KEY_WORDS_ID + " integer primary key, "
            + KEY_WORDS_CONTENTS_ID + " integer, "
            + KEY_WORDS_IMG + " text, "
            + KEY_WORDS_LETTER + " text, "
            + KEY_WORDS_SOUND + " text, "
            + KEY_WORDS_Word + " text)";
    private static final String DATABASE_CREATE_WORDS_TABLE_BANGLA_MATH = "create table if not exists "
            + DATABASE_WORDS_TABLE_BANGLA_MATH + "("
            + KEY_WORDS_ID + " integer primary key, "
            + KEY_WORDS_CONTENTS_ID + " integer, "
            + KEY_WORDS_IMG + " text, "
            + KEY_WORDS_LETTER + " text, "
            + KEY_WORDS_SOUND + " text, "
            + KEY_WORDS_Word + " text)";
    private static final String DATABASE_CREATE_LOCK_TABLE = "create table if not exists "
            + DATABASE_LOCK_TABLE + "("
            + KEY_LOCK_ID + " integer primary key autoincrement, "
            + KEY_POINT + " integer, "
            + KEY_UNLOCK + " integer, "
            + KEY_TOTAL_POINT + " integer, "
            + KEY_LEVEL_ID + " integer, "
            + KEY_SUB_LEVEL_ID + " integer)";
    private static final String DATABASE_CREATE_OPTION_TABLE = "create table if not exists "
            + DATABASE_OPTION_TABLE + "("
            + KEY_OP_ID + " integer primary key autoincrement , "
            + KEY_ITEM + " integer, "
            + KEY_TAG + " integer)";
    private static final String DATABASE_CREATE_POPUP_TABLE = "create table if not exists "
            + DATABASE_POPUP_TABLE + "("
            + KEY_Q_ID + " integer primary key, "
            + KEY_POPUP + " integer, "
            + KEY_POPUP2 + " integer, "
            + KEY_POPUP3 + " integer, "
            + KEY_POPUP4 + " integer, "
            + KEY_POPUP5 + " integer, "
            + KEY_POPUP6 + " integer, "
            + KEY_POPUP7 + " integer, "
            + KEY_POPUP8 + " integer, "
            + KEY_POPUP9 + " integer, "
            + KEY_POPUP10 + " integer, "
            + KEY_POPUP11 + " integer, "
            + KEY_QUES + " text)";
    private static final String DATABASE_CREATE_POINT_TABLE = "create table if not exists "
            + DATABASE_POINT_TABLE + "("
            + KEY_PRESENT_POINT + " integer, "
            + KEY_POINT_ID + " integer primary key, "
            + KEY_POINT + " integer)";
    private static DatabaseHelper instance;
    private static SQLiteDatabase db;
    private final String TAG = getClass().getSimpleName();
    private final String PASS = Utils.databasePassKey("nayan5565@gmail.com", "As us");
    private Context context;

    public DatabaseHelper(Context context) {

        this.context = context;
        instance = this;
        openDB(context);
        onCreate();
    }

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    private void openDB(Context context) {
        SQLiteDatabase.loadLibs(context);
        File databaseFile = context.getDatabasePath(DATABASE_NAME);
        if (!databaseFile.exists()) {
            databaseFile.mkdirs();
            databaseFile.delete();
        }
        if (db == null)
            db = SQLiteDatabase.openOrCreateDatabase(databaseFile, PASS, null);
    }


    public void onCreate() {
        db.execSQL(DATABASE_CREATE_LEVEL_TABLE);
        db.execSQL(DATABASE_CREATE_CONTENTS_TABLE);
        db.execSQL(DATABASE_CREATE_SUB_LEVEL_TABLE);
        db.execSQL(DATABASE_CREATE_LOCK_TABLE);
        db.execSQL(DATABASE_CREATE_POPUP_TABLE);
        db.execSQL(DATABASE_CREATE_OPTION_TABLE);
        db.execSQL(DATABASE_CREATE_POINT_TABLE);
        db.execSQL(DATABASE_CREATE_WORDS_TABLE);
        db.execSQL(DATABASE_CREATE_WORDS_TABLE_BANGLA);
        db.execSQL(DATABASE_CREATE_WORDS_TABLE_MATH);
        db.execSQL(DATABASE_CREATE_WORDS_TABLE_BANGLA_MATH);
        db.execSQL(DATABASE_CREATE_CONTENTS_OF_ENGLISH_TABLE);
        db.execSQL(DATABASE_CREATE_CONTENTS_OF_BANGLA_TABLE);
        db.execSQL(DATABASE_CREATE_CONTENTS_OF_MATH_TABLE);
        db.execSQL(DATABASE_CREATE_CONTENTS_OF_BANGLA_MATH_TABLE);

    }

    public void addLevelFromJson(MLevel mLevel) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, mLevel.getName());
            values.put(KEY_LEVEL_ID, mLevel.getLid());
            values.put(KEY_UPDATE_DATE, mLevel.getUpdate_date());
            values.put(KEY_HOW_TO, mLevel.getHowto());
//            values.put(KEY_DIFFICULTY, mLevel.getDifficulty());
            values.put(KEY_TOTAL_S_LEVEL, mLevel.getTotal_slevel());
//            if (mLevel.getBestpoint() > 0) {
//                values.put(KEY_POINT, mLevel.getBestpoint());
//            }

            if (mLevel.getLevelWinCount() > 0) {
                values.put(KEY_LEVEL_WIN_COUNT, mLevel.getLevelWinCount());
            }

            String sql = "select * from " + DATABASE_LEVEL_TABLE + " where " + KEY_LEVEL_ID + "='" + mLevel.getLid() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                int update = db.update(DATABASE_LEVEL_TABLE, values, KEY_LEVEL_ID + "=?", new String[]{mLevel.getLid() + ""});
                Log.e("log", "update : " + mLevel.getLid());
            } else {
                long v = db.insert(DATABASE_LEVEL_TABLE, null, values);
                Log.e("log", "return : " + v);

            }


        } catch (Exception e) {

        }
        if (cursor != null)
            cursor.close();
    }

    public void addSubFromJsom(MSubLevel mSubLevel) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_SUB_LEVEL_ID, mSubLevel.getLid());
            values.put(KEY_PARENT_ID, mSubLevel.getParentId());
            values.put(KEY_UNLOCK, mSubLevel.getUnlockNextLevel());
            values.put(KEY_POINT, mSubLevel.getBestPoint());
            values.put(KEY_PARENT_NAME, mSubLevel.getParentName());
            values.put(KEY_NAME, mSubLevel.getName());
            values.put(KEY_HOW_TO, mSubLevel.getHowto());
            values.put(KEY_COINS_PRICE, mSubLevel.getCoins_price());
            values.put(KEY_NO_OF_COINS, mSubLevel.getNo_of_coins());
            values.put(KEY_LOGIC, mSubLevel.getLogic());

            String sql = "select * from " + DATABASE_SUB_LEVEL_TABLE + " where " + KEY_SUB_LEVEL_ID + "='" + mSubLevel.getLid() + "'";
            cursor = db.rawQuery(sql, null);
            Log.e("cu", "has" + cursor);
            if (cursor != null && cursor.moveToFirst()) {
                int update = db.update(DATABASE_SUB_LEVEL_TABLE, values, KEY_SUB_LEVEL_ID + "=?", new String[]{mSubLevel.getLid() + ""});
                Log.e("sublevel", "sub level update : " + update);
            } else {
                long v = db.insert(DATABASE_SUB_LEVEL_TABLE, null, values);
                Log.e("log", "sub level insert : " + v);

            }


        } catch (Exception e) {

        }
        if (cursor != null)
            cursor.close();
    }

    public void addEnglishWordsFromJsom(MWords mWords) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_WORDS_ID, mWords.getWid());
            values.put(KEY_WORDS_CONTENTS_ID, mWords.getContentId());
            values.put(KEY_WORDS_IMG, mWords.getWimg());
            values.put(KEY_WORDS_LETTER, mWords.getWletter());
            values.put(KEY_WORDS_Word, mWords.getWword());
            values.put(KEY_WORDS_SOUND, mWords.getWsound());


            String sql = "select * from " + DATABASE_WORDS_TABLE + " where " + KEY_WORDS_ID + "='" + mWords.getWid() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                int update = db.update(DATABASE_WORDS_TABLE, values, KEY_WORDS_ID + "=?", new String[]{mWords.getWid() + ""});
                Log.e("log", "sub level update : " + update);
            } else {
                long v = db.insert(DATABASE_WORDS_TABLE, null, values);
                Log.e("log", "sub level insert : " + v);

            }


        } catch (Exception e) {

        }
        if (cursor != null)
            cursor.close();
    }

    public void addBanglaWordsFromJsom(MWords mWords) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_WORDS_ID, mWords.getWid());
            values.put(KEY_WORDS_CONTENTS_ID, mWords.getContentId());
            values.put(KEY_WORDS_IMG, mWords.getWimg());
            values.put(KEY_WORDS_LETTER, mWords.getWletter());
            values.put(KEY_WORDS_Word, mWords.getWword());
            values.put(KEY_WORDS_SOUND, mWords.getWsound());


            String sql = "select * from " + DATABASE_WORDS_TABLE_BANGLA + " where " + KEY_WORDS_ID + "='" + mWords.getWid() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                int update = db.update(DATABASE_WORDS_TABLE_BANGLA, values, KEY_WORDS_ID + "=?", new String[]{mWords.getWid() + ""});
                Log.e("log", "sub level update : " + update);
            } else {
                long v = db.insert(DATABASE_WORDS_TABLE_BANGLA, null, values);
                Log.e("log", "sub level insert : " + v);

            }


        } catch (Exception e) {

        }
        if (cursor != null)
            cursor.close();
    }

    public void addMathWordsFromJsom(MWords mWords) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_WORDS_ID, mWords.getWid());
            values.put(KEY_WORDS_CONTENTS_ID, mWords.getContentId());
            values.put(KEY_WORDS_IMG, mWords.getWimg());
            values.put(KEY_WORDS_LETTER, mWords.getWletter());
            values.put(KEY_WORDS_Word, mWords.getWword());
            values.put(KEY_WORDS_SOUND, mWords.getWsound());


            String sql = "select * from " + DATABASE_WORDS_TABLE_MATH + " where " + KEY_WORDS_ID + "='" + mWords.getWid() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                int update = db.update(DATABASE_WORDS_TABLE_MATH, values, KEY_WORDS_ID + "=?", new String[]{mWords.getWid() + ""});
                Log.e("log", "math words update : " + update);
            } else {
                long v = db.insert(DATABASE_WORDS_TABLE_MATH, null, values);
                Log.e("log", "math words insert : " + v);

            }


        } catch (Exception e) {

        }
        if (cursor != null)
            cursor.close();
    }

    public void addBanglaMathWordsFromJsom(MWords mWords) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_WORDS_ID, mWords.getWid());
            values.put(KEY_WORDS_CONTENTS_ID, mWords.getContentId());
            values.put(KEY_WORDS_IMG, mWords.getWimg());
            values.put(KEY_WORDS_LETTER, mWords.getWletter());
            values.put(KEY_WORDS_Word, mWords.getWword());
            values.put(KEY_WORDS_SOUND, mWords.getWsound());


            String sql = "select * from " + DATABASE_WORDS_TABLE_BANGLA_MATH + " where " + KEY_WORDS_ID + "='" + mWords.getWid() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                int update = db.update(DATABASE_WORDS_TABLE_BANGLA_MATH, values, KEY_WORDS_ID + "=?", new String[]{mWords.getWid() + ""});
                Log.e("log", "math words update : " + update);
            } else {
                long v = db.insert(DATABASE_WORDS_TABLE_BANGLA_MATH, null, values);
                Log.e("log", "math words insert : " + v);

            }


        } catch (Exception e) {

        }
        if (cursor != null)
            cursor.close();
    }

    public void addContentsFromJsom(MContents mContents) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_LEVEL_ID, mContents.getLid());
            values.put(KEY_MODEL_ID, mContents.getMid());
            values.put(KEY_IMAGE, mContents.getImg());
            values.put(KEY_TEXT, mContents.getTxt());
            values.put(KEY_SOUNDS, mContents.getAud());
            values.put(KEY_PRESENT_TYPE, mContents.getPresentType());
            values.put(KEY_VIDEO, mContents.getVid());
            values.put(KEY_SEN, mContents.getSen());
            values.put(KEY_PRESENT_ID, mContents.getPresentId());

            String sql = "select * from " + DATABASE_CONTENTS_TABLE + " where " + KEY_MODEL_ID + "='" + mContents.getMid() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                int update = db.update(DATABASE_CONTENTS_TABLE, values, KEY_MODEL_ID + "=?", new String[]{mContents.getMid() + ""});
                Log.e("updateMath", "content update : " + update);
            } else {
                long v = db.insert(DATABASE_CONTENTS_TABLE, null, values);
                Log.e("insetMath", "content insert : " + v);

            }


        } catch (Exception e) {

        }

        if (cursor != null)
            cursor.close();
    }

    public void addEnglishContentsFromJsom(MAllContent mAllContent) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_LEVEL_ID, mAllContent.getLid());
            values.put(KEY_MODEL_ID, mAllContent.getMid());
            values.put(KEY_IMAGE, mAllContent.getImg());
            values.put(KEY_TEXT, mAllContent.getTxt());
            values.put(KEY_SOUNDS, mAllContent.getAud());
            values.put(KEY_PRESENT_TYPE, mAllContent.getPresentType());
            values.put(KEY_VIDEO, mAllContent.getVid());
            values.put(KEY_SEN, mAllContent.getSen());
            values.put(KEY_PRESENT_ID, mAllContent.getPresentId());

            String sql = "select * from " + DATABASE_ENGLISH_CONTENTS_TABLE + " where " + KEY_MODEL_ID + "='" + mAllContent.getMid() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                int update = db.update(DATABASE_ENGLISH_CONTENTS_TABLE, values, KEY_MODEL_ID + "=?", new String[]{mAllContent.getMid() + ""});
                Log.e("log", "content update : " + update);
            } else {
                long v = db.insert(DATABASE_ENGLISH_CONTENTS_TABLE, null, values);
                Log.e("log", "content insert : " + v);

            }


        } catch (Exception e) {

        }

        if (cursor != null)
            cursor.close();
    }

    public void addBanglaContentsFromJsom(MAllContent mAllContent) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_LEVEL_ID, mAllContent.getLid());
            values.put(KEY_MODEL_ID, mAllContent.getMid());
            values.put(KEY_IMAGE, mAllContent.getImg());
            values.put(KEY_TEXT, mAllContent.getTxt());
            values.put(KEY_SOUNDS, mAllContent.getAud());
            values.put(KEY_PRESENT_TYPE, mAllContent.getPresentType());
            values.put(KEY_VIDEO, mAllContent.getVid());
            values.put(KEY_SEN, mAllContent.getSen());
            values.put(KEY_PRESENT_ID, mAllContent.getPresentId());

            String sql = "select * from " + DATABASE_BANGLA_CONTENTS_TABLE + " where " + KEY_MODEL_ID + "='" + mAllContent.getMid() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                int update = db.update(DATABASE_BANGLA_CONTENTS_TABLE, values, KEY_MODEL_ID + "=?", new String[]{mAllContent.getMid() + ""});
                Log.e("log", "content update : " + update);
            } else {
                long v = db.insert(DATABASE_BANGLA_CONTENTS_TABLE, null, values);
                Log.e("log", "content insert : " + v);

            }


        } catch (Exception e) {

        }

        if (cursor != null)
            cursor.close();
    }

    public void addMathContentsFromJsom(MAllContent mAllContent) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_LEVEL_ID, mAllContent.getLid());
            values.put(KEY_MODEL_ID, mAllContent.getMid());
            values.put(KEY_IMAGE, mAllContent.getImg());
            values.put(KEY_TEXT, mAllContent.getTxt());
            values.put(KEY_SOUNDS, mAllContent.getAud());
            values.put(KEY_PRESENT_TYPE, mAllContent.getPresentType());
            values.put(KEY_VIDEO, mAllContent.getVid());
            values.put(KEY_SEN, mAllContent.getSen());
            values.put(KEY_PRESENT_ID, mAllContent.getPresentId());

            String sql = "select * from " + DATABASE_MATH_CONTENTS_TABLE + " where " + KEY_MODEL_ID + "='" + mAllContent.getMid() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                int update = db.update(DATABASE_MATH_CONTENTS_TABLE, values, KEY_MODEL_ID + "=?", new String[]{mAllContent.getMid() + ""});
                Log.e("log", "content update : " + update);
            } else {
                long v = db.insert(DATABASE_MATH_CONTENTS_TABLE, null, values);
                Log.e("log", "content insert : " + v);

            }


        } catch (Exception e) {

        }

        if (cursor != null)
            cursor.close();
    }

    public void addBanglaMathContentsFromJsom(MAllContent mAllContent) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_LEVEL_ID, mAllContent.getLid());
            values.put(KEY_MODEL_ID, mAllContent.getMid());
            values.put(KEY_IMAGE, mAllContent.getImg());
            values.put(KEY_TEXT, mAllContent.getTxt());
            values.put(KEY_SOUNDS, mAllContent.getAud());
            values.put(KEY_PRESENT_TYPE, mAllContent.getPresentType());
            values.put(KEY_VIDEO, mAllContent.getVid());
            values.put(KEY_SEN, mAllContent.getSen());
            values.put(KEY_PRESENT_ID, mAllContent.getPresentId());

            String sql = "select * from " + DATABASE_BANGLA_MATH_CONTENTS_TABLE + " where " + KEY_MODEL_ID + "='" + mAllContent.getMid() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                int update = db.update(DATABASE_BANGLA_MATH_CONTENTS_TABLE, values, KEY_MODEL_ID + "=?", new String[]{mAllContent.getMid() + ""});
                Log.e("log", "content update : " + update);
            } else {
                long v = db.insert(DATABASE_BANGLA_MATH_CONTENTS_TABLE, null, values);
                Log.e("log", "content insert : " + v);

            }


        } catch (Exception e) {

        }

        if (cursor != null)
            cursor.close();
    }

    public void addLockData(MLock mLock) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_LEVEL_ID, mLock.getLevel_id());
            values.put(KEY_SUB_LEVEL_ID, mLock.getSub_level_id());
            values.put(KEY_POINT, mLock.getBestPoint());
            values.put(KEY_TOTAL_POINT, mLock.getTotal_pont());
            values.put(KEY_UNLOCK, mLock.getUnlockNextLevel());

            String sql = "select * from " + DATABASE_LOCK_TABLE + " where " + KEY_LEVEL_ID + "='" + mLock.getLevel_id()
                    + "' AND " + KEY_SUB_LEVEL_ID + "='" + mLock.getSub_level_id() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.getCount() > 0) {
                int update = db.update(DATABASE_LOCK_TABLE, values, KEY_LEVEL_ID + "=? AND " + KEY_SUB_LEVEL_ID + "=?", new String[]{mLock.getLevel_id() + "", mLock.getSub_level_id() + ""});
                Log.e("DB", "mlockUpdae:" + update);
            } else {
                long v = db.insert(DATABASE_LOCK_TABLE, null, values);
                Log.e("DB", "mlockAdded:" + v);

            }


        } catch (Exception e) {
            Log.e("ERR", "mlock:" + e.toString());
        }

        if (cursor != null)
            cursor.close();
    }

    public void addPointData(MPoint mPoint) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_POINT_ID, mPoint.getpId());
            values.put(KEY_PRESENT_POINT, mPoint.getPresentPoint());
            values.put(KEY_POINT, mPoint.getBestPoint());

            String sql = "select * from " + DATABASE_POINT_TABLE + " where " + KEY_POINT_ID + "='" + mPoint.getpId() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                int update = db.update(DATABASE_POINT_TABLE, values, KEY_POINT_ID + "=?", new String[]{mPoint.getpId() + ""});
                Log.e("log", "content update : " + update);
            } else {
                long v = db.insert(DATABASE_POINT_TABLE, null, values);
                Log.e("log", "content insert : " + v);

            }


        } catch (Exception e) {

        }

        if (cursor != null)
            cursor.close();
    }

    public void addQuesData(MQuestions mQuestions) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_POPUP, mQuestions.getPopUp());
            values.put(KEY_POPUP2, mQuestions.getPopUp2());
            values.put(KEY_POPUP3, mQuestions.getPopUp3());
            values.put(KEY_POPUP4, mQuestions.getPopUp4());
            values.put(KEY_POPUP5, mQuestions.getPopUp5());
            values.put(KEY_POPUP6, mQuestions.getPopUp6());
            values.put(KEY_POPUP7, mQuestions.getPopUp7());
            values.put(KEY_POPUP8, mQuestions.getPopUp8());
            values.put(KEY_POPUP9, mQuestions.getPopUp9());
            values.put(KEY_POPUP10, mQuestions.getPopUp10());
            values.put(KEY_POPUP11, mQuestions.getPopUp11());
            values.put(KEY_Q_ID, mQuestions.getId());

            String sql = "select * from " + DATABASE_POPUP_TABLE + " where " + KEY_Q_ID + "='" + mQuestions.getId() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                int update = db.update(DATABASE_POPUP_TABLE, values, KEY_Q_ID + "=?", new String[]{mQuestions.getId() + ""});
                Log.e("ques", "content update : " + update);
            } else {
                long v = db.insert(DATABASE_POPUP_TABLE, null, values);
                Log.e("ques", "content insert : " + v);

            }


        } catch (Exception e) {

        }

        if (cursor != null)
            cursor.close();
    }

    public void addOptonData(MItem mItem) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_ITEM, mItem.getItem());
            values.put(KEY_TAG, mItem.getTag());

            String sql = "select * from " + DATABASE_POPUP_TABLE;
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                int update = db.update(DATABASE_OPTION_TABLE, values, KEY_OP_ID + "=?", new String[]{mItem.getId() + ""});
                Log.e("log", "content update : " + update);
            } else {
                long v = db.insert(DATABASE_OPTION_TABLE, null, values);
                Log.e("log", "content insert : " + v);

            }


        } catch (Exception e) {

        }

        if (cursor != null)
            cursor.close();
    }


    public ArrayList<MLevel> getLevelData(int id) {
        ArrayList<MLevel> levelArrayList = new ArrayList<>();

        MLevel mLevel;
        String sql = "select * from " + DATABASE_LEVEL_TABLE + " where " + KEY_LEVEL_ID + "='" + id + "'";
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("cursor", "count :" + cursor.getCount());
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Log.e("do", "start");
                mLevel = new MLevel();
                mLevel.setLid(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_ID)));
                mLevel.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                mLevel.setUpdate_date(cursor.getString(cursor.getColumnIndex(KEY_UPDATE_DATE)));
                mLevel.setTotal_slevel(cursor.getString(cursor.getColumnIndex(KEY_TOTAL_S_LEVEL)));
                mLevel.setHowto(cursor.getString(cursor.getColumnIndex(KEY_HOW_TO)));
                mLevel.setLevelWinCount(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_WIN_COUNT)));
                levelArrayList.add(mLevel);
                Log.e("do", "end");
            } while (cursor.moveToNext());


        }
        cursor.close();


        return levelArrayList;
    }

    public ArrayList<MLevel> getLevelAllData() {
        ArrayList<MLevel> levelArrayList = new ArrayList<>();

        MLevel mLevel;
        String sql = "select * from " + DATABASE_LEVEL_TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("cursor", "count :" + cursor.getCount());
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Log.e("do", "start");
                mLevel = new MLevel();
//                mLevel.setLid(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_ID)));
//                mLevel.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
//                mLevel.setUpdate_date(cursor.getString(cursor.getColumnIndex(KEY_UPDATE_DATE)));
                mLevel.setTotal_slevel(cursor.getString(cursor.getColumnIndex(KEY_TOTAL_S_LEVEL)));
//                mLevel.setHowto(cursor.getString(cursor.getColumnIndex(KEY_HOW_TO)));
//                mLevel.setLevelWinCount(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_WIN_COUNT)));
                levelArrayList.add(mLevel);
                Log.e("do", "end");
            } while (cursor.moveToNext());


        }
        cursor.close();


        return levelArrayList;
    }

    public MSubLevel getSubOrParentName(int id) {
        MSubLevel mSubLevel = new MSubLevel();

        return mSubLevel;
    }


    public MLock getLocalData(int levelId, int subLevelId) {
        ArrayList<MLock> unlocks = new ArrayList<>();
        MLock mLock = new MLock();
        String sql = "select * from " + DATABASE_LOCK_TABLE + " where " + KEY_LEVEL_ID + "='" + levelId + "' "
                + " AND " + KEY_SUB_LEVEL_ID + "='" + subLevelId + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mLock = new MLock();
                mLock.setId(cursor.getInt(cursor.getColumnIndex(KEY_LOCK_ID)));
                mLock.setSub_level_id(cursor.getInt(cursor.getColumnIndex(KEY_SUB_LEVEL_ID)));
                mLock.setUnlockNextLevel(cursor.getInt(cursor.getColumnIndex(KEY_UNLOCK)));
                mLock.setBestPoint(cursor.getInt(cursor.getColumnIndex(KEY_POINT)));
                mLock.setTotal_pont(cursor.getInt(cursor.getColumnIndex(KEY_TOTAL_POINT)));
                Log.e("totalPoint", "is :" + mLock.getTotal_pont());
                Log.e("unlock", "lock size" + unlocks.size());
                unlocks.add(mLock);

            } while (cursor.moveToNext());

        }
        cursor.close();

        return mLock;
    }

    public int getPopUp() {
        String sql = "select * from " + DATABASE_POPUP_TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            Log.e("popUp", "data have");
            return cursor.getInt(cursor.getColumnIndex("pop_up"));

        } else {
            Log.e("popUp", "data null");
        }
        cursor.close();


        return 0;
    }

    public int getLockTotalPointData(int id) {
        String sql = "select sum(" + KEY_TOTAL_POINT + ") as q  from " + DATABASE_LOCK_TABLE + " where " + KEY_LEVEL_ID + "='" + id + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            Log.e("DB", "data have");

//                mLock.setUnlockNextLevel(cursor.getInt(cursor.getColumnIndex(KEY_UNLOCK)));
//                mLock.setBestPoint(cursor.getInt(cursor.getColumnIndex(KEY_POINT)));
            return cursor.getInt(cursor.getColumnIndex("q"));

        } else {
            Log.e("DB", "data null");
        }
        cursor.close();


        return 0;
    }

    public MPoint getPointData(int id) {
        ArrayList<MPoint> mPoints = new ArrayList<>();
        MPoint mPoint = new MPoint();
        String sql = "select * from " + DATABASE_POINT_TABLE + " where " + KEY_POINT_ID + "='" + id + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {

                mPoint.setBestPoint(cursor.getInt(cursor.getColumnIndex(KEY_POINT)));
                mPoint.setPresentPoint(cursor.getInt(cursor.getColumnIndex(KEY_PRESENT_POINT)));

                mPoints.add(mPoint);

            } while (cursor.moveToNext());

        }
        cursor.close();

        return mPoint;
    }

    public MQuestions getQuesData() {

        MQuestions mQuestions = new MQuestions();
        String sql = "select * from " + DATABASE_POPUP_TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {

                mQuestions.setId(cursor.getInt(cursor.getColumnIndex(KEY_Q_ID)));
                mQuestions.setPopUp(cursor.getInt(cursor.getColumnIndex(KEY_POPUP)));
                mQuestions.setPopUp2(cursor.getInt(cursor.getColumnIndex(KEY_POPUP2)));
                mQuestions.setPopUp3(cursor.getInt(cursor.getColumnIndex(KEY_POPUP3)));
                mQuestions.setPopUp4(cursor.getInt(cursor.getColumnIndex(KEY_POPUP4)));
                mQuestions.setPopUp5(cursor.getInt(cursor.getColumnIndex(KEY_POPUP5)));
                mQuestions.setPopUp6(cursor.getInt(cursor.getColumnIndex(KEY_POPUP6)));
                mQuestions.setPopUp7(cursor.getInt(cursor.getColumnIndex(KEY_POPUP7)));
                mQuestions.setPopUp8(cursor.getInt(cursor.getColumnIndex(KEY_POPUP8)));
                mQuestions.setPopUp9(cursor.getInt(cursor.getColumnIndex(KEY_POPUP9)));
                mQuestions.setPopUp10(cursor.getInt(cursor.getColumnIndex(KEY_POPUP10)));
                mQuestions.setPopUp11(cursor.getInt(cursor.getColumnIndex(KEY_POPUP11)));


            } while (cursor.moveToNext());

        }
        cursor.close();
        return mQuestions;
    }

    public ArrayList<MItem> getOptionData() {
        ArrayList<MItem> mItems = new ArrayList<>();
        MItem mItem = new MItem();
        String sql = "select * from " + DATABASE_POPUP_TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {

                mItem.setItem(cursor.getInt(cursor.getColumnIndex(KEY_ITEM)));
                mItem.setId(cursor.getInt(cursor.getColumnIndex(KEY_OP_ID)));
                mItem.setTag(cursor.getInt(cursor.getColumnIndex(KEY_TAG)));

                mItems.add(mItem);

            } while (cursor.moveToNext());

        }
        cursor.close();


        return mItems;
    }

    public ArrayList<MSubLevel> getSubLevelData(int id) {
        ArrayList<MSubLevel> assetArrayList = new ArrayList<>();
        Log.e("DB", "S1");
        MSubLevel mSubLevel;
        String sql = "select a.s_lid,a.pNm,a.how_to,a.k_Logic,a.pid,a.name,a.coins_price,a.no_of_coins,b.un_lock,b.best_point from sub a left join lock_tb b on a.pid=b.lid AND a.s_lid=b.s_lid where a." + KEY_PARENT_ID + "='" + id + "'";
//                " from " + DATABASE_SUB_LEVEL_TABLE + " a where " + KEY_PARENT_ID + "='" + id + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            Log.e("DB", "S2 :" + cursor.getCount());
            do {
                mSubLevel = new MSubLevel();
                mSubLevel.setLid(cursor.getInt(cursor.getColumnIndex(KEY_SUB_LEVEL_ID)));
                mSubLevel.setLogic(cursor.getInt(cursor.getColumnIndex(KEY_LOGIC)));
                mSubLevel.setUnlockNextLevel(cursor.getInt(cursor.getColumnIndex(KEY_UNLOCK)));
                mSubLevel.setHowto(cursor.getString(cursor.getColumnIndex(KEY_HOW_TO)));

                try {
                    mSubLevel.setBestPoint(cursor.getInt(cursor.getColumnIndex(KEY_POINT)));
                } catch (Exception e) {
                    mSubLevel.setBestPoint(0);
                }

                mSubLevel.setParentId(cursor.getInt(cursor.getColumnIndex(KEY_PARENT_ID)));
                mSubLevel.setParentName(cursor.getString(cursor.getColumnIndex(KEY_PARENT_NAME)));
                mSubLevel.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                mSubLevel.setCoins_price(cursor.getString(cursor.getColumnIndex(KEY_COINS_PRICE)));
                mSubLevel.setNo_of_coins(cursor.getString(cursor.getColumnIndex(KEY_NO_OF_COINS)));
                assetArrayList.add(mSubLevel);
                Log.e("DB", "S3 :" + mSubLevel.getUnlockNextLevel());

            } while (cursor.moveToNext());

        }
        cursor.close();


        return assetArrayList;
    }

    public ArrayList<MWords> getEnglishWordsData(int id) {
        ArrayList<MWords> assetArrayList = new ArrayList<>();

        MWords mWords;
        String sql = "select * from " + DATABASE_WORDS_TABLE + " a where " + KEY_WORDS_CONTENTS_ID + "='" + id + "'";

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mWords = new MWords();
                mWords.setWid(cursor.getInt(cursor.getColumnIndex(KEY_WORDS_ID)));
                mWords.setWsound(cursor.getString(cursor.getColumnIndex(KEY_WORDS_SOUND)));
                mWords.setWword(cursor.getString(cursor.getColumnIndex(KEY_WORDS_Word)));
                mWords.setContentId(cursor.getInt(cursor.getColumnIndex(KEY_WORDS_CONTENTS_ID)));
                mWords.setWimg(cursor.getString(cursor.getColumnIndex(KEY_WORDS_IMG)));
                mWords.setWletter(cursor.getString(cursor.getColumnIndex(KEY_WORDS_LETTER)));
                assetArrayList.add(mWords);

            } while (cursor.moveToNext());

        }
        cursor.close();

        return assetArrayList;
    }

    public ArrayList<MWords> getMathWordsData(int id) {
        Log.e("start", "get math");
        ArrayList<MWords> assetArrayList = new ArrayList<>();

        MWords mWords;
        String sql = "select * from " + DATABASE_WORDS_TABLE_MATH + " a where " + KEY_WORDS_CONTENTS_ID + "='" + id + "'";

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mWords = new MWords();
                mWords.setWid(cursor.getInt(cursor.getColumnIndex(KEY_WORDS_ID)));
                mWords.setWsound(cursor.getString(cursor.getColumnIndex(KEY_WORDS_SOUND)));
                mWords.setWword(cursor.getString(cursor.getColumnIndex(KEY_WORDS_Word)));
                mWords.setContentId(cursor.getInt(cursor.getColumnIndex(KEY_WORDS_CONTENTS_ID)));
                mWords.setWimg(cursor.getString(cursor.getColumnIndex(KEY_WORDS_IMG)));
                mWords.setWletter(cursor.getString(cursor.getColumnIndex(KEY_WORDS_LETTER)));
                assetArrayList.add(mWords);

            } while (cursor.moveToNext());

        }

        cursor.close();
        Log.e("getmathlist", "math size" + assetArrayList.size());
        return assetArrayList;

    }

    public ArrayList<MWords> getBanglaMathWordsData(int id) {
        Log.e("start", "get math");
        ArrayList<MWords> assetArrayList = new ArrayList<>();

        MWords mWords;
        String sql = "select * from " + DATABASE_WORDS_TABLE_BANGLA_MATH + " a where " + KEY_WORDS_CONTENTS_ID + "='" + id + "'";

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mWords = new MWords();
                mWords.setWid(cursor.getInt(cursor.getColumnIndex(KEY_WORDS_ID)));
                mWords.setWsound(cursor.getString(cursor.getColumnIndex(KEY_WORDS_SOUND)));
                mWords.setWword(cursor.getString(cursor.getColumnIndex(KEY_WORDS_Word)));
                mWords.setContentId(cursor.getInt(cursor.getColumnIndex(KEY_WORDS_CONTENTS_ID)));
                mWords.setWimg(cursor.getString(cursor.getColumnIndex(KEY_WORDS_IMG)));
                mWords.setWletter(cursor.getString(cursor.getColumnIndex(KEY_WORDS_LETTER)));
                assetArrayList.add(mWords);

            } while (cursor.moveToNext());

        }

        cursor.close();
        Log.e("getmathlist", "math size" + assetArrayList.size());
        return assetArrayList;

    }

    public ArrayList<MWords> getBanglaWordsData(int id) {
        ArrayList<MWords> assetArrayList = new ArrayList<>();

        MWords mWords;
        String sql = "select * from " + DATABASE_WORDS_TABLE_BANGLA + " a where " + KEY_WORDS_CONTENTS_ID + "='" + id + "'";

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mWords = new MWords();
                mWords.setWid(cursor.getInt(cursor.getColumnIndex(KEY_WORDS_ID)));
                mWords.setWsound(cursor.getString(cursor.getColumnIndex(KEY_WORDS_SOUND)));
                mWords.setWword(cursor.getString(cursor.getColumnIndex(KEY_WORDS_Word)));
                mWords.setContentId(cursor.getInt(cursor.getColumnIndex(KEY_WORDS_CONTENTS_ID)));
                mWords.setWimg(cursor.getString(cursor.getColumnIndex(KEY_WORDS_IMG)));
                mWords.setWletter(cursor.getString(cursor.getColumnIndex(KEY_WORDS_LETTER)));

                assetArrayList.add(mWords);

            } while (cursor.moveToNext());

        }
        cursor.close();

        return assetArrayList;
    }

    public ArrayList<MContents> getBanglaContentsData() {
        ArrayList<MContents> assetArrayList = new ArrayList<>();

        MContents mContents;
        String sql = "select * from " + DATABASE_CONTENTS_TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mContents = new MContents();
                mContents.setLid(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_ID)));
                mContents.setMid(cursor.getInt(cursor.getColumnIndex(KEY_MODEL_ID)));
                mContents.setAud(cursor.getString(cursor.getColumnIndex(KEY_SOUNDS)));
                mContents.setVid(cursor.getString(cursor.getColumnIndex(KEY_VIDEO)));
                mContents.setTxt(cursor.getString(cursor.getColumnIndex(KEY_TEXT)));
                mContents.setImg(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
                mContents.setSen(cursor.getString(cursor.getColumnIndex(KEY_SEN)));
                mContents.setPresentType(cursor.getInt(cursor.getColumnIndex(KEY_PRESENT_TYPE)));
                mContents.setPresentId(cursor.getInt(cursor.getColumnIndex(KEY_PRESENT_ID)));
                assetArrayList.add(mContents);

            } while (cursor.moveToNext());

        }
        cursor.close();

        return assetArrayList;
    }

    public ArrayList<MAllContent> getEnglishContentsContentsData() {
        ArrayList<MAllContent> assetArrayList = new ArrayList<>();

        MAllContent mAllContent;
        String sql = "select * from " + DATABASE_ENGLISH_CONTENTS_TABLE;
//                + " where " + KEY_LEVEL_ID + "='" + id + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mAllContent = new MAllContent();
                mAllContent.setLid(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_ID)));
                mAllContent.setMid(cursor.getInt(cursor.getColumnIndex(KEY_MODEL_ID)));
                mAllContent.setAud(cursor.getString(cursor.getColumnIndex(KEY_SOUNDS)));
                mAllContent.setVid(cursor.getString(cursor.getColumnIndex(KEY_VIDEO)));
                mAllContent.setTxt(cursor.getString(cursor.getColumnIndex(KEY_TEXT)));
                mAllContent.setImg(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
                mAllContent.setSen(cursor.getString(cursor.getColumnIndex(KEY_SEN)));
                mAllContent.setPresentType(cursor.getInt(cursor.getColumnIndex(KEY_PRESENT_TYPE)));
                mAllContent.setPresentId(cursor.getInt(cursor.getColumnIndex(KEY_PRESENT_ID)));
                assetArrayList.add(mAllContent);

            } while (cursor.moveToNext());

        }
        cursor.close();

        return assetArrayList;
    }

    public ArrayList<MAllContent> getBanglaContentsContentsData() {
        ArrayList<MAllContent> assetArrayList = new ArrayList<>();

        MAllContent mAllContent;
        String sql = "select * from " + DATABASE_BANGLA_CONTENTS_TABLE;
//                + " where " + KEY_LEVEL_ID + "='" + id + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mAllContent = new MAllContent();
                mAllContent.setLid(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_ID)));
                mAllContent.setMid(cursor.getInt(cursor.getColumnIndex(KEY_MODEL_ID)));
                mAllContent.setAud(cursor.getString(cursor.getColumnIndex(KEY_SOUNDS)));
                mAllContent.setVid(cursor.getString(cursor.getColumnIndex(KEY_VIDEO)));
                mAllContent.setTxt(cursor.getString(cursor.getColumnIndex(KEY_TEXT)));
                mAllContent.setImg(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
                mAllContent.setSen(cursor.getString(cursor.getColumnIndex(KEY_SEN)));
                mAllContent.setPresentType(cursor.getInt(cursor.getColumnIndex(KEY_PRESENT_TYPE)));
                mAllContent.setPresentId(cursor.getInt(cursor.getColumnIndex(KEY_PRESENT_ID)));
                Log.e("image", "get image : " + mAllContent.getImg());
                assetArrayList.add(mAllContent);

            } while (cursor.moveToNext());

        }
        cursor.close();

        return assetArrayList;
    }

    public ArrayList<MAllContent> getMathContentsContentsData() {
        ArrayList<MAllContent> assetArrayList = new ArrayList<>();

        MAllContent mAllContent;
        String sql = "select * from " + DATABASE_MATH_CONTENTS_TABLE;
//                + " where " + KEY_LEVEL_ID + "='" + id + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mAllContent = new MAllContent();
                mAllContent.setLid(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_ID)));
                mAllContent.setMid(cursor.getInt(cursor.getColumnIndex(KEY_MODEL_ID)));
                mAllContent.setAud(cursor.getString(cursor.getColumnIndex(KEY_SOUNDS)));
                mAllContent.setVid(cursor.getString(cursor.getColumnIndex(KEY_VIDEO)));
                mAllContent.setTxt(cursor.getString(cursor.getColumnIndex(KEY_TEXT)));
                mAllContent.setImg(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
                mAllContent.setSen(cursor.getString(cursor.getColumnIndex(KEY_SEN)));
                mAllContent.setPresentType(cursor.getInt(cursor.getColumnIndex(KEY_PRESENT_TYPE)));
                mAllContent.setPresentId(cursor.getInt(cursor.getColumnIndex(KEY_PRESENT_ID)));
                assetArrayList.add(mAllContent);

            } while (cursor.moveToNext());

        }
        cursor.close();

        return assetArrayList;
    }

    public ArrayList<MAllContent> getBanglaMathContentsContentsData() {
        ArrayList<MAllContent> assetArrayList = new ArrayList<>();

        MAllContent mAllContent;
        String sql = "select * from " + DATABASE_BANGLA_MATH_CONTENTS_TABLE;
//                + " where " + KEY_LEVEL_ID + "='" + id + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mAllContent = new MAllContent();
                mAllContent.setLid(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_ID)));
                mAllContent.setMid(cursor.getInt(cursor.getColumnIndex(KEY_MODEL_ID)));
                mAllContent.setAud(cursor.getString(cursor.getColumnIndex(KEY_SOUNDS)));
                mAllContent.setVid(cursor.getString(cursor.getColumnIndex(KEY_VIDEO)));
                mAllContent.setTxt(cursor.getString(cursor.getColumnIndex(KEY_TEXT)));
                mAllContent.setImg(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
                mAllContent.setSen(cursor.getString(cursor.getColumnIndex(KEY_SEN)));
                mAllContent.setPresentType(cursor.getInt(cursor.getColumnIndex(KEY_PRESENT_TYPE)));
                mAllContent.setPresentId(cursor.getInt(cursor.getColumnIndex(KEY_PRESENT_ID)));
                assetArrayList.add(mAllContent);

            } while (cursor.moveToNext());

        }
        cursor.close();

        return assetArrayList;
    }

    private void close() {
        if (db != null && db.isOpen()) {
            db.close();
            db = null;
        }
    }
}
