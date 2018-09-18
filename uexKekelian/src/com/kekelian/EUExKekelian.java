package com.kekelian;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.kekelian.bean.InfoBean;
import com.kekelian.callBack.OnResumeCallBackListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.zywx.wbpalmstar.engine.DataHelper;
import org.zywx.wbpalmstar.engine.EBrowserView;
import org.zywx.wbpalmstar.engine.universalex.EUExBase;

import java.util.Date;


public class EUExKekelian extends EUExBase implements Parcelable {
    public static final String BASE="EUExKekelian";
    public static final String INFO="info";
    public static final String TAG = "EUExKekelian";
    public static final String CALLBACK_ON_FRAGMENT_VIP_CLICK = "uexKekelian.onFragmentVipClick";
    public static final String CALLBACK_ON_FRAGMENT_DO_EXERCISE = "uexKekelian.onFragmentDoExercise";
    public static final String CALLBACK_ON_KEKELIAN_CLOSE="uexKekelian.onKekelianClose";
    public static final String CALLBACK_ON_SCORE_REPORT_CLOSE="uexKekelian.onScoreReportClose";
    public static final String CALLBACK_ON_NO_TOPIC_CLOSE="uexKekelian.onNoTopicClose";
    private static Context context=null;
    private static EBrowserView view=null;
    private View mMapDecorView;
    public static LocalActivityManager mgr;
    public static final String ID_TAG="TAG_KEKELIAN_TAG";
    public static final String ID_SCORE="TAG_KEKELIAN_SCORE";
    public static final String ID_TOPIC="TAG_KEKELIAN_TOPIC";
    private OnResumeCallBackListener onResumeCallBackListener;


    public void setOnResumeCallBackListener(OnResumeCallBackListener onResumeCallBackListener) {
        this.onResumeCallBackListener = onResumeCallBackListener;
    }

    public EUExKekelian(Context context, EBrowserView view) {
        super(context, view);
        EUExKekelian.context=context;
        EUExKekelian.view=view;
    }



    public  void onActivityResume(String[] parm) {
//        if (mgr != null) {
////            mgr.dispatchResume();
////
////        }
        Log.i(TAG, "onDoExerciseBack: 刷新数据");
        onResumeCallBackListener.onResumeCallBack();
    }

    public static void onActivityPause(Context context) {
        if (mgr != null) {
            mgr.dispatchPause(((Activity) context).isFinishing());
        }
    }

    /**
     * 做题界面点击返回的刷新课课练的卡片页
     * @param parm
     */
    public void  onDoExerciseBack(String[] parm){
        Log.i(TAG, "onDoExerciseBack: 刷新数据");
        onResumeCallBackListener.onResumeCallBack();

    }
    /**
     * 打开课课练的入口
     * @param parm
     */
    public void startActivity(String[] parm) {
        if(parm==null || parm.length<1){
            return;
        }
        final InfoBean infoBean = DataHelper.gson.fromJson(parm[0], InfoBean.class);
        Log.i(TAG,"参数如下："+infoBean.toString());
       addActivity(HealthPush.class,ID_TAG,infoBean);

    }

    /**
     * 打开课课练成绩界面的入口
     * @param parm
     */
    public void opentScoreReportcActivity(String[] parm) {
        if(parm==null ){
            return;
        }
        addActivity(ScoreReport.class,ID_SCORE,parm[0]);
    }

    /**
     * 打开没题的界面
     * @param parm
     */
    public void openNoTopic(String[] parm){
        addActivity(NoTopic.class,ID_TOPIC);
    }

    /**
     * 打开课课练的入口
     * @param parm
     */
    public void launchActivity(String[] parm) {
        try {
            Intent intent = new Intent();
            intent.setClass(mContext, HealthPush.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "找不到此Activity!!", Toast.LENGTH_LONG)
                    .show();
        }
    }

