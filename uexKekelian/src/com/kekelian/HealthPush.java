package com.kekelian;


import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kekelian.adapter.IntegralFragmentPagerAdapter;
import com.kekelian.bean.InfoBean;
import com.kekelian.bean.KKLLessionListBean;
import com.kekelian.bean.UnitTestTabRecordBean;
import com.kekelian.callBack.OnFragmentCallBack;
import com.kekelian.callBack.OnResumeCallBackListener;
import com.kekelian.fragment.CourseItemFragment;
import com.kekelian.net.Api;
import com.kekelian.net.CallBack;
import com.kekelian.net.HttpClient;
import com.kekelian.transformer.CardTransformer;
import com.kekelian.unit.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import java.util.ArrayList;


public class HealthPush extends FragmentActivity implements OnFragmentCallBack,OnResumeCallBackListener {
    private static final String TAG="HealthPush";
    private EUExKekelian mUexBaseObj;
    private InfoBean infoBean;
    private boolean isVip=false;
    // View
    private HorizontalScrollView mColumnHorizontalScrollView;
    private LinearLayout mColumnContent;
    //指示器
    private TextView indicateTV;
    // 指示器的布局参数，可设置控件在布局中的相关属性
    private LinearLayout.LayoutParams indicateParams;
    private ViewPager mViewPager;
    //显示内容的布局
    private LinearLayout llContent;
    //显示加载的布局
    private RelativeLayout rlLoad;
    //网络加载的布局
    private LinearLayout llNetWork;
    //返回
    private RelativeLayout rlBack;
    private TextView tvTitle;
    private ImageView ivLoad;

    /** fragment适配器 */
    private IntegralFragmentPagerAdapter mAdapetr;
    /** 当前选中的类别 */
    private int columnSelectIndex = 1;
    /** 屏幕宽度 */
    private int mScreenWidth = 0;
    /** Item宽度 */
    private int mItemWidth = 0;

    //存放tabItem的文本 模拟数据源
    private ArrayList<KKLLessionListBean.MessageBean.DataBean.LessonTabRecordBean> list = new ArrayList<>();
    private UnitTestTabRecordBean recordBean;
    /** Fragment列表 */
    private ArrayList<Fragment> fragments;

//    public  static View mMapDecorView;
//    public static  Context context;
    /**
     * 指示器动画联动
     */
    private Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg) {
            initStartAnimation(msg.arg1, (Float)msg.obj);		//移动指示器
        }
    };
//    public static LocalActivityManager mgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUexBaseObj =  getIntent().getParcelableExtra(EUExKekelian.BASE);
        mUexBaseObj.setOnResumeCallBackListener(this);
        infoBean=getIntent().getParcelableExtra(EUExKekelian.INFO);
        if("Y".equals(infoBean.getIsVip())){
            isVip=true;
        }
//        context=this;
        Log.i(TAG,"HealthPush---->onCreate()");
        setContentView(EUExUtil.getResLayoutID("hpush_activity"));
//        StatusBarUtil.setColor(HealthPush.this, Color.parseColor("#94dace"),0);
        initView();
        getKekelianList();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"HealthPush---->onResume()");
