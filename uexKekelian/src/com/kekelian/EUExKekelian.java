package com.kekelian;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.zywx.wbpalmstar.engine.EBrowserView;
import org.zywx.wbpalmstar.engine.universalex.EUExBase;


public class EUExKekelian extends EUExBase implements Parcelable {
    public static final String BASE="EUExKekelian";
    public static final String TAG = "EUExKekelian";
    public static final String CALLBACK_ON_FRAGMENT_VIP_CLICK = "uexKekelian.onFragmentVipClick";
    public static final String CALLBACK_ON_FRAGMENT_BUY_VIP_CLICK = "uexKekelian.onFragmentBuyVipClick";
    private static Context context=null;
    private static EBrowserView view=null;
    private View mMapDecorView;
    private static LocalActivityManager mgr;
    public static final String ID_TAG="TAG_KEKELIAN_TAG";
    public static final String ID_SCORE="TAG_KEKELIAN_SCORE";
    public static final String ID_TOPIC="TAG_KEKELIAN_TOPIC";



    public EUExKekelian(Context context, EBrowserView view) {
        super(context, view);
        EUExKekelian.context=context;
        EUExKekelian.view=view;
    }


    public static void onActivityResume(Context context) {
        if (mgr != null) {
            mgr.dispatchResume();
        }
    }

    public static void onActivityPause(Context context) {
        if (mgr != null) {
            mgr.dispatchPause(((Activity) context).isFinishing());
        }
    }

    /**
     * 打开课课练的入口
     * @param parm
     */
    public void startActivity(String[] parm) {
       addActivity(HealthPush.class,ID_TAG);

    }

    /**
     * 打开课课练的入口
     * @param parm
     */
    public void opentActivity(String[] parm) {
        try {
            Intent intent = new Intent();
            intent.putExtra(BASE,EUExKekelian.this);
            intent.setClass(mContext, HealthPush.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "找不到此Activity!!", Toast.LENGTH_LONG)
                    .show();
        }
    }

    /**
     * 打开课课练的入口
     * @param parm
     */
    public void launchActivity(String[] parm) {
        try {
            Intent intent = new Intent();
            intent.setClass(mContext, PracticeCardPage.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "找不到此Activity!!", Toast.LENGTH_LONG)
                    .show();
        }
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
     * 关闭启动tab页面
     */
    public void closeTabActivity(@NonNull String id) {
        removeActivity(id);
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
        return true;
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
