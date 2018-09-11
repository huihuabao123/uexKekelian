package com.kekelian.bean;

public class exerciseResultBean {

    private int correctItemCount;
    private int finishItemCount;
    private String levelRecordId;
    private String levelTypeName;
    private int score;
    private int totalItemCount;

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

    public String getLevelRecordId() {
        return levelRecordId;
    }

    public void setLevelRecordId(String levelRecordId) {
        this.levelRecordId = levelRecordId;
    }

    public String getLevelTypeName() {
        return levelTypeName;
    }

    public void setLevelTypeName(String levelTypeName) {
        this.levelTypeName = levelTypeName;
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
}