//        fragments.get(mViewPager.getCurrentItem()).setUserVisibleHint(true);
    }



    private void getKekelianList() {
        showPreload();
        String params="?menuId="+infoBean.getMenuId()+"&userId="+infoBean.getUserId();
        HttpClient.get(this, Api.GET_KEKELIAN_LIST + params, new CallBack<KKLLessionListBean>() {
            @Override
            public void onSuccess(KKLLessionListBean result) {
                if(result==null){
                    return;
                }
                if(result.getMessage().isStatus()){
                    ivLoad.setVisibility(View.GONE);
                    rlLoad.setVisibility(View.VISIBLE);
                  list.clear();
                  list.addAll(result.getMessage().getData().getLessonTabRecord());
                  //计算第一个小试牛刀未完成的下标
                  for (int i=0;i<list.size();i++){
                      if(list.get(i).getFinishProgress()==0){
                          columnSelectIndex=i;
                          break;
                      }
                  }
                  Log.i(TAG,"tab的数量："+list.size());
                  recordBean=result.getMessage().getData().getUnitTestTabRecord();
                  setChangelView();
                  showContent();
                }
            }



        });
    }


    /** 初始化layout控件 */
    private void initView() {
         rlBack=(RelativeLayout)findViewById(EUExUtil.getResIdID("rl_back"));
         rlBack.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                mUexBaseObj.closeKekelian(null);
             }
         });
        tvTitle=(TextView) findViewById(EUExUtil.getResIdID("title_name_text"));
        tvTitle.setText(infoBean.getUnitTitle());
        llContent=(LinearLayout)findViewById(EUExUtil.getResIdID("ll_content"));
        rlLoad=(RelativeLayout)findViewById(EUExUtil.getResIdID("rl_load"));
        //初始化导航条
        mColumnHorizontalScrollView = (HorizontalScrollView) findViewById(EUExUtil.getResIdID("mColumnHorizontalScrollView_IntegralShop"));
        mColumnContent = (LinearLayout) findViewById(EUExUtil.getResIdID("mRadioGroup_content_IntegralShop"));
        mViewPager = (ViewPager) findViewById(EUExUtil.getResIdID("mViewPager_IntegralShop"));
        //初始化指示器
        indicateTV = (TextView) findViewById(EUExUtil.getResIdID("indicateId"));
        ivLoad=(ImageView) findViewById(EUExUtil.getResIdID("load_view"));
        llNetWork=(LinearLayout) findViewById(EUExUtil.getResIdID("ll_net_work"));
        indicateParams = (LinearLayout.LayoutParams) indicateTV.getLayoutParams();
        if(NetworkUtils.getNetworkStatus(this)==-1){
            showErrorNet();
        }


    }

    /**
     * 显示网络异常界面
     */
    private void showErrorNet() {
        llContent.setVisibility(View.GONE);
        rlLoad.setVisibility(View.GONE);
        llNetWork.setVisibility(View.VISIBLE);
    }

    /**
     * 展示加载中界面
     */
    private void showPreload() {
        llContent.setVisibility(View.GONE);
        rlLoad.setVisibility(View.VISIBLE);
        llNetWork.setVisibility(View.GONE);
        Glide.with(HealthPush.this).load(EUExUtil.getResDrawableID("loading")).asGif().into(ivLoad);
    }

    /**
     * 展示内容
     */
    private void showContent() {
        llContent.setVisibility(View.VISIBLE);
        rlLoad.setVisibility(View.GONE);
        llNetWork.setVisibility(View.GONE);

    }


    /**
     * 初始化动态加载
     */
    private void setChangelView() {
        if (list == null || list.size()==0) {
            //第一次进入APP,从网络获取数据
            return;
        }
        initTabColumn();
        initFragment();
        //初始化指示器位置
        initIndicator();

    }



    /**
     * 初始化Column栏目项
     * */
    private void initTabColumn() {
        Configuration config = getResources().getConfiguration();
        int smallestScreenWidth = config.smallestScreenWidthDp;
        mScreenWidth = getWindowsWidth(this);
        // 一个Item宽度为屏幕的1/list.size()
//        int count =list.size()+1;
        int count =list.size();
        if(count<4 && count>0){
            mItemWidth = mScreenWidth / count;
        }else {
            mItemWidth = mScreenWidth / 4;
        }
        mColumnContent.removeAllViews();
        for (int i = 0; i < count; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth, LayoutParams.WRAP_CONTENT);
            TextView localTextView = new TextView(this);
            localTextView.setGravity(Gravity.CENTER);
            localTextView.setPadding(5,0,5,0);
//            if(i==count-1){
//                localTextView.setText("单元测验");
//            }else {
//                localTextView.setText(list.get(i).getLessonName());
//            }
            localTextView.setText(list.get(i).getLessonName());

            if(smallestScreenWidth>=600){
                localTextView.setTextSize(25);
            }else {
                localTextView.setTextSize(20);
            }

            localTextView.setTextColor(Color.parseColor("#c4e4d6"));
            if (columnSelectIndex == i) {//设置第一个被选中,颜色变化
                localTextView.setTextColor(Color.parseColor("#73a891"));
            }
            mColumnContent.addView(localTextView, i, params);
        }
        //重新设置宽度
        LinearLayout.LayoutParams columuParams=(android.widget.LinearLayout.LayoutParams) mColumnContent.getLayoutParams();
        columuParams.width=mItemWidth*count;
        mColumnContent.setLayoutParams(columuParams);
        //设置标题类型选择点击事件
        setModelClick() ;
    }


    /**
     * 初始化Fragment
     * */
    private void initFragment() {
        fragments = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CourseItemFragment courseItemFragment= CourseItemFragment.newInstance(list.get(i),isVip);
            courseItemFragment.setOnFragmentCallBack(this);
            fragments.add(courseItemFragment);

        }
             //添加单元测验
