package com.kekelian.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class UnitTestTabRecordBean implements Parcelable {
    /**
     * correctItemCount : 0
     * finishItemCount : 0
     * isLocked : true
     * score : 0
     * totalItemCount : 0
     * unitTestRecordId : 18f95dd3536d488c94a5cd210dabee12
     */

    private int correctItemCount;
    private int finishItemCount;
    private boolean isLocked;
    private int score;
    private int totalItemCount;
    private String unitTestRecordId;

    public int getCorrectItemCount() {
        return correctItemCount;
    }

    public void setCorrectItemCount(int correctItemCount) {
        this.correctItemCount = correctItemCount;
    }

    public int getFinishItemCount() {
        return finishItemCount;
    }

    public void setFinishItemCount(int finishItemCount) {
        this.finishItemCount = finishItemCount;
    }

    public boolean isIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(int totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public String getUnitTestRecordId() {
        return unitTestRecordId;
    }

    public void setUnitTestRecordId(String unitTestRecordId) {
        this.unitTestRecordId = unitTestRecordId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.correctItemCount);
        dest.writeInt(this.finishItemCount);
        dest.writeByte(this.isLocked ? (byte) 1 : (byte) 0);
        dest.writeInt(this.score);
        dest.writeInt(this.totalItemCount);
        dest.writeString(this.unitTestRecordId);
    }

    public UnitTestTabRecordBean() {
    }

    protected UnitTestTabRecordBean(Parcel in) {
        this.correctItemCount = in.readInt();
        this.finishItemCount = in.readInt();
        this.isLocked = in.readByte() != 0;
        this.score = in.readInt();
        this.totalItemCount = in.readInt();
        this.unitTestRecordId = in.readString();
    }

    public static final Parcelable.Creator<UnitTestTabRecordBean> CREATOR = new Parcelable.Creator<UnitTestTabRecordBean>() {
        @Override
        public UnitTestTabRecordBean createFromParcel(Parcel source) {
            return new UnitTestTabRecordBean(source);
        }

        @Override
        public UnitTestTabRecordBean[] newArray(int size) {
            return new UnitTestTabRecordBean[size];
        }
    };
}
