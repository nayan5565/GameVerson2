package com.example.nayan.gameverson2.model;

import java.util.ArrayList;

/**
 * Created by NAYAN on 2/8/2017.
 */
public class MAllContent {
    ArrayList<MWords> words=new ArrayList<>();
    private int mid;
    private int lid;
    private String img;
    private String aud;
    private String txt;
    private String vid;
    private String sen;
    private int presentType,presentId;

    public int getPresentType() {
        return presentType;
    }

    public void setPresentType(int presentType) {
        this.presentType = presentType;
    }

    public int getPresentId() {
        return presentId;
    }

    public void setPresentId(int presentId) {
        this.presentId = presentId;
    }

    public ArrayList<MWords> getWords() {
        return words;
    }

    public void setWords(ArrayList<MWords> words) {
        this.words = words;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getSen() {
        return sen;
    }

    public void setSen(String sen) {
        this.sen = sen;
    }
}
