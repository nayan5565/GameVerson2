package com.example.nayan.gameverson2.tools;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.nayan.gameverson2.R;

/**
 * Created by NAYAN on 9/8/2016.
 */
public class DialogSoundOnOff {
    public static final String MYPREF = "mpref";
    public static final String KEY_IMAGE = "image";
    public static SharedPreferences preferences;

    public static String getPREF(Context context, String key) {
        preferences = context.getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        return preferences.getString(key, "null");
    }

    public static void savePref(Context context, String key, String value) {
        preferences = context.getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void dialogShow(final Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setTitle("Setting");
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_setting);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final ImageView imgSound = (ImageView) dialog.findViewById(R.id.imgSoundOnOf);
        ImageView btnOK = (ImageView) dialog.findViewById(R.id.btnOk);
        String image;
        image = getPREF(context, KEY_IMAGE);
        if (image.equals(1 + "")) {
            Utils.isSoundPlay = true;
            imgSound.setImageResource(R.drawable.on);
        } else if (image.equals(0 + "")) {
            Utils.isSoundPlay = false;
            imgSound.setImageResource(R.drawable.off);
        }
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        imgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isSoundPlay == false) {
                    Utils.isSoundPlay = true;
                    imgSound.setImageResource(R.drawable.on);
                    savePref(context, KEY_IMAGE, 1 + "");
                } else {
                    Utils.isSoundPlay = false;
                    imgSound.setImageResource(R.drawable.off);
                    savePref(context, KEY_IMAGE, 0 + "");
                }
            }
        });
        dialog.show();
    }

}
