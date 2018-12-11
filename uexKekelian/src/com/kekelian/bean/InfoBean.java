package com.kekelian.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class InfoBean implements Parcelable {

    private String menuId;
    private String userId;
    private String isVip;
    private String courseTextBookFlag;
    private String menuIndex;
    private String unitTitle;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public String getUnitTitle() {
        return unitTitle;
    }

    public void setUnitTitle(String unitTitle) {
        this.unitTitle = unitTitle;
    }

    public String getCourseTextBookFlag() {
        return courseTextBookFlag;
    }

    public void setCourseTextBookFlag(String courseTextBookFlag) {
        this.courseTextBookFlag = courseTextBookFlag;
    }

    public String getMenuIndex() {
        return menuIndex;
    }

    public void setMenuIndex(String menuIndex) {
        this.menuIndex = menuIndex;
    }

    @Override
    public String toString() {
        return "InfoBean{" +
                "menuId='" + menuId + '\'' +
                ", userId='" + userId + '\'' +
                ", isVip='" + isVip + '\'' +
                ", courseTextBookFlag='" + courseTextBookFlag + '\'' +
                ", menuIndex='" + menuIndex + '\'' +
                ", unitTitle='" + unitTitle + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.menuId);
        dest.writeString(this.userId);
        dest.writeString(this.isVip);
        dest.writeString(this.courseTextBookFlag);
        dest.writeString(this.menuIndex);
        dest.writeString(this.unitTitle);
    }

    public InfoBean() {
    }

    protected InfoBean(Parcel in) {
        this.menuId = in.readString();
        this.userId = in.readString();
        this.isVip = in.readString();
        this.courseTextBookFlag = in.readString();
        this.menuIndex = in.readString();
        this.unitTitle = in.readString();
    }

    public static final Parcelable.Creator<InfoBean> CREATOR = new Parcelable.Creator<InfoBean>() {
        @Override
        public InfoBean createFromParcel(Parcel source) {
            return new InfoBean(source);
        }

        @Override
        public InfoBean[] newArray(int size) {
            return new InfoBean[size];
        }
    };
}
