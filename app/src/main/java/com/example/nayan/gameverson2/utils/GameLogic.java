package com.example.nayan.gameverson2.utils;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.activity.GameActivity;
import com.example.nayan.gameverson2.model.MAllContent;
import com.example.nayan.gameverson2.model.MContents;
import com.example.nayan.gameverson2.model.MLock;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.model.MWords;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.nayan.gameverson2.activity.SubLevelActivity.mSubLevels;

/**
 * Created by NAYAN on 8/20/2016.
 */
public class GameLogic {
    private int previousId, count, counter, clickCount, matchWinCount, previousType, gameWinCount, previousPoint, presentPoint, bestPoint, idPrevious;
    private static GameLogic gameLogic;
    private ArrayList<MAllContent> list;
    private ArrayList<MWords> list2;
    private SharedPreferences preferences;
    private Context context;
    private Handler handler = new Handler();
    private RecyclerView.Adapter gameAdapter;
    private MAllContent previousMcontents = new MAllContent();
    private RecyclerView recyclerView;
    private LinearLayout changeColor;
    View view1;
    TextView textView2;
    String uriGreen = "@drawable/green_panel";


    private GameLogic() {

    }

    public static GameLogic getInstance(Context context1) {
        gameLogic = new GameLogic();
        gameLogic.context = context1;

        return gameLogic;

    }

    public void callData(ArrayList<MAllContent> list, RecyclerView.Adapter adapter) {
        this.list = list;
        clickCount = getMin() - 1;
        this.gameAdapter = adapter;
    }

    public void setLevel(MAllContent mContents) {
        this.previousMcontents = mContents;

    }


    private void saveDb() {
        DatabaseHelper db = DatabaseHelper.getInstance(context);
        MLock lock = db.getLocalData(Global.SUB_LEVEL_ID);
        if (lock == null) {
            lock = new MLock();
        }
        lock.setId(Global.SUB_LEVEL_ID);
        lock.setBestPoint(Utils.bestPoint);
//        lock.setUnlockNextLevel(1);
        db.addLockData(lock);

        if (mSubLevels.size() - 1 > Global.SUB_INDEX_POSITION) {
            lock = new MLock();
            Log.e("global", "index" + Global.SUB_INDEX_POSITION);
            Log.e("array List", "size :" + mSubLevels.size());
            MSubLevel mSubLevel = mSubLevels.get(Global.SUB_INDEX_POSITION + 1);
            lock.setId(mSubLevel.getLid());
            lock.setUnlockNextLevel(1);
            db.addLockData(lock);
        }

    }