//        UnitTestFragment unitTestFragment=UnitTestFragment.newInstance(infoBean.getUserId(),infoBean.getMenuId(),isVip);
//        unitTestFragment.setOnFragmentCallBack(this);
//        fragments.add(unitTestFragment);

        mViewPager.removeAllViews();

        mViewPager.setPageMargin(dip2px(HealthPush.this,34));
        mViewPager.setPageTransformer(false, new CardTransformer());
        if(mAdapetr!=null)
            mAdapetr.clearFragment();
        mAdapetr = new IntegralFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapetr);
//        mViewPager.setOffscreenPageLimit(list.size()+1);
        mViewPager.setOffscreenPageLimit(list.size());
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        mViewPager.setCurrentItem(columnSelectIndex);
    }

    private void initIndicator() {
        //重置选择器位置
        selectMode(columnSelectIndex);
        //初始化指示器并设置指示器位置
//        indicateParams.width=mItemWidth;//设置指示器宽度
        indicateParams.width=mItemWidth/3;//设置指示器宽度
        indicateParams.setMargins(0,0,0,0);
        indicateTV.setLayoutParams(indicateParams);//设置指示器宽度
    }

    /**
     * Fragment中会员的点击事件
     * @param type
     */
    @Override
    public void onResultClick(int type) {

        mUexBaseObj.callBackPluginJs(EUExKekelian.CALLBACK_ON_FRAGMENT_VIP_CLICK,""+type);

    }


    /**
     * 跳转做题的界面
     * @param levelTypeName 关卡的名称
     * @param recordId
     */
    @Override
    public void onDoExerciseCallBack( String levelTypeName, String recordId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("levelTypeName", levelTypeName);
            jsonObject.put("exerciseRecordId",recordId);
            mUexBaseObj.callBackPluginJs(EUExKekelian.CALLBACK_ON_FRAGMENT_DO_EXERCISE,jsonObject.toString());
        } catch (JSONException e) {
            Log.i(TAG, String.valueOf(e.getMessage()));
        }
    }

    @Override
    public void onResumeCallBack() {
        fragments.get(mViewPager.getCurrentItem()).setUserVisibleHint(true);
    }


    /**
     * ViewPager切换监听方法
     */
    class MyOnPageChangeListener implements OnPageChangeListener{

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int position, float offset, int offsetPixes) {
            Message msg=Message.obtain();
            msg.arg1=position;
            msg.obj=offset;
            handler.sendMessage(msg);
        }

        @Override
        public void onPageSelected(int position) {
            selectMode(position);
        }
    }


    private void initStartAnimation(int position, float offset) {
        if(fragments.isEmpty()){
            indicateTV.setVisibility(View.GONE);
        }else{
            indicateTV.setVisibility(View.VISIBLE);
        }
        if (offset == 0){ // 停止滚动
//            indicateParams.setMargins(indicateParams.width * position,0, 0, 0);
            indicateParams.setMargins(indicateParams.width+mItemWidth * position,0, 0, 0);
        }
        else{
//            indicateParams.setMargins((int) ( indicateParams.width * (position + offset)),0, 0, 0);
            indicateParams.setMargins((int) ( indicateParams.width+mItemWidth * (position + offset)),0, 0, 0);
        }
        indicateTV.setLayoutParams(indicateParams);
    }

    /**
     * 设置HorizontalScrollView将被选中项移动到中间位置,并且设置被选中项字体变化
     * @param position
     */
    private void selectMode(int position) {
        if(fragments.isEmpty())return;
        TextView selTextView = null;
        // 获取所有model,清除之前选择的状态
        for (int i = 0; i < mColumnContent.getChildCount(); i++) {
            TextView textView = (TextView) mColumnContent.getChildAt(i);
            textView.setTextColor(Color.parseColor("#000000"));
            if (i == position) {
                selTextView = textView;
                //设置被选中项字体颜色变化为被选中颜色
                selTextView.setTextColor(Color.parseColor("#f77275"));
            }
        }
        int left = selTextView.getLeft();
        int right = selTextView.getRight();
        int sw = getResources().getDisplayMetrics().widthPixels;
        // 将控件滚动到屏幕的中间位置
        mColumnHorizontalScrollView.scrollTo(left - sw / 2 + (right - left) / 2, 0);
        //width是每次滑动的距离
//        int width = selTextView.getWidth()+10;
//        //让scrollView滑动   滑动距离是textview之间的间距
//        mColumnHorizontalScrollView.scrollTo(width*position,0);

    }




    /**
     * 设置每一个模块(标题)的点击事件
     */
    private void setModelClick() {
        // 获取所有model,清除之前选择的状态
        for (int i = 0; i < mColumnContent.getChildCount(); i++) {
            TextView textView = (TextView) mColumnContent.getChildAt(i);
            textView.setTag(i);
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = (Integer) v.getTag();
                    mViewPager.setCurrentItem(index);
                }
            });
        }
    }


    /**获取屏幕宽度
     * @param activity
     * @return
     */
    public final static int getWindowsWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**获取屏幕高度
     * @param activity
     * @return
     */
    public final static int getWindowsHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


