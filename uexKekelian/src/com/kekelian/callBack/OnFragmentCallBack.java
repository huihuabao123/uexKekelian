package com.kekelian.callBack;

public interface OnFragmentCallBack {
    /**
     * @param type
     * 1.是Vip中点击了解会员
     * 2.是Vip中点击购买会员
     * 3.点击课本预习
     */
     void onResultClick(int type);

    /**
     * 跳转到做题界面
     * @param levelTypeName 关卡类型
     * @param RecordId 做题的id
     */
     void onDoExerciseCallBack(String levelTypeName,String RecordId);
}
