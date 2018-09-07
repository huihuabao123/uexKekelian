package com.kekelian.callBack;

public interface OnFragmentCallBack {
    /**
     * 1.是Vip中点击了解会员
     * 2.是Vip中点击购买会员
     * 3.点击课本预习
     * 4.跳转没有题目的界面
     * 5.跳转做题界面
     * @param type
     */
    public void onResultClick(int type);
}
