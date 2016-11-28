package com.example.nayan.gameverson2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.model.MContents;
import com.example.nayan.gameverson2.utils.Global;
import com.example.nayan.gameverson2.utils.NLogic;
import com.example.nayan.gameverson2.utils.Utils;

import java.util.ArrayList;

/**
 * Created by NAYAN on 11/24/2016.
 */
public class Class1AdapterOfBangla extends RecyclerView.Adapter<Class1AdapterOfBangla.MyViewholder> {

    private ArrayList<MContents> textArrayList;

    private MContents mContents = new MContents();
    private Context context;
    private LayoutInflater inflater;
    private NLogic nLogic;
    private Animation animation;
    private int subLevelType;


    public Class1AdapterOfBangla(Context context) {
        this.context = context;

        textArrayList = new ArrayList<>();


        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<MContents> textArraylist) {
        this.textArrayList = textArraylist;

        Log.e("log", "setdata:" + textArraylist.size());
        nLogic = NLogic.getInstance(context);
        nLogic.callData(textArraylist, this);

        notifyDataSetChanged();
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.image_row, parent, false);
        MyViewholder viewholder = new MyViewholder(view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        mContents = textArrayList.get(position);
        if (Global.SUB_LEVEL_ID == 1) {
            if (mContents.getClick()==Utils.IMAGE_ON){
                holder.txtContents.setBackgroundColor(0xff888888);
            }
            else {
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);

        }
        if (Global.SUB_LEVEL_ID == 2) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);

        } else if (Global.SUB_LEVEL_ID == 3) {
            if (mContents.getClick() == Utils.IMAGE_ON) {
                if (mContents.getTxt() == null || mContents.getTxt().equals("")) {
                    holder.txtContents.setText(mContents.getSen());
                } else {
                    holder.txtContents.setText(mContents.getTxt());
                }
            } else {
                holder.txtContents.setText(" ");
            }
        }

    }

    @Override
    public int getItemCount() {
        return textArrayList.size();
    }


    class MyViewholder extends RecyclerView.ViewHolder {
        TextView txtContents;

        public MyViewholder(final View itemView) {
            super(itemView);
            txtContents = (TextView) itemView.findViewById(R.id.textContents);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContents = textArrayList.get(getAdapterPosition());
//                    nLogic.textClick(mContents);
//                    Toast.makeText(context,mContents.getTxt(), Toast.LENGTH_SHORT).show();
                    if (Global.SUB_LEVEL_ID == 1) {
                        nLogic.textClick(mContents,getAdapterPosition(), textArrayList.size());

                    } else if (Global.SUB_LEVEL_ID == 2) {
                        nLogic.imageClick(mContents, getAdapterPosition(), textArrayList.size());
                    } else if (Global.SUB_LEVEL_ID == 3) {
                        nLogic.imageClick(mContents, getAdapterPosition(), textArrayList.size());
                    }

                }
            });
        }
    }
}
