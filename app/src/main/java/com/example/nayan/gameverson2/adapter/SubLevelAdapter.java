package com.example.nayan.gameverson2.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.activity.GameActivity;
import com.example.nayan.gameverson2.model.MLock;
import com.example.nayan.gameverson2.model.MQuestions;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.tools.DatabaseHelper;
import com.example.nayan.gameverson2.tools.DialogSoundOnOff;
import com.example.nayan.gameverson2.tools.Global;
import com.example.nayan.gameverson2.tools.Utils;

import java.util.ArrayList;

/**
 * Created by NAYAN on 11/24/2016.
 */
public class SubLevelAdapter extends RecyclerView.Adapter<SubLevelAdapter.MyViewHolder> {
    private ArrayList<MSubLevel> mSubLevels;
    private MSubLevel mSubLevel = new MSubLevel();
    private MLock mLock = new MLock();
    private Context context;
    MQuestions mQuestions;
    private LayoutInflater inflater;
    private DatabaseHelper db;
    private int count;
    int one;
    private int subLevel;

    public SubLevelAdapter(Context context) {
        this.context = context;
        mSubLevels = new ArrayList<>();
        db = new DatabaseHelper(context);
        inflater = LayoutInflater.from(context);
        mQuestions = new MQuestions();
    }

    public void setData(ArrayList<MSubLevel> mSubLevels) {
        this.mSubLevels = mSubLevels;

        Log.e("log", "setdata:" + mSubLevels.size());
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_image, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mSubLevel = mSubLevels.get(position);
        holder.txtSubLevel.setText(mSubLevel.getName());

        if (mSubLevel.getBestPoint() == 100) {
            holder.txtPoint.setImageResource(R.drawable.s_star_3);
        } else if (mSubLevel.getBestPoint() == 75) {
            holder.txtPoint.setImageResource(R.drawable.s_star_2);
        } else if (mSubLevel.getBestPoint() == 50) {
            holder.txtPoint.setImageResource(R.drawable.s_star_1);
        }

        if (mSubLevel.getUnlockNextLevel() == 1) {
            holder.imgLock.setVisibility(View.GONE);
        } else {
            holder.imgLock.setVisibility(View.VISIBLE);
            holder.imgSub.setImageResource(R.drawable.inactive_bg);
        }
    }

    @Override
    public int getItemCount() {
        return mSubLevels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtSubLevel;
        ImageView imgLock, txtPoint, imgSub;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtSubLevel = (TextView) itemView.findViewById(R.id.txtLevel);
            txtPoint = (ImageView) itemView.findViewById(R.id.txtPoint);
            imgLock = (ImageView) itemView.findViewById(R.id.imgLock);
            imgSub = (ImageView) itemView.findViewById(R.id.imgSub);
            Utils.setFont(context, "carterone", txtSubLevel);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//                    int one1 = Global.popUp;
//                    Log.e("popUp", "is " + Global.popUp);
//                    one1++;
//                    mQuestions.setPopUp(one1);
//                    db.addQuesData(mQuestions);
                    if (Global.subLevelId == 1) {
                        int popUp = Global.popUp;
                        popUp++;
                        mQuestions.setPopUp(popUp);
                        db.addQuesData(mQuestions);
                        Log.e("popUp1", "one " + popUp);
                    } else if (Global.subLevelId == 2) {
                        int popUp2 = Global.popUp2;
                        popUp2++;
                        mQuestions.setPopUp2(popUp2);
                        db.addQuesData(mQuestions);
                        Log.e("popUp1", "two " + popUp2);
                    } else if (Global.subLevelId == 3) {
                        int popUp3 = Global.popUp3;
                        popUp3++;
                        mQuestions.setPopUp3(popUp3);
                        db.addQuesData(mQuestions);
                        Log.e("popUp1", "three " + popUp3);
                    } else if (Global.subLevelId == 4) {
                        int popUp4 = Global.popUp4;
                        popUp4++;
                        mQuestions.setPopUp4(popUp4);
                        db.addQuesData(mQuestions);
                        Log.e("popUp1", "four " + popUp4);
                    }


//                    Global.popUp = db.getPopUp();
//                    Log.e("popUp", "opp  " + Global.popUp);

                    mSubLevel = mSubLevels.get(getAdapterPosition());
                    Utils.bestPoint = mSubLevel.getBestPoint();
                    if (mSubLevel.getUnlockNextLevel() == 1) {
                        Intent intent = new Intent(context, GameActivity.class);
                        intent.putExtra("subLevelName", mSubLevel.getName());
                        intent.putExtra("index", getAdapterPosition());
                        intent.putExtra("Sid", mSubLevel.getLid());
                        intent.putExtra("parentLevelName", mSubLevel.getParentName());
                        context.startActivity(intent);
                    }

                }
            });
        }
    }


}