    /**
     *
     * @param cls
     * @param id
     * @param infoBean javabean
     */
    public void  addActivity(final Class<?> cls, final String id, final InfoBean infoBean){
        ((Activity) mContext).runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (mMapDecorView != null) {
                    Log.i("corVideo", "already open");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(BASE,EUExKekelian.this);
                intent.putExtra(INFO,infoBean);
                intent.setClass(mContext,cls );
                if (mgr == null) {
                    mgr = new LocalActivityManager((Activity) mContext, true);
                    mgr.dispatchCreate(null);
                }
                Window window = mgr.startActivity(id, intent);
                mMapDecorView = window.getDecorView();
                RelativeLayout.LayoutParams lp;
                lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                lp.leftMargin = 0;
                lp.topMargin = 0;
                addView2CurrentWindow(mMapDecorView, lp);
            }
        });
    }

    /**
     *
     * @param cls
     * @param id
     * @param levelRecordId 做题记录的id
     */
    public void  addActivity(final Class<?> cls, final String id, final String levelRecordId ){
        ((Activity) mContext).runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (mMapDecorView != null) {
                    Log.i("corVideo", "already open");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(BASE,EUExKekelian.this);
                intent.putExtra(INFO,levelRecordId);
                intent.setClass(mContext,cls );
                if (mgr == null) {
                    mgr = new LocalActivityManager((Activity) mContext, true);
                    mgr.dispatchCreate(null);
                }
                Window window = mgr.startActivity(id, intent);
                mMapDecorView = window.getDecorView();
                RelativeLayout.LayoutParams lp;
                lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                lp.leftMargin = 0;
                lp.topMargin = 0;
                addView2CurrentWindow(mMapDecorView, lp);
            }
        });
    }


    /**
     * 1.启动新的Activity
     * 2.将新启动activity所在的View放入到WebView中
     * @param cls
     * @param id
     */
    public void  addActivity(final Class<?> cls, final String id){
        ((Activity) mContext).runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (mMapDecorView != null) {
                    Log.i("corVideo", "already open");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(BASE,EUExKekelian.this);
                intent.setClass(mContext,cls );
                if (mgr == null) {
                    mgr = new LocalActivityManager((Activity) mContext, true);
                    mgr.dispatchCreate(null);
                }
                Window window = mgr.startActivity(id, intent);
                mMapDecorView = window.getDecorView();
                RelativeLayout.LayoutParams lp;
                lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                lp.leftMargin = 0;
                lp.topMargin = 0;
                addView2CurrentWindow(mMapDecorView, lp);
            }
        });
    }


    /**
     * 当当前Activity所在的View添加到webView中
     * @param child
     * @param params
     */
    private void addView2CurrentWindow(View child, RelativeLayout.LayoutParams params) {
        int l = params.leftMargin;
        int t = params.topMargin;
        int w = params.width;
        int h = params.height;
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(w, h);
        lp.gravity = Gravity.NO_GRAVITY;
        lp.leftMargin = l;
        lp.topMargin = t;
        // adptLayoutParams(params, lp);
        // Log.i(TAG, "addView2CurrentWindow");
        mBrwView.addViewToCurrentWindow(child, lp);
    }



    /**
     * 将当前的activiti所在的View从WebView中移除
     * @param id
     */
    public void  removeActivity(final String id){
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mMapDecorView != null) {
                    removeViewFromCurrentWindow(mMapDecorView);
                    mMapDecorView = null;
                    mgr.destroyActivity(id, true);
                }
            }
        });
    }

    /**
     * 关闭课课练界面
     * @param params
     */
    public void closeKekelian(String[] params){
        removeActivity(ID_TAG);
    }

    /**
     * 关闭成绩报告的界面
     */
    public void closeScoreReport(String[] params){
        removeActivity(ID_SCORE);
    }


    public void closeNotopic(String[] params){
        removeActivity(ID_TOPIC);
    }

    public void closeKekelianCallBack() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("currentTime", new Date().getTime()); //返回的时间单位为秒
            callBackPluginJs(CALLBACK_ON_KEKELIAN_CLOSE, jsonObject.toString());
            Log.i(TAG,"课课练界面消失："+jsonObject.toString());
        } catch (JSONException e) {
            Log.i(TAG, String.valueOf(e.getMessage()));
        }
    }

    /**
     * 将当前的activiti所在的View从WebView中移除
     * @param context 启动该Activity的上下文
     * @param localActivityManager  activity管理器
     * @param view 启动的该Activity的父View
     * @param id 添加该Activity的View的标识
     * @param mUexBaseObj
     */
    public void  removeActivity(Context context, final LocalActivityManager localActivityManager , final View view, final String id, final EUExKekelian mUexBaseObj){
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (view != null) {
                    mUexBaseObj.removeViewFromCurrentWindow(view);
                    localActivityManager.destroyActivity(id, true);
                }
            }
        });
    }

    public void callBackPluginJs(String methodName, String jsonData){
        String js = SCRIPT_HEADER + "if(" + methodName + "){"
                + methodName + "('" + jsonData + "');}";
        Log.i(TAG, "callBackPluginJs: "+methodName);
        onCallback(js);
    }

    // clean something
    @Override
    protected boolean clean() {
        return false;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }


    public static final Parcelable.Creator<EUExKekelian> CREATOR = new Parcelable.Creator<EUExKekelian>() {
        @Override
        public EUExKekelian createFromParcel(Parcel source) {
            return new EUExKekelian(EUExKekelian.context,EUExKekelian.view);
        }

        @Override
        public EUExKekelian[] newArray(int size) {
            return new EUExKekelian[size];
        }
    };
}
