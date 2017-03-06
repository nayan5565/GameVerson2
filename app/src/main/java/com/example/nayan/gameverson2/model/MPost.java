package com.example.nayan.gameverson2.model;

import java.util.ArrayList;

/**
 * Created by Nayan on 3/7/2017.
 */

public class MPost {
    private ArrayList<MSubLevel> mSubLevels;
    private String deviceId, email;

    public ArrayList<MSubLevel> getmSubLevels() {
        return mSubLevels;
    }

    public void setmSubLevels(ArrayList<MSubLevel> mSubLevels) {
        this.mSubLevels = mSubLevels;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
