package com.kekelian.callBack;

public interface OnFragmentCallBack {
    /**
     * @param type
     * 1.是Vip中点击了解会员
     * 2.是Vip中点击购买会员
     * 3.点击课本预习
     */
    public void onResultClick(int type);

    /**
     * 跳转到做题界面
     * @param isLocked 是否有题
     * @param isErrors 是否显示错题
     * @param levelTypeName 关卡类型
     * @param RecordId 做题的id
     */
    public void onDoExerciseCallBack(boolean isLocked,boolean isErrors,String levelTypeName,String RecordId);
}
