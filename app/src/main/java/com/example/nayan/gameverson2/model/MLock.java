package com.example.nayan.gameverson2.model;

/**
 * Created by NAYAN on 11/16/2016.
 */
public class MLock {
    private int unlockNextLevel;
    private int id;
    private int bestPoint;

    public int getTotal_pont() {
        return total_pont;
    }

    public void setTotal_pont(int total_pont) {
        this.total_pont = total_pont;
    }

    private int total_pont;

    public int getBestPoint() {
        return bestPoint;
    }

    public void setBestPoint(int bestPoint) {
        this.bestPoint = bestPoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnlockNextLevel() {
        return unlockNextLevel;
    }

    public void setUnlockNextLevel(int unlockNextLevel) {
        this.unlockNextLevel = unlockNextLevel;
    }
}
