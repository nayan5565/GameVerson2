package com.example.nayan.gameverson2.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import com.example.nayan.gameverson2.model.MContents;
import com.example.nayan.gameverson2.utils.Global;
import com.example.nayan.gameverson2.utils.GameLogic;
import com.example.nayan.gameverson2.utils.Utils;

import java.util.ArrayList;

/**
 * Created by NAYAN on 11/24/2016.
 */
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewholder> {

    private ArrayList<MContents> textArrayList;

    private MContents mContents = new MContents();
    private Context context;
    private LayoutInflater inflater;
    private GameLogic gameLogic;
    private Animation animation;
    private int subLevelType;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;


    public GameAdapter(Context context) {
        this.context = context;

        textArrayList = new ArrayList<>();


        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<MContents> textArraylist) {
        this.textArrayList = textArraylist;

        Log.e("log", "setdata:" + textArraylist.size());
        gameLogic = GameLogic.getInstance(context);
        gameLogic.callData(textArraylist, this);

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
        }else if (Global.SUB_LEVEL_ID == 3) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        }else if (Global.SUB_LEVEL_ID == 4) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        }else if (Global.SUB_LEVEL_ID == 5) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        }else if (Global.SUB_LEVEL_ID == 6) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        }else if (Global.SUB_LEVEL_ID == 8) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        }else if (Global.SUB_LEVEL_ID == 9) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        }else if (Global.SUB_LEVEL_ID == 13) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        }else if (Global.SUB_LEVEL_ID == 14) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        }else if (Global.SUB_LEVEL_ID == 15) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        }else if (Global.SUB_LEVEL_ID == 19) {
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContents.getMatch() == 1) {
                holder.txtContents.setBackgroundColor(Color.BLACK);
            } else {
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        }
//        else if (Global.SUB_LEVEL_ID == 8) {
//            if (mContents.getClick() == Utils.IMAGE_OPEN) {
//                if (mContents.getTxt() == null || mContents.getTxt().equals("")) {
//
//                    holder.txtContents.setText(mContents.getSen());
//
//                } else {
//                    holder.txtContents.setText(mContents.getTxt());
//                }
//            } else {
//                holder.txtContents.setText(" ");
//            }
//        }

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
                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition());
//                        gameLogic.imageClick(mContents, getAdapterPosition(), textArrayList.size(), itemView);
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
}
