package com.example.nayan.gameverson2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.activity.GameActivity;
import com.example.nayan.gameverson2.model.MLock;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.tools.DatabaseHelper;
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

    private LayoutInflater inflater;
    private DatabaseHelper db;
    private int count;
    private int subLevel;

    public SubLevelAdapter(Context context) {
        this.context = context;
        mSubLevels = new ArrayList<>();
        db = new DatabaseHelper(context);
        inflater = LayoutInflater.from(context);
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
            holder.txtPoint.setTextColor(0xffffff00);
            holder.txtPoint.setText(Utils.getIntToStar(3));
        } else if (mSubLevel.getBestPoint() == 75) {
            holder.txtPoint.setTextColor(0xffffff00);
            holder.txtPoint.setText(Utils.getIntToStar(2));
        } else if (mSubLevel.getBestPoint() == 50) {
            holder.txtPoint.setTextColor(0xffffff00);
            holder.txtPoint.setText(Utils.getIntToStar(1));
        } else {
            holder.txtPoint.setText(Utils.getIntToStar(0));
        }

        if (mSubLevel.getUnlockNextLevel() == 1) {
            holder.imgLock.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mSubLevels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtSubLevel, txtPoint;
        ImageView imgLock;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtSubLevel = (TextView) itemView.findViewById(R.id.txtLevel);
            txtPoint = (TextView) itemView.findViewById(R.id.txtPoint);
            imgLock = (ImageView) itemView.findViewById(R.id.imgLock);
            Utils.setFont(context, "carterone", txtSubLevel);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
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