    public int getMin() {
        int min = list.get(0).getMid();
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getMid() < min) {
                min = list.get(i).getMid();
            }
        }
        return min;
    }

    public void textClick(final MAllContent mContents, int pos, final int listSize, final View view, TextView view2, final ImageView imageView) {
        counter++;

        Log.e("counter", "is" + counter);
        int imageResourceGreen = context.getResources().getIdentifier(uriGreen, null, context.getPackageName());
        final Drawable resGreen = context.getResources().getDrawable(imageResourceGreen);
        //don't work if mid !=1 at first time because first time click count=1
        if (mContents.getMid() == clickCount + 1) {
            list.get(pos).setMatch(1);
//            list.get(pos).setClick(Utils.IMAGE_OPEN);
            Utils.getSound(context, R.raw.click);
            gameAdapter.notifyDataSetChanged();
            //clickcount store present mid
            flipAnimation(view);
            imageView.setImageResource(R.drawable.green_panel);
//            view2.setBackgroundColor(0xff888888);
            clickCount = mContents.getMid();
            count++;

            Toast.makeText(context, mContents.getTxt(), Toast.LENGTH_SHORT).show();
        } else {
            Utils.getSound(context, R.raw.fail);
            shakeAnimation(view);
            imageView.setImageResource(R.drawable.red_panel);
//            view2.setBackgroundColor(0xffff0000);
            Toast.makeText(context, "wrong click", Toast.LENGTH_SHORT).show();
        }
        if (count == listSize) {


            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    dialogShowForLevelClear(listSize);


                }
            }, 1200);

            savePoint(listSize);

            Toast.makeText(context, "game over", Toast.LENGTH_SHORT).show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Utils.getSound(context, R.raw.shuffle);
                }
            }, 500);

        }


    }


    public void forLevel2(final View itemView, final MAllContent mContents, final int listSize, TextView textView, int pos, final ImageView imageView) {
        counter++;
        int imageResourceGreen = context.getResources().getIdentifier(uriGreen, null, context.getPackageName());
        final Drawable resGreen = context.getResources().getDrawable(imageResourceGreen);
        if (mContents.getMatch() == 1) {
            Toast.makeText(context, "matched", Toast.LENGTH_SHORT).show();
            Log.e("s", ":1");
            return;
        }
//        list.get(pos).setClick(Utils.IMAGE_OPEN);
        list.get(pos).setMatch(1);
        if (previousId == 0) {
            mContents.setMatch(1);
//            mContents.setClick(Utils.IMAGE_OPEN);
//            list.get(pos).setClick(Utils.IMAGE_OPEN);
            previousMcontents = mContents;
            previousId = mContents.getMid();
            flipAnimation(itemView);

//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    gameAdapter.notifyDataSetChanged();
//                }
//            }, 400);
            Log.e("s", ":3");
            return;
        }

        if (previousId == mContents.getMid()) {
            Toast.makeText(context, "same click", Toast.LENGTH_SHORT).show();

            Log.e("s", ":2");
            return;

        } else {

            Log.e("s", ":4");
            if (mContents.getPresentType() == previousMcontents.getPresentType()) {
                mContents.setMatch(1);
//                mContents.setClick(Utils.IMAGE_OPEN);
                matchWinCount++;
                Toast.makeText(context, "match", Toast.LENGTH_SHORT).show();

                if (matchWinCount == listSize / 2) {

//                    textView.setBackgroundColor(0);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            resetList2(listSize);
//                            Utils.dialogShowForLevelClear(context,listSize,presentPoint);
                            dialogShowForLevelClear(listSize);
                        }
                    }, 1500);
                    savePoint(listSize);
                    gameWinCount++;
                }

            } else {
                previousMcontents.setMatch(0);
                mContents.setMatch(0);
                imageView.setImageResource(R.drawable.red_panel);
//                mContents.setClick(Utils.IMAGE_OFF);
//                previousMcontents.setClick(Utils.IMAGE_OFF);
                Toast.makeText(context, "wrong", Toast.LENGTH_SHORT).show();
//                textView.setBackgroundColor(0xffff0000);
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        flipAnimation2(itemView);
//                    }
//                },800);

            }
            previousId = 0;
            previousMcontents = mContents;
            flipAnimation(itemView);
        }
    }

    public void imageClick(final MAllContent mImage, int pos, final int listSize, final View view, final ImageView imageView) {
        Log.e("Loge", "present id ::" + mImage.getPresentId());
        Log.e("position", "pos" + pos);

        counter++;

        if (previousId == mImage.getMid() || count > 1 || mImage.getMatch() ==1) {
            Log.e("previous type", "same: " + mImage.getPresentType());
            Log.e("click over 1", "count: " + count);
//            shakeAnimation(view);
            Toast.makeText(context, "same click", Toast.LENGTH_SHORT).show();
            return;
        }
        clickCount++;

        list.get(pos).setMatch(1);

        flipAnimation(view);
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                gameAdapter.notifyDataSetChanged();
////                textView.setBackgroundColor(0xff888888);
//            }
//        }, 400);

        count++;


        Log.e("click", "count: " + count);
//        Utils.getSound(context, R.raw.click);
        if (count == 2) {

            if (previousType == mImage.getPresentType()) {
                Toast.makeText(context, "match", Toast.LENGTH_SHORT).show();
                Log.e("log", "match win count : " + matchWinCount);
                Log.e("previous id", "MID : " + previousId);


                matchWinCount++;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

//                        Utils.getSound(context, R.raw.match2);
                        count = 0;


                    }
                }, 800);


                if (matchWinCount == listSize / 2) {

//                    textView.setBackgroundColor(0);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            resetList(listSize);
                            dialogShowForLevelClear(listSize);
                        }
                    }, 1500);
//                    VungleAdManager.getInstance(context).play();
                    savePoint(listSize);


                    Toast.makeText(context, "game over", Toast.LENGTH_SHORT).show();
                    gameWinCount++;
                    Log.e("log", "game over : " + gameWinCount);
                }

                return;
            } else {
//                shakeAnimation(view);
//                textView.setBackgroundColor(0xffff0000);


                final int previous = previousId;

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        Utils.getSound(context, R.raw.fail);
                        for (int i = 0; i < listSize; i++) {
                            if (list.get(i).getMid() == previous || list.get(i).getMid() == mImage.getMid()) {
                                list.get(i).setMatch(0);
                                gameAdapter.notifyDataSetChanged();
                                imageView.setImageResource(R.drawable.red_panel);
                                Toast.makeText(context, "did not match", Toast.LENGTH_SHORT).show();
//                                flipAnimation2(view);
//
                            }
                        }
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                gameAdapter.notifyDataSetChanged();
//                            }
//                        }, 400);

//                        textView.setBackgroundColor(0);
                        count = 0;
                    }
                }, 1000);
                previousId = 0;
                previousType = 0;
                return;
            }
        }
        previousId = mImage.getMid();
        previousType = mImage.getPresentType();
