package com.example.nayan.gameverson2.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.activity.MainActivity;
import com.example.nayan.gameverson2.model.MAllContent;
import com.example.nayan.gameverson2.tools.DatabaseHelper;
import com.example.nayan.gameverson2.tools.GameLogic;
import com.example.nayan.gameverson2.tools.Global;
import com.example.nayan.gameverson2.tools.Utils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by NAYAN on 11/24/2016.
 */
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewholder> {

    String sounds = "sounds";
    String mainSounds = "m_sounds";
    String imageWords = "WImage";
    //    private ArrayList<MWords> textArrayList2;
    DatabaseHelper db;
    private ArrayList<MAllContent> textArrayList;
    private MAllContent mContents = new MAllContent();
    private Context context;
    private LayoutInflater inflater;
    private GameLogic gameLogic;
    private Animation animation;
    private int subLevelType;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;
    private RelativeLayout changeColor;


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
        if (Global.logic == 1) {
//                || Global.subLevelId == 4 || Global.subLevelId == 8 || Global.subLevelId == 13) {
            if (mContents.getMatch() == 1) {
//                holder.txtContents.setBackgroundColor(0xff888888);
//                flipAnimation(holder.txtContents);
                holder.imgAnim2.setImageResource(R.drawable.green_panel);

            } else {
//                shakeAnimation(holder.itemView);
                holder.imgAnim2.setImageResource(R.drawable.yellow_panel);
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);

        }
//        else if (Global.subLevelId == 2) {
//            holder.txtContents.setTextColor(0xffff00ff);
//            holder.txtContents.setTextSize(20);
//            if (mContents.getTxt() == null || mContents.getTxt().equals("")) {
//                holder.txtContents.setText(mContents.getSen());
//
//
//            } else {
//                holder.txtContents.setText(mContents.getTxt());
//            }
//            if (mContents.getMatch() == 1) {
//                holder.imgAnim2.setImageResource(R.drawable.green_panel);
//            } else {
//                holder.imgAnim2.setImageResource(R.drawable.yellow_panel);
//                holder.txtContents.setBackgroundColor(0);
//            }
//
//        }
//        else if (Global.subLevelId == 2) {
//            holder.txtContents.setTextColor(0xffff00ff);
//            holder.txtContents.setTextSize(20);
//
//            if (mContents.getMatch() == 1) {
//                if (mContents.getTxt() == null || mContents.getTxt().equals("")) {
//                    holder.txtContents.setText(mContents.getSen());
//
//
//                } else {
//                    holder.txtContents.setText(mContents.getTxt());
//                }
//                holder.imgAnim2.setImageResource(R.drawable.green_panel);
//            } else {
//                holder.txtContents.setText("");
//                holder.imgAnim2.setImageResource(R.drawable.yellow_panel);
//                holder.txtContents.setBackgroundColor(0);
//            }
//
//        }

        if (Global.logic == 2) {
//                || Global.subLevelId == 5 || Global.subLevelId == 9 || Global.subLevelId == 14) {
            holder.txtContents.setTextColor(0xffff00ff);
//            holder.txtContents.setText(mContents.getTxt());


            if (mContents.getMatch() == 1) {


                holder.imgAnim2.setImageResource(R.drawable.green_panel);
                if (mContents.getTxt() == null || mContents.getTxt().equals("")) {

                    holder.imgAnim.setVisibility(View.VISIBLE);
                    holder.txtContents.setText("");
                    Log.e("image e", "img :" + Global.IMAGE_URL + mContents.getImg());
                    String loc = "loc";
                    if (Global.levelId == 1) {
                        loc = MainActivity.image;
                    } else if (Global.levelId == 2) {
                        loc = MainActivity.image;
                    } else if (Global.levelId == 3) {
                        loc = MainActivity.image;
                    } else if (Global.levelId == 4) {
                        loc = MainActivity.image;
                    }
                    Bitmap bmp = BitmapFactory.decodeFile(loc + "/" + mContents.getImg());

                    holder.imgAnim.setImageBitmap(bmp);

                } else {
                    holder.txtContents.setText(mContents.getTxt());
                    holder.imgAnim.setVisibility(View.GONE);
                }
            } else {
                holder.txtContents.setText("");
                holder.imgAnim.setVisibility(View.GONE);
                holder.imgAnim2.setImageResource(R.drawable.yellow_panel);
                holder.txtContents.setBackgroundColor(Color.TRANSPARENT);
            }
        } else if (Global.logic == 3) {

            if (mContents.getMatch() == 1) {

            } else {
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
        } else if (Global.logic == 4) {
            holder.txtContents.setTextColor(0xffff00ff);
            holder.txtContents.setTextSize(20);
            holder.imgAnim2.setImageResource(R.drawable.green_panel);
            if (mContents.getTxt() == null || mContents.getTxt().equals("")) {

                holder.imgAnim.setVisibility(View.VISIBLE);
                holder.txtContents.setText("");
                Log.e("image e", "img :" + Global.IMAGE_URL + mContents.getImg());
                String loc = "loc";
                if (Global.levelId == 1) {
                    loc = MainActivity.image;
                } else if (Global.levelId == 2) {
                    loc = MainActivity.image;
                } else if (Global.levelId == 3) {
                    loc = MainActivity.image;
                } else if (Global.levelId == 4) {
                    loc = MainActivity.image;
                }
                Bitmap bmp = BitmapFactory.decodeFile(loc + "/" + mContents.getImg());

                holder.imgAnim.setImageBitmap(bmp);

            } else {
                holder.txtContents.setText(mContents.getTxt());
                holder.imgAnim.setVisibility(View.GONE);
            }
            if (mContents.getMatch() == 1) {
                holder.imgAnim2.setImageResource(R.drawable.green_panel);
            } else {
                holder.imgAnim2.setImageResource(R.drawable.yellow_panel);
                holder.txtContents.setBackgroundColor(0);
            }
        }
       /* else if (Global.subLevelId == 6) {
            if (mContents.getMatch() == 1) {

            } else {
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
        } else if (Global.subLevelId == 15) {
            if (mContents.getClick() == Global.IMAGE_OPEN) {

            } else {
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
        } else if (Global.subLevelId == 19) {
            if (mContents.getMatch() == 1) {

            } else {
                holder.imgAnim2.setImageResource(R.drawable.yellow_panel);
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContents.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
        }*/

    }

    @Override
    public int getItemCount() {
        return textArrayList.size();
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

    private void dialogShowWithWordArray(final int pos) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_show_with_word_text);
        RelativeLayout changeColor2 = (RelativeLayout) dialog.findViewById(R.id.dia_relativeLayout2);
        final TextView txt1 = (TextView) dialog.findViewById(R.id.txtOne);
        TextView txt2 = (TextView) dialog.findViewById(R.id.txtTwo);
        TextView txt3 = (TextView) dialog.findViewById(R.id.txtThree);
        TextView txt4 = (TextView) dialog.findViewById(R.id.txtFour);
        ImageView img1 = (ImageView) dialog.findViewById(R.id.imgOne);
        ImageView img2 = (ImageView) dialog.findViewById(R.id.imgTwo);
        ImageView img3 = (ImageView) dialog.findViewById(R.id.imgThree);
        Utils.setFont(context, "carterone", txt1, txt2, txt3, txt4);
        ImageView imgSound = (ImageView) dialog.findViewById(R.id.imgSoundOne);
        ImageView imgBack = (ImageView) dialog.findViewById(R.id.imgBackOne);
        ImageView imgForward = (ImageView) dialog.findViewById(R.id.imgForward1);
        txt1.setText(textArrayList.get(pos).getTxt());

        sounds = MainActivity.sounds;
        mainSounds = MainActivity.sounds;
        imageWords = MainActivity.image;
//        if (Global.levelId == 1) {
//
//        } else if (Global.levelId == 2) {
//            mainSounds = MainActivity.sounds;
//            sounds = MainActivity.sounds;
//            imageWords = MainActivity.image;
//        } else if (Global.levelId == 3) {
//            mainSounds = MainActivity.sounds;
//            sounds = MainActivity.sounds;
//            imageWords = MainActivity.image;
//        } else if (Global.levelId == 4) {
//            mainSounds = MainActivity.sounds;
//            sounds = MainActivity.sounds;
//            imageWords = MainActivity.image;
//        }

        imgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.PlaySound(mainSounds + File.separator + mContents.getAud());
            }
        });


        imgForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Global.GAME_INDEX_POSITION >= textArrayList.size() - 1) {
                    Utils.toastMassage(context, "level finish");
//                    Collections.shuffle(textArrayList);
                    dialog.dismiss();

                } else {
                    Global.GAME_INDEX_POSITION = Global.GAME_INDEX_POSITION + 1;

                    mContents = textArrayList.get(Global.GAME_INDEX_POSITION);

                    if (Global.levelId == 1) {


                        mContents.setWords(db.getBanglaWordsData(mContents.getMid()));
                    } else if (Global.levelId == 2) {

                        mContents.setWords(db.getBanglaMathWordsData(mContents.getMid()));
                    } else if (Global.levelId == 3) {

                        mContents.setWords(db.getEnglishWordsData(mContents.getMid()));
                    } else if (Global.levelId == 4) {

                        mContents.setWords(db.getMathWordsData(mContents.getMid()));
                    }

                    dialogShowWithWordArray(Global.GAME_INDEX_POSITION);
//                    txt1.setText(textArrayList.get(pos).getTxt());
                    dialog.dismiss();
                }
            }
        });
        Log.e("TEST", "s:" + mContents.getWords().size());
        if (Global.levelId == 1) {
            Utils.changeUIcolor(context, Global.uriBangla, changeColor2);
        } else if (Global.levelId == 2) {
            Utils.changeUIcolor(context, Global.uriOngko, changeColor2);
        } else if (Global.levelId == 3) {
            Utils.changeUIcolor(context, Global.uriEnglish, changeColor2);
        }
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Global.GAME_INDEX_POSITION <= 0) {
                    Utils.toastMassage(context, "level finish");

                    dialog.dismiss();

                } else {
                    Global.GAME_INDEX_POSITION = Global.GAME_INDEX_POSITION - 1;

                    mContents = textArrayList.get(Global.GAME_INDEX_POSITION);

                    if (Global.levelId == 1) {


                        mContents.setWords(db.getBanglaWordsData(mContents.getMid()));
                    } else if (Global.levelId == 2) {

                        mContents.setWords(db.getBanglaMathWordsData(mContents.getMid()));
                    } else if (Global.levelId == 3) {

                        mContents.setWords(db.getEnglishWordsData(mContents.getMid()));
                    } else if (Global.levelId == 4) {

                        mContents.setWords(db.getMathWordsData(mContents.getMid()));
                    }


                    dialogShowWithWordArray(Global.GAME_INDEX_POSITION);
//                    txt1.setText(textArrayList.get(pos).getTxt());
                    Utils.toastMassage(context, "Position");
                    dialog.dismiss();
                }
