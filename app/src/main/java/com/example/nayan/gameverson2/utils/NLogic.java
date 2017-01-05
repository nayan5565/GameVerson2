package com.example.nayan.gameverson2.utils;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.activity.SubLevelActivity;
import com.example.nayan.gameverson2.model.MContents;
import com.example.nayan.gameverson2.model.MLock;
import com.example.nayan.gameverson2.model.MSubLevel;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by NAYAN on 8/20/2016.
 */
public class NLogic {
    private int previousId, count, counter, clickCount, matchWinCount, previousType, gameWinCount, previousPoint, presentPoint, bestPoint;
    private static NLogic nLogic;
    private ArrayList<MContents> list;
    private SharedPreferences preferences;
    private Context context;
    private Handler handler = new Handler();
    private RecyclerView.Adapter gameAdapter;
    private MContents previousMcontents;
    View view1;
    TextView textView2;


    private NLogic() {

    }

    public static NLogic getInstance(Context context1) {
        nLogic = new NLogic();
        nLogic.context = context1;

        return nLogic;

    }

    public void callData(ArrayList<MContents> list, RecyclerView.Adapter adapter) {
        this.list = list;
        this.gameAdapter = adapter;
    }

    public void setLevel(MContents mContents) {
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

        if (SubLevelActivity.mSubLevels.size() - 1 > Global.INDEX_POSISION) {
            lock = new MLock();
            Log.e("global", "index" + Global.INDEX_POSISION);
            Log.e("array List", "size :" + SubLevelActivity.mSubLevels.size());
            MSubLevel mSubLevel = SubLevelActivity.mSubLevels.get(Global.INDEX_POSISION + 1);
            lock.setId(mSubLevel.getLid());
            lock.setUnlockNextLevel(1);
            db.addLockData(lock);
        }

    }

    public void textClick(MContents mContents, int pos, final int listSize, View view, TextView view2) {
        counter++;
        Log.e("counter", "is" + counter);

        //don't work if mid !=1 at first time because first time click count=1
        if (mContents.getMid() == clickCount + 1) {
            list.get(pos).setClick(Utils.IMAGE_ON);
            gameAdapter.notifyDataSetChanged();
            //clickcount store present mid
            flipAnimation(view);
            view2.setBackgroundColor(0xff888888);
            clickCount = mContents.getMid();
            count++;

            Toast.makeText(context, mContents.getTxt(), Toast.LENGTH_SHORT).show();
        } else {
            shakeAnimation(view);
            view2.setBackgroundColor(0xffff0000);
            Toast.makeText(context, "wrong click", Toast.LENGTH_SHORT).show();
        }
        if (count == listSize) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    resetList(listSize);
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
            }, 1200);
            savePoint(listSize);

            Toast.makeText(context, "game over", Toast.LENGTH_SHORT).show();

        }


    }
    public void forLevel2(View itemView,MContents mContents){
        if (mContents.getMatch()==1){
            Toast.makeText(context,"matched",Toast.LENGTH_SHORT).show();
            Log.e("s",":1");
            return;
        }
        if (previousId==0){
            mContents.setMatch(1);
            gameAdapter.notifyDataSetChanged();
            previousMcontents=mContents;
            previousId=mContents.getMid();
            Log.e("s",":3");
            return;
        }
        if (previousId==mContents.getMid()){
            Toast.makeText(context,"same click",Toast.LENGTH_SHORT).show();
            Log.e("s",":2");
            return;
        }

        else {
            Log.e("s",":4");
            if (mContents.getPresentType()==previousMcontents.getPresentType()){
                mContents.setMatch(1);
                Toast.makeText(context,"match",Toast.LENGTH_SHORT).show();
            }
            else {
                mContents.setMatch(0);
                Toast.makeText(context,"wrong",Toast.LENGTH_SHORT).show();
            }
            previousId=0;
            previousMcontents=mContents;
            gameAdapter.notifyDataSetChanged();
        }

    }
    public void imageClick(final MContents mImage, int pos, final int listSize, final View view) {
        Log.e("Loge", "present id ::" + mImage.getPresentId());
        Log.e("position", "pos" + pos);

        counter++;

        if (previousId == mImage.getPresentId() || count > 1 || mImage.getClick() == Utils.IMAGE_ON) {
            Log.e("previous type", "same: " + mImage.getPresentType());
            Log.e("click over 1", "count: " + count);
//            shakeAnimation(view);
            Toast.makeText(context, "same click", Toast.LENGTH_SHORT).show();
            return;
        }
        clickCount++;

        list.get(pos).setClick(Utils.IMAGE_ON);

        flipAnimation(view);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameAdapter.notifyDataSetChanged();
//                textView.setBackgroundColor(0xff888888);
            }
        }, 400);

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
                            resetList(listSize);

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
//                shakeAnimation(view);
//                textView.setBackgroundColor(0xffff0000);


                final int previous = previousId;

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        Utils.getSound(context, R.raw.fail);
                        for (int i = 0; i < listSize; i++) {
                            if (list.get(i).getPresentId() == previous || list.get(i).getPresentId() == mImage.getPresentId()) {
                                list.get(i).setClick(Utils.IMAGE_OFF);
                                Toast.makeText(context, "did not match", Toast.LENGTH_SHORT).show();
                                flipAnimation2(view);
//
                            }
                        }
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gameAdapter.notifyDataSetChanged();
                            }
                        },400);

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

        if (previousType == mImage.getPresentType() || count > 1 || mImage.getClick() == Utils.IMAGE_ON) {
            Log.e("previous type", "same: " + mImage.getPresentType());
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

        list.get(pos).setClick(Utils.IMAGE_ON);

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

            if (previousId == mImage.getMid()) {
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
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            resetList(listSize);
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

                final int perevious = previousType;

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        Utils.getSound(context, R.raw.fail);
                        for (int i = 0; i < listSize; i++) {
                            if (list.get(i).getPresentType() == perevious || list.get(i).getPresentType() == mImage.getPresentType()) {
                                list.get(i).setClick(Utils.IMAGE_OFF);
                                Toast.makeText(context, "did not match", Toast.LENGTH_SHORT).show();
//
                            }
                        }
                        gameAdapter.notifyDataSetChanged();
                        count = 0;
                    }
                }, 800);
                previousId = 0;
                previousType = 0;
                return;
            }
        }
        previousId = mImage.getMid();
        previousType = mImage.getPresentType();
    }
    public void flipAnimation(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationY", -180, 0);
        animator.setDuration(500);
        animator.start();
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


    private void resetList(int listSize) {
        for (int i = 0; i < listSize; i++) {
            list.get(i).setClick(Utils.IMAGE_OFF);
        }
        Collections.shuffle(list);
        clickCount = 0;
        matchWinCount = 0;
        previousType = 0;
        previousId = 0;
        count = 0;
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
