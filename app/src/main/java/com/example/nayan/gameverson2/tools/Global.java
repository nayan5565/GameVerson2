package com.example.nayan.gameverson2.tools;

import android.content.Context;
import android.widget.Toast;

import com.example.nayan.gameverson2.model.MSubLevel;

import java.util.ArrayList;

/**
 * Created by NAYAN on 11/14/2016.
 */
public class Global {
    public static final String IMAGE_URL = "http://www.radhooni.com/content/match_game/v1/images/";
    public static int levelId;
    public static String levelName;
    public static String subLevelName;
    public static String parentLevelName;
    public static int SUB_INDEX_POSITION;
    public static String ALTER_URL = "";
    public static int subLevelId;
    public static int totalPoint;
    public static int ALL_TOTAL_POINT;
    public static int GAME_INDEX_POSITION;
    public static final String API_LEVELS = "levels.php";
    public static final String API_CONTENTS = "contents.php";
    public static final String API_MATH = "contents_math.php";
    public static final String API_ENGLISH = "contents_english.php";
    public static final String API_BANGLA = "contents_bangla.php";
    public static final String BASE_URL = "http://www.radhooni.com/content/match_game/v1/";
    public static ArrayList<MSubLevel> parentName;
    public static String uriGreen = "@drawable/green_panel";
    public static String uriYellow = "@drawable/yellow_panel";
    public static String uriRed = "@drawable/red_panel";


}