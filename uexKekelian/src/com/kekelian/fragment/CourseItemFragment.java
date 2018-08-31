package com.kekelian.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kekelian.EUExKekelian;
import com.kekelian.NoTopic;
import com.kekelian.ScoreReport;
import com.kekelian.bean.GankResp;
import com.kekelian.callBack.OnClickCallBack;
import com.kekelian.dialog.LevelDialog;
import com.kekelian.dialog.VipDialog;
import com.kekelian.net.CallBack;
import com.kekelian.net.HttpClient;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

/**
 * 单元测试的Fragment
 */
public class CourseItemFragment extends Fragment {

    private static String INDEX="INDEX";
    protected boolean isCreated = false;
    private static final String TAG="HealthPush";
    private LinearLayout llCourseItemFragment;
    private LinearLayout llNotPoint;
    private RelativeLayout rlContent;
    private Button btBookPreview;
    private TextView tvTitle;
    private TextView tvBlock;
    private ImageView ivButcher;
    private ImageView ivShow;
    private LevelDialog popLevelStatus;
    private VipDialog vipDialog;
    private int index=-1;
    private static final String GANK_URL = "http://gank.io/api/data/福利/5000/1";

    private onFragmentCallBack onFragmentCallBack;

    public void setOnFragmentCallBack(CourseItemFragment.onFragmentCallBack onFragmentCallBack) {
        this.onFragmentCallBack = onFragmentCallBack;
    }

    public interface onFragmentCallBack{
        /**
         * 1.是Vip中点击了解会员
         * 2.是Vip中点击购买会员
         * 3.点击课本预习
         * 4.跳转没有题目的界面
         * @param type
         */
        public void onResultClick(int type);
    }


    public static CourseItemFragment newInstance(int index) {
        CourseItemFragment fragment = new CourseItemFragment();
        Bundle args = new Bundle();
        args.putInt(INDEX, index);
//        args.putString(ENROLL_PLAY_ID, enrollPlanId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        if (bundle != null) {
            index = bundle.getInt(INDEX, -1);
        }
        Log.i(TAG,"CourseItemFragment"+index+"---->onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        isCreated=true;
        Log.i(TAG,"CourseItemFragment"+index+"---->onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(EUExUtil.getResLayoutID("course_item_fragment"), container, false);
        initViews(view);
        Log.i(TAG,"CourseItemFragment"+index+"---->onCreateView()");
        return view;
    }

    /**
     * 懒加载
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(!isCreated){
            return;
        }
        if(isVisibleToUser){
            getData();
        }
    }

    private void getData() {
        HttpClient.get(this, GANK_URL, new CallBack<GankResp>() {
            @Override
            public void onSuccess(GankResp result) {
                if(result==null){
                    return;
                }
                Log.i(TAG,"返回的json："+result.toString());
            }


        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG,"CourseItemFragment"+index+"---->onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG,"CourseItemFragment"+index+"---->onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"CourseItemFragment"+index+"---->onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"CourseItemFragment"+index+"---->onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"CourseItemFragment"+index+"---->onStop()");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG,"CourseItemFragment"+index+"---->onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG,"CourseItemFragment"+index+"---->onDetach()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"CourseItemFragment"+index+"---->onDestroy()");
    }

    /**
     * 初始化控件
     */
    private void initViews(View view ) {
        llCourseItemFragment=(LinearLayout) view.findViewById(EUExUtil.getResIdID("ll_courese_item_fragment"));
        tvBlock=(TextView) view.findViewById(EUExUtil.getResIdID("tv_block"));
        ivButcher=(ImageView) view.findViewById(EUExUtil.getResIdID("iv_butcher"));
        ivShow=(ImageView) view.findViewById(EUExUtil.getResIdID("iv_show"));
        popLevelStatus=new LevelDialog(getActivity());
        vipDialog=new VipDialog(getContext());
        ivShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popLevelStatus.pop();
            }
        });
        tvTitle=(TextView) view.findViewById(EUExUtil.getResIdID("tv_title"));
        tvTitle.setText("第"+index+"课时");
        llNotPoint=(LinearLayout) view.findViewById(EUExUtil.getResIdID("ll_not_point"));
        rlContent=(RelativeLayout) view.findViewById(EUExUtil.getResIdID("rl_content"));
        if(index==1){
            tvBlock.setText("adasdasda,sdwcaniae,wmasdma,mdaewa.sd a");
            llNotPoint.setVisibility(View.VISIBLE);
            rlContent.setVisibility(View.GONE);
        }else {
            tvBlock.setText("中山大道撒ad搭搭撒撒amdaewoa.sda");
            rlContent.setVisibility(View.VISIBLE);
            llNotPoint.setVisibility(View.GONE);
        }
        btBookPreview=(Button) view.findViewById(EUExUtil.getResIdID("bt_book_preview"));
        btBookPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFragmentCallBack.onResultClick(3);
            }
        });
        ivButcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(index==1){
                 startActivity(new Intent(getActivity(), ScoreReport.class));
             } else if(index==2){
                 onFragmentCallBack.onResultClick(3);
             }else if(index==4){
                 onFragmentCallBack.onResultClick(4);
             } else {
                 vipDialog.pop(new OnClickCallBack() {
                     @Override
                     public void clickCallBack(int type) {
                         onFragmentCallBack.onResultClick(type);
                     }

                 });
             }

            }
        });
    }
}
