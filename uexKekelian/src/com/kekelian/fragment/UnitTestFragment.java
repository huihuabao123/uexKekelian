package com.kekelian.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kekelian.bean.UnitTestBean;
import com.kekelian.callBack.OnClickCallBack;
import com.kekelian.callBack.OnFragmentCallBack;
import com.kekelian.dialog.ExameDialog;
import com.kekelian.dialog.VipDialog;
import com.kekelian.net.Api;
import com.kekelian.net.CallBack;
import com.kekelian.net.HttpClient;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

/**
 * 单元测试的Fragment
 */
public class UnitTestFragment extends Fragment {
    private static final String TAG="UnitTestFragment";
    private static String DATA="data";
    private static final String MENUID="menuId";
    private static final String USERID="userId";
    private String userid;
    private String menuid;
    private ImageView ivStatus;
    private TextView tvBlock;
    private LinearLayout llScoreStar;
    private ImageView ivStar;
    private TextView tvScore;
    private ExameDialog popExameStatus;
    protected boolean isCreated = false;
    public static final String IS_vIP="is_vip";
    private boolean isVip=false;
    private VipDialog vipDialog;
    //是否解锁状态
    private Boolean isLocked;
    //是否重新生成题目
    private boolean isUnitLocked=true;
    private boolean isErrors=false;//是否显示错题
    private String unitTestRecordId;

    public static UnitTestFragment newInstance(String userid,String menuid,Boolean isVip) {
        UnitTestFragment fragment = new UnitTestFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_vIP, isVip);
        args.putString(USERID,userid);
        args.putString(MENUID,menuid);
        fragment.setArguments(args);
        return fragment;
    }

    private OnFragmentCallBack onFragmentCallBack;

    public void setOnFragmentCallBack(OnFragmentCallBack onFragmentCallBack) {
        this.onFragmentCallBack = onFragmentCallBack;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        if (bundle != null) {
            userid=bundle.getString(USERID);
            menuid=bundle.getString(MENUID);
            isVip=bundle.getBoolean(IS_vIP,false);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        isCreated=true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(EUExUtil.getResLayoutID("unit_test_fragment"), container, false);
        initViews(view);
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
              LoadData();
        }
    }


    private void LoadData() {
        String params="?menuId="+menuid+"&userId="+userid;
        HttpClient.get(this, Api.GET_UNIT_TEST_CONTENT + params, new CallBack<UnitTestBean>() {
            @Override
            public void onSuccess(UnitTestBean result) {
                if(result==null){
                    return;
                }
                if(result.getMessage().isStatus()){
                   int totalItemCount=result.getMessage().getData().getUnitTestTabRecord().getTotalItemCount();
                    int correctItemCount=result.getMessage().getData().getUnitTestTabRecord().getCorrectItemCount();
                    unitTestRecordId=result.getMessage().getData().getUnitTestTabRecord().getUnitTestRecordId();
                    if(totalItemCount>0){
                        isUnitLocked=false;
                    }else {
                        isUnitLocked=true;
                    }
                    if(correctItemCount>0 && totalItemCount>0 && correctItemCount==totalItemCount){
                        isErrors=false;
                    }else if(correctItemCount==0 &&correctItemCount==totalItemCount){
                        isErrors=false;
                    }else{
                        isErrors=true;
                    }
                    isLocked=result.getMessage().getData().getUnitTestTabRecord().isIsLocked();
                    if(isLocked){
                        tvBlock.setText("请完成所有课时的小试牛刀哦！");
                        tvBlock.setVisibility(View.VISIBLE);
                        ivStatus.setImageResource(EUExUtil.getResDrawableID("kkl_img05_lock"));
                        return;
                    }
                    if(result.getMessage().getData().getUnitTestTabRecord().getFinishItemCount()==0){
                        tvBlock.setText("请完成所有课时的小试牛刀哦！");
                        tvBlock.setVisibility(View.VISIBLE);
                         ivStatus.setImageResource(EUExUtil.getResDrawableID("kkl_img05_unlock"));
                         return;
                     }
                     if(result.getMessage().getData().getUnitTestTabRecord().getFinishItemCount()>0 ){
                        tvBlock.setVisibility(View.GONE);
                        ivStatus.setVisibility(View.GONE);
                        llScoreStar.setVisibility(View.VISIBLE);
                         tvScore.setText(result.getMessage().getData().getUnitTestTabRecord().getScore()+"分");
                             if(correctItemCount==0){
                                 //全错
                                 ivStar.setVisibility(View.GONE);
                             } else if (totalItemCount>0 && correctItemCount==1 ){
                                 //一颗星
                                 ivStar.setImageResource(EUExUtil.getResDrawableID("star03"));
                             }else if(correctItemCount>0 && totalItemCount>0 && correctItemCount==totalItemCount) {
                                 //三颗星
                                 ivStar.setImageResource(EUExUtil.getResDrawableID("star01"));
                             }else {
                                 //两颗星
                                 ivStar.setImageResource(EUExUtil.getResDrawableID("star02"));
                             }

                     }

                }
            }
        });
    }


    private void initViews(View view) {
        vipDialog=new VipDialog(getContext());
        popExameStatus=new ExameDialog(getActivity());
        ivStatus=(ImageView) view.findViewById(EUExUtil.getResIdID("iv_status"));
        tvBlock=(TextView) view.findViewById(EUExUtil.getResIdID("tv_block"));
        llScoreStar=(LinearLayout) view.findViewById(EUExUtil.getResIdID("ll_score_star"));
        tvScore=(TextView) view.findViewById(EUExUtil.getResIdID("tv_score"));
        ivStar=(ImageView) view.findViewById(EUExUtil.getResIdID("iv_star"));
        ivStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isVip){
                    vipDialog.pop(new OnClickCallBack() {
                        @Override
                        public void clickCallBack(int type) {
                            onFragmentCallBack.onResultClick(type);
                        }

                    });
                }else {
                    if(isLocked){
                        popExameStatus.pop();
                    }else {
                        //跳转做题界面
                        onFragmentCallBack.onDoExerciseCallBack(isUnitLocked,isErrors,"单元测验",unitTestRecordId);
                    }

                }
            }
        });
        //跳转逻辑
        llScoreStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转做题界面
                onFragmentCallBack.onDoExerciseCallBack(isUnitLocked,isErrors,"单元测验",unitTestRecordId);
            }
        });

    }
}
