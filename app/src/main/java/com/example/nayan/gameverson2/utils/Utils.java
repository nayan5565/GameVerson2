package com.example.nayan.gameverson2.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import com.example.nayan.gameverson2.model.MAllContent;
import com.example.nayan.gameverson2.model.MContents;
import com.example.nayan.gameverson2.model.MLevel;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.model.MWords;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import static android.R.attr.typeface;

/**
 * Created by NAYAN on 8/25/2016.
 */
public class Utils {
    public static String IMAGE_OPEN = "one", IMAGE_OFF = "two";
    public static String ASSETS_DOWNLOAD_MASSAGE = "downloaded";
    public static String CONVERT_NUM = "downloaded";
    public static ArrayList<MSubLevel> mSubLevelArrayList;
    public static ArrayList<MLevel> levels;
    public static ArrayList<MContents> contents;
    public static ArrayList<MAllContent> English;
    public static ArrayList<MWords> English_words;
    public static ArrayList<MWords> BANGLA_words;
    public static ArrayList<MWords> MATH_words;
    public static ArrayList<MAllContent> Maths;
    public static ArrayList<MAllContent> BANGLA;
    public static boolean isSoundPlay = true;
    public static int bestPoint, presentPoint;
    static MediaPlayer mediaPlayer;
    public static int widthSize, heightSize;

    public static void getSound(Context context, int path) {
        if (isSoundPlay) {

            if (mediaPlayer != null) {
                mediaPlayer.stop();
                //mediaPlayer.reset();
                mediaPlayer.release();
            }
            Log.e("CONTEXT", "value :" + context + ":" + path);
            mediaPlayer = MediaPlayer.create(context, path);
            mediaPlayer.start();
            Log.e("log", "playing");
        }
    }

    public static String convertNum(String num) {
        if (num.length() > 0) {
            num = num.replace("0", "০").replace("1", "১").replace("2", "২")
                    .replace("3", "৩").replace("4", "৪").replace("5", "৫")
                    .replace("6", "৬").replace("7", "৭").replace("8", "৮")
                    .replace("9", "৯");
        }


        return num;
    }

    public static String databasePassKey(String emailName, String deviceId) {
        String firstChOfEmail = emailName.substring(0, 1);
        String lastChOfEmail = emailName.substring(emailName.indexOf("@") - 1, emailName.indexOf("@"));
        String firstNumOfDeviceId = deviceId.substring(0, 1);
        String secondNumOfDeviceId = deviceId.substring(deviceId.length() - 1);
        return firstChOfEmail + lastChOfEmail + firstNumOfDeviceId + secondNumOfDeviceId;
    }

    public static void zoom(View view, boolean isLeft) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 1.2f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 1.2f);
        animator2.setDuration(2000);
        animator2.setRepeatCount(ValueAnimator.INFINITE);
        animator2.setRepeatMode(ValueAnimator.REVERSE);
        animator2.start();
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(2000);
        animator.start();
    }

    public static void rotation(View view, boolean isLeft) {
        view.setPivotX(view.getX() + view.getWidth() / 2);
        view.setPivotY(view.getY() + view.getHeight() / 2);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationY", 0, 180);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(1);
        animator.setDuration(2000);
        animator.start();
    }

    public static int getScreenSize(Activity activity, int itemSize) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        itemSize = getPixel(activity, itemSize);
        int width = size.x;
        int height = size.y;
        int item = (width - 30) / (itemSize);
        return item;

    }

    public static int getPixel(Context context, int pixel) {
        float density = context.getResources().getDisplayMetrics().density;
        int paddingDp = (int) (pixel * density);
        return paddingDp;
    }

    public static void moveAnimation(Object target, Object target2) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "translationX", -230, widthSize + 10);
        animator.setDuration(9000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(target2, "translationX", widthSize, -200);
        animator1.setDuration(9000);
        animator1.setRepeatCount(ValueAnimator.INFINITE);
        animator1.start();

    }

    public static int randInt(int min, int max) {


        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static void move(final View view, final View view2) {

//        count=count+10;
//        Log.e("count", "is" + count);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
//                getScreenSize(MainActivity.this);

                view2.setY(randInt(50, heightSize - 50));
                view2.setX(280);
                view.setY(randInt(50, heightSize - 50));
                view.setX(-200);
                move(view, view2);
            }
        }, 9000);

    }

    public static void getScreenSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        widthSize = (width - 5);
        heightSize = (height - 50);
        Log.e("width", "size" + width);
        Log.e("height", "size" + height);

    }

    public static boolean isInternetOn(Context context) {

        try {
            ConnectivityManager con = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifi, mobile;
            wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            mobile = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (wifi.isConnectedOrConnecting() || mobile.isConnectedOrConnecting()) {
                return true;
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

//    public static void moveAnimation(Object target, Object target2) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "translationX", -280, 280);
//        animator.setDuration(9000);
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator.start();
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(target2, "translationX", 280, -280);
//        animator1.setDuration(9000);
//        animator1.setRepeatCount(ValueAnimator.INFINITE);
//        animator1.start();
//
//    }

    public static String getIntToStar(int starCount) {
        String fillStar = "\u2605";
        String blankStar = "\u2606";
        String star = "";

        for (int i = 0; i < starCount; i++) {
            star = star.concat(" " + fillStar);
        }
        for (int j = (3 - starCount); j > 0; j--) {
            star = star.concat(" " + blankStar);
        }

        return star;
    }

    public static void changeFont(TextView tx, Context context) {
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/carterone.ttf");

        tx.setTypeface(custom_font);
    }

    public static void changeFontAnotherWay(TextView tx, Context context) {

        AssetManager am = context.getApplicationContext().getAssets();

        Typeface typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "carterone.ttf"));

        tx.setTypeface(typeface);
    }

    public static void setFont(Context context, String font, TextView... textViews) {

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + font + ".ttf");
        for (TextView textView : textViews) {
            textView.setTypeface(typeface);
        }
    }


}