//    public void  addActivity(final Class<?> cls, final String id){
//        ((Activity)HealthPush.this ).runOnUiThread(new Runnable() {
//
//            @Override
//            public void run() {
//                if (mMapDecorView != null) {
//                    Log.i("corVideo", "already open");
//                    return;
//                }
//                Intent intent = new Intent();
//                intent.putExtra(EUExKekelian.BASE,mUexBaseObj);
//                intent.setClass(HealthPush.this,cls );
//                if (mgr == null) {
//                    mgr = new LocalActivityManager((Activity) HealthPush.this, true);
//                    mgr.dispatchCreate(null);
//                }
//                Window window = mgr.startActivity(id, intent);
//                mMapDecorView = window.getDecorView();
//                RelativeLayout.LayoutParams lp;
//                lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
//                        RelativeLayout.LayoutParams.MATCH_PARENT);
//                lp.leftMargin = 0;
//                lp.topMargin = 0;
//                addView2CurrentWindow(mMapDecorView, lp);
//            }
//        });
//    }
//    private void addView2CurrentWindow(View child, RelativeLayout.LayoutParams params) {
//        int l = params.leftMargin;
//        int t = params.topMargin;
//        int w = params.width;
//        int h = params.height;
//        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(w, h);
//        lp.gravity = Gravity.NO_GRAVITY;
//        lp.leftMargin = l;
//        lp.topMargin = t;
//        // adptLayoutParams(params, lp);
//        // Log.i(TAG, "addView2CurrentWindow");
//        mUexBaseObj.mBrwView.addViewToCurrentWindow(child, lp);
//    }
//
//
//    /**
//     * 将当前的activiti所在的View从WebView中移除
//     */
//    public void  removeActivity(){
//        ((Activity) HealthPush.this).runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if (mMapDecorView != null) {
//                    mUexBaseObj.removeViewFromCurrentWindow(mMapDecorView);
//                    mUexBaseObj=null;
//                    mgr.destroyActivity(EUExKekelian.ID_TOPIC, true);
//                }
//            }
//        });
//    }




    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"HealthPush---->onDestroy()");
    }
}


