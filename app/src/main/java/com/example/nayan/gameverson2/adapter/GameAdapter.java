package com.example.nayan.gameverson2.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.model.MAllContent;
import com.example.nayan.gameverson2.model.MWords;
import com.example.nayan.gameverson2.utils.DatabaseHelper;
import com.example.nayan.gameverson2.utils.GameLogic;
import com.example.nayan.gameverson2.utils.Global;
import com.example.nayan.gameverson2.utils.Utils;

import java.util.ArrayList;

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
            if (mContents.getClick() == Utils.IMAGE_OPEN) {
//                holder.txtContents.setBackgroundColor(0xff888888);
//                flipAnimation(holder.txtContents);
            } else {
//                shakeAnimation(holder.itemView);
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);

        } else if (Global.SUB_LEVEL_ID == 2) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        } else if (Global.SUB_LEVEL_ID == 3) {
            if (mContents.getClick() == Utils.IMAGE_OPEN) {
//                holder.txtContents.setBackgroundColor(0xff888888);
//                flipAnimation(holder.txtContents);
            } else {
//                shakeAnimation(holder.itemView);
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
//        }else if (Global.SUB_LEVEL_ID == 4) {
//            holder.txtContents.setText(mContents.getTxt());
//            holder.txtContents.setTextColor(0xffff00ff);
//            if (mContents.getMatch() == 1) {
//                holder.txtContents.setBackgroundColor(Color.BLACK);
//            } else {
//                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
//            }
        } else if (Global.SUB_LEVEL_ID == 5) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        } else if (Global.SUB_LEVEL_ID == 6) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        } else if (Global.SUB_LEVEL_ID == 8) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        } else if (Global.SUB_LEVEL_ID == 9) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        } else if (Global.SUB_LEVEL_ID == 13) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        } else if (Global.SUB_LEVEL_ID == 14) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        } else if (Global.SUB_LEVEL_ID == 15) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        } else if (Global.SUB_LEVEL_ID == 19) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        } else if (Global.SUB_LEVEL_ID == 4) {
            if (mContents.getClick() == Utils.IMAGE_OPEN) {
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
        private ImageView imgAnim;
        private ImageView imgAnim2;

        public MyViewholder(final View itemView) {
            super(itemView);
            imgAnim = (ImageView) itemView.findViewById(R.id.imganim);
            imgAnim2 = (ImageView) itemView.findViewById(R.id.imganim2);
            txtContents = (TextView) itemView.findViewById(R.id.textContents);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    flipAnimation(itemView);
                    mContents = textArrayList.get(getAdapterPosition());

                    if (Global.SUB_LEVEL_ID == 1) {

                        gameLogic.textClick(mContents, getAdapterPosition(), textArrayList.size(), itemView, txtContents);

                    } else if (Global.SUB_LEVEL_ID == 2) {
                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition());
//                        gameLogic.imageClick(mContents, getAdapterPosition(), textArrayList.size(), itemView);
                    } else if (Global.SUB_LEVEL_ID == 3) {
                        dialogShohWithWordsList();
                    } else if (Global.SUB_LEVEL_ID == 4) {
                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition());
//                        gameLogic.imageClick(mContents, getAdapterPosition(), textArrayList.size(), itemView);
                    } else if (Global.SUB_LEVEL_ID == 5) {
                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition());
//                        gameLogic.imageClick(mContents, getAdapterPosition(), textArrayList.size(), itemView);
                    } else if (Global.SUB_LEVEL_ID == 6) {
                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition());
//                        gameLogic.imageClick(mContents, getAdapterPosition(), textArrayList.size(), itemView);
                    } else if (Global.SUB_LEVEL_ID == 8) {
                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition());
//                        gameLogic.imageClick(mContents, getAdapterPosition(), textArrayList.size(), itemView);
                    } else if (Global.SUB_LEVEL_ID == 9) {
                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition());
//                        gameLogic.imageClick(mContents, getAdapterPosition(), textArrayList.size(), itemView);
                    } else if (Global.SUB_LEVEL_ID == 13) {
                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition());
//                        gameLogic.imageClick(mContents, getAdapterPosition(), textArrayList.size(), itemView);
                    } else if (Global.SUB_LEVEL_ID == 14) {
                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition());
//                        gameLogic.imageClick(mContents, getAdapterPosition(), textArrayList.size(), itemView);
                    } else if (Global.SUB_LEVEL_ID == 15) {
                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition());
//                        gameLogic.imageClick(mContents, getAdapterPosition(), textArrayList.size(), itemView);
                    } else if (Global.SUB_LEVEL_ID == 19) {
                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition());
//                        gameLogic.imageClick(mContents, getAdapterPosition(), textArrayList.size(), itemView);
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
        mContents.setWords(db.getMathWordsData(mContents.getMid()));
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_show_text);
        dialog.setCancelable(true);
        TextView txt1 = (TextView) dialog.findViewById(R.id.txt1);
        TextView txt2 = (TextView) dialog.findViewById(R.id.txt2);
        TextView txt3 = (TextView) dialog.findViewById(R.id.txt3);
        TextView txt4 = (TextView) dialog.findViewById(R.id.txt4);
        if (mContents.getWords().size() == 4) {
            txt1.setText(mContents.getWords().get(0).getWword());
            txt2.setText(mContents.getWords().get(1).getWword());
            txt3.setText(mContents.getWords().get(2).getWword());
            txt4.setText(mContents.getWords().get(3).getWword());

        } else if (mContents.getWords().size() == 3) {
            txt1.setText(mContents.getWords().get(0).getWword());
            txt2.setText(mContents.getWords().get(1).getWword());
            txt3.setText(mContents.getWords().get(2).getWword());
            txt4.setText("null");
        } else if (mContents.getWords().size() == 2) {
            txt1.setText(mContents.getWords().get(0).getWword());
            txt2.setText(mContents.getWords().get(1).getWword());
            txt3.setText("null");
            txt4.setText("null");

        } else if (mContents.getWords().size() == 1) {
            txt1.setText(mContents.getWords().get(0).getWword());
            txt2.setText("null");
            txt3.setText("null");
            txt4.setText("null");

        }
        dialog.show();
    }
}