//        textView2 = textView;
//        view1=view;
    }


    public void imageClick2(final MContents mImage, int pos, final int listSize, final View view, final View view1) {
        Log.e("Loge", "present id ::" + mImage.getPresentId());
        Log.e("position", "pos" + pos);

        counter++;

        if (idPrevious == mImage.getPresentId() || count > 1 || mImage.getClick() == Utils.IMAGE_OPEN) {
            Log.e("previous id", "same: " + mImage.getPresentId());
            Log.e("click over 1", "count: " + count);

//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    flipAnimation2(view);
//                    flipAnimation2(view1);
//                }
//            },500);

            Toast.makeText(context, "same click", Toast.LENGTH_SHORT).show();
            return;
        }
        clickCount++;

        list.get(pos).setClick(Utils.IMAGE_OPEN);

        flipAnimation(view);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameAdapter.notifyDataSetChanged();
            }
        }, 400);

        count++;


        Log.e("click", "count: " + count);
//        Utils.getSound(context, R.raw.click);
        if (count == 2) {

            if (previousType == mImage.getPresentType()) {
                Toast.makeText(context, "match", Toast.LENGTH_SHORT).show();
                Log.e("log", "match win count : " + matchWinCount);
                Log.e("previous type", "type : " + previousType);


                matchWinCount++;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

//                        Utils.getSound(context, R.raw.match2);
                        count = 0;


                    }
                }, 800);


                if (matchWinCount == listSize / 2) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            resetList(listSize);
                            Dialog dialog = new Dialog(context);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.dialog_level_cleared);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            TextView txtPoint = (TextView) dialog.findViewById(R.id.txtLevelPoint);
                            TextView txtBestPoint = (TextView) dialog.findViewById(R.id.txtLevelBestPoint);
                            TextView txtScore = (TextView) dialog.findViewById(R.id.txtLevelScore);
                            txtBestPoint.setText("" + Utils.bestPoint);
                            txtScore.setText(presentPoint + "");
                            if (presentPoint == 50) {
                                txtPoint.setText(Utils.getIntToStar(1));
                            } else if (presentPoint == 75) {
                                txtPoint.setText(Utils.getIntToStar(2));
                            } else if (presentPoint == 100) {
                                txtPoint.setText(Utils.getIntToStar(3));
                            } else txtPoint.setText(Utils.getIntToStar(0));
                            dialog.show();
                        }
                    }, 1500);
//                    VungleAdManager.getInstance(context).play();
                    savePoint(listSize);

                    Toast.makeText(context, "game over", Toast.LENGTH_SHORT).show();
                    gameWinCount++;
                    Log.e("log", "game over : " + gameWinCount);
                }

                return;
            } else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        flipAnimation2(view);
                    }
                }, 500);

                final int perevious = idPrevious;

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        Utils.getSound(context, R.raw.fail);
                        for (int i = 0; i < listSize; i++) {
                            if (list.get(i).getPresentId() == perevious || list.get(i).getPresentId() == mImage.getPresentId()) {
                                list.get(i).setClick(Utils.IMAGE_OFF);
                                Toast.makeText(context, "did not match", Toast.LENGTH_SHORT).show();
//
                            }
                        }
                        gameAdapter.notifyDataSetChanged();
                        count = 0;
                    }
                }, 800);
                idPrevious = 0;
                previousType = 0;
                return;
            }
        }
        idPrevious = mImage.getMid();
        previousType = mImage.getPresentType();
    }

    private void dialogShowForLevelClear(final int listSize) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_level_cleared);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        changeColor = (LinearLayout) dialog.findViewById(R.id.dia_LenearLayout);
        final TextView txtPoint = (TextView) dialog.findViewById(R.id.txtLevelPoint);
        final TextView txtBestPoint = (TextView) dialog.findViewById(R.id.txtLevelBestPoint);
        final TextView txtScore = (TextView) dialog.findViewById(R.id.txtLevelScore);
        ImageView imgLevelMenu = (ImageView) dialog.findViewById(R.id.imgLevelMenu);
        ImageView imgFacebook = (ImageView) dialog.findViewById(R.id.imgFacebook);
        imgLevelMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                            Intent intent = new Intent(context, SubLevelActivity.class);
//                            intent.putExtra("id", Global.levelId);
//                            intent.putExtra("name", Global.levelName);
//                            context.startActivity(intent);
                ((Activity) context).finish();
                dialog.dismiss();


                Toast.makeText(context, "return game level", Toast.LENGTH_SHORT).show();
            }
        });