//
            }
        });

        if (mContents.getWords().size() == 4) {
//            txt1.setText(mContents.getWords().get(0).getWword());
            txt2.setText(mContents.getWords().get(0).getWword());
            txt3.setText(mContents.getWords().get(1).getWword());
            txt4.setText(mContents.getWords().get(2).getWword());

            Bitmap bmp = BitmapFactory.decodeFile(imageWords + File.separator + mContents.getWords().get(0).getWimg());
            Bitmap bmp2 = BitmapFactory.decodeFile(imageWords + File.separator + mContents.getWords().get(1).getWimg());
            Bitmap bmp3 = BitmapFactory.decodeFile(imageWords + File.separator + mContents.getWords().get(2).getWimg());
            img1.setImageBitmap(bmp);
            img2.setImageBitmap(bmp2);
            img3.setImageBitmap(bmp3);

            img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Utils.PlaySound(sounds + File.separator + mContents.getWords().get(0).getWsound());
                }
            });
            img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Utils.PlaySound(sounds + File.separator + mContents.getWords().get(1).getWsound());

                }
            });
            img3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Utils.PlaySound(sounds + File.separator + mContents.getWords().get(2).getWsound());

                }
            });

//            String url = Global.IMAGE_URL + mContents.getWords().get(0).getWimg();
//            String url2 = Global.IMAGE_URL + mContents.getWords().get(1).getWimg();
//            String url3 = Global.IMAGE_URL + mContents.getWords().get(2).getWimg();
//            Picasso.with(context)
//                    .load(url)
//                    .into(img1);
//            Picasso.with(context)
//                    .load(url2)
//                    .into(img2);
//            Picasso.with(context)
//                    .load(url3)
//                    .into(img3);
        } else if (mContents.getWords().size() == 3) {
            txt2.setText(mContents.getWords().get(0).getWword());
            txt3.setText(mContents.getWords().get(1).getWword());
            txt4.setText(mContents.getWords().get(2).getWword());
            Bitmap bmp = BitmapFactory.decodeFile(imageWords + File.separator + mContents.getWords().get(0).getWimg());
            Bitmap bmp2 = BitmapFactory.decodeFile(imageWords + File.separator + mContents.getWords().get(1).getWimg());
            Bitmap bmp3 = BitmapFactory.decodeFile(imageWords + File.separator + mContents.getWords().get(2).getWimg());
            img1.setImageBitmap(bmp);
            img2.setImageBitmap(bmp2);
            img3.setImageBitmap(bmp3);

            img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Utils.PlaySound(sounds + File.separator + mContents.getWords().get(0).getWsound());
                }
            });
            img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Utils.PlaySound(sounds + File.separator + mContents.getWords().get(1).getWsound());

                }
            });
            img3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Utils.PlaySound(sounds + File.separator + mContents.getWords().get(2).getWsound());

                }
            });
        } else if (mContents.getWords().size() == 2) {
            txt2.setText(mContents.getWords().get(0).getWword());
            txt3.setText(mContents.getWords().get(1).getWword());
            Bitmap bmp = BitmapFactory.decodeFile(imageWords + File.separator + mContents.getWords().get(0).getWimg());
            Bitmap bmp2 = BitmapFactory.decodeFile(imageWords + File.separator + mContents.getWords().get(1).getWimg());
            img1.setImageBitmap(bmp);
            img2.setImageBitmap(bmp2);

            img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Utils.PlaySound(sounds + File.separator + mContents.getWords().get(0).getWsound());
                }
            });
            img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Utils.PlaySound(sounds + File.separator + mContents.getWords().get(1).getWsound());

                }
            });
        } else if (mContents.getWords().size() == 1) {
            txt2.setText(mContents.getWords().get(0).getWword());
            Bitmap bmp = BitmapFactory.decodeFile(imageWords + File.separator + mContents.getWords().get(0).getWimg());
            img1.setImageBitmap(bmp);

            img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Utils.PlaySound(sounds + File.separator + mContents.getWords().get(0).getWsound());
                }
            });

        }

        dialog.show();

    }

    private void dialogShowWithWordsList() {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_show_text);
        RelativeLayout changeColor2 = (RelativeLayout) dialog.findViewById(R.id.relLayout);

        TextView txt1 = (TextView) dialog.findViewById(R.id.txt1);
        TextView txt2 = (TextView) dialog.findViewById(R.id.txt2);
        TextView txt3 = (TextView) dialog.findViewById(R.id.txt3);
        TextView txt4 = (TextView) dialog.findViewById(R.id.txt4);
        ImageView img1 = (ImageView) dialog.findViewById(R.id.img1);
        ImageView img2 = (ImageView) dialog.findViewById(R.id.img2);
        ImageView img3 = (ImageView) dialog.findViewById(R.id.img3);
        ImageView img4 = (ImageView) dialog.findViewById(R.id.img4);
        Utils.setFont(context, "carterone", txt1, txt2, txt3, txt4);
        ImageView imgBack = (ImageView) dialog.findViewById(R.id.imgBack);
        ImageView imgClose = (ImageView) dialog.findViewById(R.id.imgClose);
        if (Global.levelId == 1) {
            Utils.changeUIcolor(context, Global.uriBangla, changeColor2);
        } else if (Global.levelId == 2) {
            Utils.changeUIcolor(context, Global.uriOngko, changeColor2);
        } else if (Global.levelId == 3) {
            Utils.changeUIcolor(context, Global.uriEnglish, changeColor2);
        }
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Global.GAME_INDEX_POSITION <= 0) {
                    Utils.toastMassage(context, "level finish");

                    dialog.dismiss();

                } else {
                    Global.GAME_INDEX_POSITION = Global.GAME_INDEX_POSITION - 1;

                    mContents = textArrayList.get(Global.GAME_INDEX_POSITION);

                    if (Global.subLevelId == 3) {

                        mContents.setWords(db.getBanglaWordsData(mContents.getMid()));
                    }
                    if (Global.subLevelId == 6) {

                        mContents.setWords(db.getMathWordsData(mContents.getMid()));
                    }
                    if (Global.subLevelId == 9) {

                        mContents.setWords(db.getEnglishWordsData(mContents.getMid()));
                    }

                    dialogShowWithWordsList();
                    Utils.toastMassage(context, "Position");
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
            String url = Global.IMAGE_URL + mContents.getWords().get(0).getWimg();
            Log.e("imgae", "url is" + url);
            Picasso.with(context)
                    .load(url)
                    .into(img1);
//            img1.setImageResource(mContents.getWords().get(0).getWimg());


        }
        dialog.show();
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
                    if (getAdapterPosition() < 0)
                        return;

                    mContents = textArrayList.get(getAdapterPosition());
                    Global.GAME_INDEX_POSITION = getAdapterPosition();


                    if (Global.logic == 1) {
//                            || Global.subLevelId == 4 || Global.subLevelId == 8 || Global.subLevelId == 13) {
//                        String mSound = "msound";
//                        if (Global.levelId == 1) {
//                            mSound = MainActivity.sounds;
//                        } else if (Global.levelId == 2) {
//                            mSound = MainActivity.sounds;
//                        } else if (Global.levelId == 3) {
//                            mSound = MainActivity.sounds;
//                        } else if (Global.levelId == 4) {
//                            mSound = MainActivity.sounds;
//                        }
//                        Utils.PlaySound(mSound + File.separator + mContents.getAud());
                        gameLogic.textClick(mContents, getAdapterPosition(), textArrayList.size(), itemView, txtContents, imgAnim2);
//                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition(), imgAnim2);
                    } else if (Global.logic == 2) {
//                            || Global.subLevelId == 5 || Global.subLevelId == 9 || Global.subLevelId == 14) {

//                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition(), imgAnim2);
                        gameLogic.imageClick(mContents, getAdapterPosition(), textArrayList.size(), itemView, imgAnim2);
                    } else if (Global.logic == 3) {


                        if (Global.levelId == 1) {


                            mContents.setWords(db.getBanglaWordsData(mContents.getMid()));
                        } else if (Global.levelId == 2) {

                            mContents.setWords(db.getBanglaMathWordsData(mContents.getMid()));
                        } else if (Global.levelId == 3) {

                            mContents.setWords(db.getEnglishWordsData(mContents.getMid()));
                        } else if (Global.levelId == 4) {

                            mContents.setWords(db.getMathWordsData(mContents.getMid()));
                        }

                        dialogShowWithWordArray(getAdapterPosition());
//                        dialogShowWithWordsList();
                    } else if (Global.logic == 4) {
                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition(), imgAnim2);
                    }
//                    else if (Global.subLevelId == 6) {
//                        mContents.setWords(db.getBanglaMathWordsData(mContents.getMid()));
//                        dialogShowWithWordArray(getAdapterPosition());
//                    } else if (Global.subLevelId == 15) {
//                        mContents.setWords(db.getMathWordsData(mContents.getMid()));
//                        dialogShowWithWordArray(getAdapterPosition());
////                        gameLogic.forLevel2(itemView, mContents, textArrayList.size(), txtContents, getAdapterPosition(), imgAnim2);
////                        gameLogic.imageClick(mContents, getAdapterPosition(), textArrayList.size(), itemView);
//                    } else if (Global.subLevelId == 19) {
//                        dialogShowWithWordsList();
//                    }


                }
            });
        }
    }
}
