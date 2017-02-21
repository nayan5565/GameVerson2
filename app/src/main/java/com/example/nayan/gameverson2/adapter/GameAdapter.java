package com.example.nayan.gameverson2.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.activity.GameActivity;
import com.example.nayan.gameverson2.model.MAllContent;
import com.example.nayan.gameverson2.utils.DatabaseHelper;
import com.example.nayan.gameverson2.utils.GameLogic;
import com.example.nayan.gameverson2.utils.Global;
import com.example.nayan.gameverson2.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by NAYAN on 11/24/2016.
 */
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewholder> {

    private ArrayList<MAllContent> textArrayList;
//    private ArrayList<MWords> textArrayList2;

    private MAllContent mContents = new MAllContent();
    private Context context;
    private LayoutInflater inflater;
    private GameLogic gameLogic;
    private Animation animation;
    private int subLevelType;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;
    DatabaseHelper db;


    public GameAdapter(Context context) {
        this.context = context;

        textArrayList = new ArrayList<>();
        db = new DatabaseHelper(context);

        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<MAllContent> textArraylist) {
        this.textArrayList = textArraylist;

        Log.e("log", "setdata:" + textArraylist.size());
        gameLogic = GameLogic.getInstance(context);
        gameLogic.callData(textArraylist, this);

        notifyDataSetChanged();
    }

//    public void setDataWord(ArrayList<MWords> wordList) {
//        this.textArrayList2 = wordList;
//        notifyDataSetChanged();
//    }

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
            if (mContents.getMatch() == 1) {
//                holder.txtContents.setBackgroundColor(0xff888888);
//                flipAnimation(holder.txtContents);

            } else {
//                shakeAnimation(holder.itemView);
                holder.imgAnim2.setImageResource(R.drawable.yellow_panel);
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);

        }