//        changeColor.setBackground(resGreen);
        ImageView imgReload = (ImageView) dialog.findViewById(R.id.btnLevelReload);
        imgReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                GameLogic.getInstance(context).resetList(listSize);
//                imageView.setImageResource(R.drawable.yellow_panel);

                resetList(listSize);
                dialog.dismiss();
                Toast.makeText(context, "Reload", Toast.LENGTH_SHORT).show();
            }


        });
        ImageView imgNextLevel = (ImageView) dialog.findViewById(R.id.imgLevelForward);
        imgNextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Global.SUB_INDEX_POSITION >= Global.parentName.size() - 1) {
                    Toast.makeText(context, "level finish", Toast.LENGTH_SHORT).show();

                    return;

                } else {
                    Global.SUB_LEVEL_ID = Global.SUB_LEVEL_ID + 1;
                    Global.SUB_INDEX_POSITION = Global.SUB_INDEX_POSITION + 1;

                    savePoint(listSize);
                    mSubLevels.get(Global.SUB_INDEX_POSITION).setUnlockNextLevel(1);
                    DatabaseHelper db = new DatabaseHelper(context);
                    db.addSubFromJsom(mSubLevels.get(Global.SUB_INDEX_POSITION));
                    GameActivity.getInstance().refresh(Global.SUB_INDEX_POSITION);
                }
                dialog.dismiss();
            }
        });
        imgFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/apple.fruit"));
                context.startActivity(browserIntent);
                dialog.dismiss();
            }
        });


        txtBestPoint.setText("" + Utils.bestPoint);
        txtScore.setText(presentPoint + "");
        if (presentPoint == 50) {
            txtPoint.setText(Utils.getIntToStar(1));
        } else if (presentPoint == 75) {
            txtPoint.setText(Utils.getIntToStar(2));
        } else if (presentPoint == 100) {
            txtPoint.setText(Utils.getIntToStar(3));
        } else txtPoint.setText(Utils.getIntToStar(0));
        dialog.show();
    }

    public void flipAnimation(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationY", -180, 0);
        animator.setDuration(500);
        animator.start();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameAdapter.notifyDataSetChanged();
            }
        }, 500);
    }

    public void flipAnimation2(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationY", 0, -180);
        animator.setDuration(500);
        animator.start();
    }

    public void shakeAnimation(View v) {
        // Create shake effect from xml resource
        Animation shake = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.shaking);
        // View element to be shaken

        // Perform animation
        v.startAnimation(shake);
    }


    public void resetList(int listSize) {
        for (int i = 0; i < listSize; i++) {
//            list.get(i).setClick(Utils.IMAGE_OFF);
//            imageView.setImageResource(R.drawable.yellow_panel);
            list.get(i).setMatch(0);
//
        }
//        previousMcontents.setMatch(0);
//        MAllContent mContents = new MAllContent();
//        previousMcontents = mContents;
        Collections.shuffle(list);
        clickCount = getMin() - 1;
        matchWinCount = 0;
        previousType = 0;
        previousId = 0;
        count = 0;
        counter = 0;
        gameAdapter.notifyDataSetChanged();

    }

    private void resetList2(int listSize) {
        for (int i = 0; i < listSize; i++) {
            list.get(i).setMatch(0);
        }
        Collections.shuffle(list);
        matchWinCount = 0;
        previousType = 0;
        previousId = 0;
        counter = 0;
        gameAdapter.notifyDataSetChanged();

    }

    private void savePoint(int listSize) {
        presentPoint = pointCount(listSize);
        if (presentPoint > Utils.bestPoint) {
            Utils.bestPoint = presentPoint;
            saveDb();
        }
    }

    private int pointCount(int listSize) {
        int point = 50;

        if (counter == listSize) {
            point = 100;
        } else if (counter > listSize && counter <= listSize + listSize / 2) {
            point = 75;
        }
        Log.e("pint", "point is" + point);
        return point;
    }

    public String showHistory() {

        Dialog dialog = new Dialog(context);
        dialog.setTitle("Status");
        dialog.setContentView(R.layout.row_d_best_point);
        TextView textView = (TextView) dialog.findViewById(R.id.txtBetPoint);
//        textView.setText("best point: " + previousMcontents.getBestpoint() + "\nWin count : " + previousMcontents.getLevelWinCount());
        TextView textView1 = (TextView) dialog.findViewById(R.id.txtTotalWin);
//        textView1.setText("no of total win: " + gameWinCount + "");
        dialog.show();
        return "";
    }

}