//        else if (Global.SUB_LEVEL_ID == 2) {
//            holder.txtContents.setText(mContents.getTxt());
//            holder.txtContents.setTextColor(0xffff00ff);
//            if (mContents.getMatch() ==1) {
//                if (mContents.getTxt() == null || mContents.getTxt().equals("")) {
//                    Log.e("image e","img :"+Global.IMAGE_URL+mContents.getImg());
//                    Picasso.with(context)
//                            .load(Global.IMAGE_URL+mContents.getImg())
//                            .into(holder.imgAnim);
//
//                } else {
//                    holder.txtContents.setText(mContents.getTxt());
//                }
//            } else {
//                holder.txtContents.setBackgroundColor(0);
//            }
//            holder.txtContents.setTextColor(0xffff00ff);
//            holder.txtContents.setTextSize(20);
//        }

        else if (Global.SUB_LEVEL_ID == 2) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                if (mContents.getTxt() == null || mContents.getTxt().equals("")) {
                    Log.e("image e","img :"+Global.IMAGE_URL+mContents.getImg());
                    holder.txtContents.setVisibility(View.GONE);
                    Picasso.with(context)
                            .load(Global.IMAGE_URL+mContents.getImg())
                            .into(holder.imgAnim);

                } else {
                    holder.txtContents.setText(mContents.getTxt());
                }
                holder.imgAnim2.setImageResource(R.drawable.green_panel);
            } else {
                holder.imgAnim2.setImageResource(R.drawable.yellow_panel);
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        }
        else if (Global.SUB_LEVEL_ID == 3) {
            if (mContents.getMatch() == 1) {

            } else {
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
        } else if (Global.SUB_LEVEL_ID == 4) {
            if (mContents.getMatch() == 1) {
//                holder.txtContents.setBackgroundColor(0xff888888);
//                flipAnimation(holder.txtContents);

            } else {
//                shakeAnimation(holder.itemView);
                holder.imgAnim2.setImageResource(R.drawable.yellow_panel);
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
        } else if (Global.SUB_LEVEL_ID == 5) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
//                holder.txtContents.setBackgroundColor(Color.BLACK);
                holder.imgAnim2.setImageResource(R.drawable.green_panel);
            } else {
                holder.imgAnim2.setImageResource(R.drawable.yellow_panel);
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        } else if (Global.SUB_LEVEL_ID == 6) {
            if (mContents.getMatch() == 1) {

            } else {
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
        } else if (Global.SUB_LEVEL_ID == 8) {
            if (mContents.getMatch() == 1) {
//                holder.txtContents.setBackgroundColor(0xff888888);
//                flipAnimation(holder.txtContents);

            } else {
//                shakeAnimation(holder.itemView);
                holder.imgAnim2.setImageResource(R.drawable.yellow_panel);
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
        } else if (Global.SUB_LEVEL_ID == 9) {
            if (mContents.getMatch() == 1) {

            } else {
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
        } else if (Global.SUB_LEVEL_ID == 13) {
            if (mContents.getMatch() == 1) {

            } else {
                holder.imgAnim2.setImageResource(R.drawable.yellow_panel);
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
        } else if (Global.SUB_LEVEL_ID == 14) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
//                holder.txtContents.setBackgroundColor(Color.BLACK);
                holder.imgAnim2.setImageResource(R.drawable.green_panel);
            } else {
                holder.imgAnim2.setImageResource(R.drawable.yellow_panel);
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        } else if (Global.SUB_LEVEL_ID == 15) {
            if (mContents.getClick() == Utils.IMAGE_OPEN) {

            } else {
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
        } else if (Global.SUB_LEVEL_ID == 19) {
            if (mContents.getMatch() == 1) {

            } else {
                holder.imgAnim2.setImageResource(R.drawable.yellow_panel);
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
        }

    }

    @Override
    public int getItemCount() {
        return textArrayList.size();
    }


    class MyViewholder extends RecyclerView.ViewHolder {
        TextView txtContents;
        private ImageView imgAnim;
        private ImageView imgAnim2;

        public MyViewholder(final View itemView) {
            super(itemView);
            imgAnim = (ImageView) itemView.findViewById(R.id.imgImage);
            imgAnim2 = (ImageView) itemView.findViewById(R.id.imganim2);
            txtContents = (TextView) itemView.findViewById(R.id.textContents);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mContents = textArrayList.get(getAdapterPosition());
                    Global.GAME_INDEX_POSITION = getAdapterPosition();

                    if (Global.SUB_LEVEL_ID == 1) {

                        gameLogic.textClick(mContents, getAdapterPosition(), textArrayList.size(), itemView, txtContents, imgAnim2);

                    } else if (Global.SUB_LEVEL_ID == 2) {
                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition(), imgAnim2);
                    } else if (Global.SUB_LEVEL_ID == 3) {
                        mContents.setWords(db.getBanglaWordsData(mContents.getMid()));
                        dialogShohWithWordsList();
                    } else if (Global.SUB_LEVEL_ID == 4) {
                        gameLogic.textClick(mContents, getAdapterPosition(), textArrayList.size(), itemView, txtContents, imgAnim2);
                    } else if (Global.SUB_LEVEL_ID == 5) {
                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition(), imgAnim2);
                    } else if (Global.SUB_LEVEL_ID == 6) {
                        mContents.setWords(db.getMathWordsData(mContents.getMid()));
                        dialogShohWithWordsList();
                    } else if (Global.SUB_LEVEL_ID == 8) {
                        gameLogic.textClick(mContents, getAdapterPosition(), textArrayList.size(), itemView, txtContents, imgAnim2);
                    } else if (Global.SUB_LEVEL_ID == 9) {
                        mContents.setWords(db.getWordsData(mContents.getMid()));
                        dialogShohWithWordsList();
                    } else if (Global.SUB_LEVEL_ID == 13) {
                        gameLogic.textClick(mContents, getAdapterPosition(), textArrayList.size(), itemView, txtContents, imgAnim2);
                    } else if (Global.SUB_LEVEL_ID == 14) {
                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition(), imgAnim2);
                    } else if (Global.SUB_LEVEL_ID == 15) {
                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition(), imgAnim2);
//                        gameLogic.imageClick(mContents, getAdapterPosition(), textArrayList.size(), itemView);
                    } else if (Global.SUB_LEVEL_ID == 19) {
                        dialogShohWithWordsList();
                    }


                }
            });
        }
    }

    public void getAnimation(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationY", -180, 0);
        animator.setDuration(500);
        animator.start();
    }

    public void flipAnimation2(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationY", 0, -180);
        animator.setDuration(500);
        animator.start();
    }


    public void getShake(View v) {
        // Create shake effect from xml resource
        Animation shake = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.shaking);
        // View element to be shaken

        // Perform animation
        v.startAnimation(shake);
    }

    private void dialogShohWithWordsList() {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_show_text);
        TextView txt1 = (TextView) dialog.findViewById(R.id.txt1);
        TextView txt2 = (TextView) dialog.findViewById(R.id.txt2);
        TextView txt3 = (TextView) dialog.findViewById(R.id.txt3);
        TextView txt4 = (TextView) dialog.findViewById(R.id.txt4);
        ImageView img1 = (ImageView) dialog.findViewById(R.id.img1);
        ImageView img2 = (ImageView) dialog.findViewById(R.id.img2);
        ImageView img3 = (ImageView) dialog.findViewById(R.id.img3);
        ImageView img4 = (ImageView) dialog.findViewById(R.id.img4);
        ImageView imgBack = (ImageView) dialog.findViewById(R.id.imgBack);
        ImageView imgClose = (ImageView) dialog.findViewById(R.id.imgClose);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Global.GAME_INDEX_POSITION <= 0) {
                    Toast.makeText(context, "level finish", Toast.LENGTH_SHORT).show();

                    dialog.dismiss();

                } else {
                    Global.GAME_INDEX_POSITION = Global.GAME_INDEX_POSITION - 1;

                    mContents = textArrayList.get(Global.GAME_INDEX_POSITION);

                    if (Global.SUB_LEVEL_ID == 3) {

                        mContents.setWords(db.getBanglaWordsData(mContents.getMid()));
                    }
                    if (Global.SUB_LEVEL_ID == 6) {

                        mContents.setWords(db.getMathWordsData(mContents.getMid()));
                    }
                    if (Global.SUB_LEVEL_ID == 9) {

                        mContents.setWords(db.getWordsData(mContents.getMid()));
                    }

                    dialogShohWithWordsList();
                    Toast.makeText(context, "position" + Global.GAME_INDEX_POSITION, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
//
            }
        });
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Log.e("TEST", "s:" + mContents.getWords().size());
        if (mContents.getWords().size() == 4) {
            txt1.setText(mContents.getWords().get(0).getWword());
            txt2.setText(mContents.getWords().get(1).getWword());
            txt3.setText(mContents.getWords().get(2).getWword());
            txt4.setText(mContents.getWords().get(3).getWword());

            String url = Global.IMAGE_URL + mContents.getWords().get(0).getWimg();
            String url2 = Global.IMAGE_URL + mContents.getWords().get(1).getWimg();
            String url3 = Global.IMAGE_URL + mContents.getWords().get(2).getWimg();
            String url4 = Global.IMAGE_URL + mContents.getWords().get(3).getWimg();
            Picasso.with(context)
                    .load(url)
                    .into(img1);
            Picasso.with(context)
                    .load(url2)
                    .into(img2);
            Picasso.with(context)
                    .load(url3)
                    .into(img3);
            Picasso.with(context)
                    .load(url4)
                    .into(img4);

        } else if (mContents.getWords().size() == 3) {
            txt1.setText(mContents.getWords().get(0).getWword());
            txt2.setText(mContents.getWords().get(1).getWword());
            txt3.setText(mContents.getWords().get(2).getWword());
            txt4.setText("null");
            String url = Global.IMAGE_URL + mContents.getWords().get(0).getWimg();
            String url2 = Global.IMAGE_URL + mContents.getWords().get(1).getWimg();
            String url3 = Global.IMAGE_URL + mContents.getWords().get(2).getWimg();
            Picasso.with(context)
                    .load(url)
                    .into(img1);
            Picasso.with(context)
                    .load(url2)
                    .into(img2);
            Picasso.with(context)
                    .load(url3)
                    .into(img3);
        } else if (mContents.getWords().size() == 2) {
            txt1.setText(mContents.getWords().get(0).getWword());
            txt2.setText(mContents.getWords().get(1).getWword());
            txt3.setText("null");
            txt4.setText("null");
            String url = Global.IMAGE_URL + mContents.getWords().get(0).getWimg();
            String url2 = Global.IMAGE_URL + mContents.getWords().get(1).getWimg();
            Picasso.with(context)
                    .load(url)
                    .into(img1);
            Picasso.with(context)
                    .load(url2)
                    .into(img2);
        } else if (mContents.getWords().size() == 1) {
            txt1.setText(mContents.getWords().get(0).getWword());
            txt2.setText("null");
            txt3.setText("null");
            txt4.setText("null");
            String url = Global.IMAGE_URL + mContents.getWords().get(0).getWimg();
            Log.e("imgae", "url is" + url);
            Picasso.with(context)
                    .load(url)
                    .into(img1);
//            img1.setImageResource(mContents.getWords().get(0).getWimg());


        }
        dialog.show();
    }
}
