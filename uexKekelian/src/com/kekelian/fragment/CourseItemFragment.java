package com.kekelian.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kekelian.bean.KKLLessionListBean;
import com.kekelian.bean.LessonContentBean;
import com.kekelian.callBack.OnClickCallBack;
import com.kekelian.callBack.OnFragmentCallBack;
import com.kekelian.dialog.ExperienceDialog;
import com.kekelian.dialog.LevelDialog;
import com.kekelian.dialog.VipDialog;
import com.kekelian.net.Api;
import com.kekelian.net.CallBack;
import com.kekelian.net.HttpClient;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import java.util.List;

/**
 * 单元测试的Fragment
 */
public class CourseItemFragment extends Fragment {

    private static String DATA="data";
    public static final String IS_vIP="is_vip";
    private boolean isVip=false;
    private static final String TAG="CourseItemFragment";
    private LinearLayout llNotPoint;
    private RelativeLayout rlContent;
    private Button btBookPreview;
    private TextView tvTitle;
    private TextView tvBlock;
    private ImageView ivButcher;
    private ImageView ivShow;
    private LevelDialog popLevelStatus;
    private VipDialog vipDialog;
    private ExperienceDialog experienceDialog;
    private Boolean stuffStats=false;
    protected boolean isCreated = false;

    private String balladeRecordId;
    private String stuffRecordId;
    private String isErrorStuff="false";
    private String isErrorBallade="false";
    private String courseTextBookFlag;
    private String menuIndex;
    private KKLLessionListBean.MessageBean.DataBean.LessonTabRecordBean dataBean;

    private OnFragmentCallBack onFragmentCallBack;
    private int totaBalladelItemCount;

    public void setOnFragmentCallBack(OnFragmentCallBack onFragmentCallBack) {
        this.onFragmentCallBack = onFragmentCallBack;
    }


    public static CourseItemFragment newInstance(KKLLessionListBean.MessageBean.DataBean.LessonTabRecordBean dataBean,
                                                 Boolean isVip, String courseTextBookFlag, String menuIndex) {
        CourseItemFragment fragment = new CourseItemFragment();
        Bundle args = new Bundle();
        args.putParcelable(DATA, dataBean);
        args.putBoolean(IS_vIP,isVip);
        args.putString("courseTextBookFlag", courseTextBookFlag);
        args.putString("menuIndex", menuIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        if (bundle != null) {
            dataBean = bundle.getParcelable(DATA);
            isVip=bundle.getBoolean(IS_vIP,false);
            courseTextBookFlag=bundle.getString("courseTextBookFlag", "0");
            menuIndex=bundle.getString("menuIndex");
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
        View view = inflater.inflate(EUExUtil.getResLayoutID("course_item_fragment"), container, false);
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
          loadData();
        }
    }


    private void loadData() {
        String params="?lessonRecordId="+dataBean.getLessonRecordId();
        HttpClient.get(this, Api.GET_KEKELIAN_LESSON_CONTENT+params, new CallBack<LessonContentBean>() {
            @Override
            public void onSuccess(LessonContentBean result) {
                if(result==null){
                    return;
                }
                 if(result.getMessage().isStatus()){
                    Log.i(TAG,"返回的json:"+result.toString());
                    showStatus(result);
                 }

            }

        });
    }

    /**
     * 省略字符串超长溢出的部分
     * @param text
     * @return
     */
    private String textOverflow(String text) {
        int OVERFLOW_LENGTH = 60;
        if(text != null && text.length() > OVERFLOW_LENGTH) {
            return new StringBuilder().append(text.substring(0, OVERFLOW_LENGTH)).append("...").toString();
        }

        return text;
    }

    private void showStatus(@NonNull LessonContentBean result) {
        //显示blockName
        if(!TextUtils.isEmpty(result.getMessage().getData().getBlockName())){
            tvBlock.setText(textOverflow(result.getMessage().getData().getBlockName()));
            // 大于60字符加省略号
        }
        //显示是否有知识点
        if(result.getMessage().getData().isHasKP()){
            rlContent.setVisibility(View.VISIBLE);
            llNotPoint.setVisibility(View.GONE);
        }else {
            rlContent.setVisibility(View.GONE);
            llNotPoint.setVisibility(View.VISIBLE);
        }
        //显示小试牛刀和大显身手的状态
        List<LessonContentBean.MessageBean.DataBean.QuizListBean> quizList=result.getMessage().getData().getQuizList();
        for (int i=0;i<quizList.size();i++){
            LessonContentBean.MessageBean.DataBean.QuizListBean quizListBean;
            quizListBean=quizList.get(i);
            if(quizListBean.getDisplayOrder()==1){
                totaBalladelItemCount=quizListBean.getTotalItemCount();
                int correctItemCount=quizListBean.getCorrectItemCount();
                int finishItemCount=quizListBean.getFinishItemCount();
                balladeRecordId=quizListBean.getLevelRecordId();
                //小试牛刀
                /**
                 * 1.finishItemCount=0表示没做
                 * 2.然后totalItemCount和
                 * correctItemCount 判断得几颗星
                 */
                if(finishItemCount==0){
                    //没有闯关
                    ivButcher.setImageResource(EUExUtil.getResDrawableID("kkl_checkpoint01_00"));
                }else {
                    if(totaBalladelItemCount>0){
                        if(correctItemCount==0){
                            //全错
                            ivButcher.setImageResource(EUExUtil.getResDrawableID("kkl_checkpoint01_00"));
                        }else if(correctItemCount==totaBalladelItemCount) {
                            //三颗星
                            stuffStats=true;
                            isErrorBallade="true";
                            ivButcher.setImageResource(EUExUtil.getResDrawableID("kkl_checkpoint01_04"));
                        } else if (correctItemCount==1 ){
                            //一颗星
                            stuffStats=true;
                            ivButcher.setImageResource(EUExUtil.getResDrawableID("kkl_checkpoint01_02"));
                        }else {
                            //两颗星
                            stuffStats=true;
                            ivButcher.setImageResource(EUExUtil.getResDrawableID("kkl_checkpoint01_03"));
                        }
                    }else {
                        //没有题目
                        ivButcher.setImageResource(EUExUtil.getResDrawableID("kkl_checkpoint01_00"));
                    }
                }

            }else if(quizListBean.getDisplayOrder()==2 ){
                int totalItemCount=quizListBean.getTotalItemCount();
                int correctItemCount=quizListBean.getCorrectItemCount();
                int finishItemCount=quizListBean.getFinishItemCount();

                //大显身手
                stuffRecordId=quizListBean.getLevelRecordId();
                if(!stuffStats){
                    //没有权限开启闯关
                    ivShow.setImageResource(EUExUtil.getResDrawableID("kkl_checkpoint02_00"));
                }else{
                    if(totaBalladelItemCount>0){

                        if(finishItemCount==0 && correctItemCount==0){
                            //没有闯关
                            ivShow.setImageResource(EUExUtil.getResDrawableID("kkl_checkpoint02_01"));

                        }else if( correctItemCount==0){
                            //全错
                            ivShow.setImageResource(EUExUtil.getResDrawableID("kkl_checkpoint02_01"));
                        }else if( correctItemCount==totalItemCount) {
                            //三颗星
                            isErrorStuff="true";
                            ivShow.setImageResource(EUExUtil.getResDrawableID("kkl_checkpoint02_05"));
                        } else if ( correctItemCount==1 ){
                            //一颗星
                            ivShow.setImageResource(EUExUtil.getResDrawableID("kkl_checkpoint02_03"));
                        } else {
                            //两颗星
                            ivShow.setImageResource(EUExUtil.getResDrawableID("kkl_checkpoint02_04"));
                        }
                    }else {
                        //没有闯关
                        ivShow.setImageResource(EUExUtil.getResDrawableID("kkl_checkpoint02_00"));
                    }
                }

            }
        }
    }

    /**
     * 初始化控件
     */
    private void initViews(View view ) {
        tvBlock=(TextView) view.findViewById(EUExUtil.getResIdID("tv_block"));
        ivButcher=(ImageView) view.findViewById(EUExUtil.getResIdID("iv_butcher"));
        ivShow=(ImageView) view.findViewById(EUExUtil.getResIdID("iv_show"));
        popLevelStatus=new LevelDialog(getActivity());
        vipDialog=new VipDialog(getContext());
        experienceDialog=new ExperienceDialog(getContext());
        //大显身手
        ivShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isVip){
                    if("1".equals(menuIndex)) {
                        experienceDialog.pop(new OnClickCallBack() {
                            @Override
                            public void clickCallBack(int type) {
                                if(type > 0) {
                                    onFragmentCallBack.onResultClick(type);
                                } else {
                                    if(!stuffStats){
                                        //没有解锁的提示
                                        popLevelStatus.pop();
                                        return;
                                    } else {
                                        //跳转做题界面
                                        onFragmentCallBack.onDoExerciseCallBack(isErrorStuff,"大显身手",stuffRecordId);
                                    }
                                }
                            }
                        });
                    } else {
                        vipDialog.pop(new OnClickCallBack() {
                            @Override
                            public void clickCallBack(int type) {
                                onFragmentCallBack.onResultClick(type);
                            }
                        });
                    }
                }else {
                    if(!stuffStats){
                        //没有解锁的提示
                        popLevelStatus.pop();
                        return;
                    }
                    //跳转做题界面
                    onFragmentCallBack.onDoExerciseCallBack(isErrorStuff,"大显身手",stuffRecordId);
                }
            }
        });
        tvTitle=(TextView) view.findViewById(EUExUtil.getResIdID("tv_title"));
        tvTitle.setText(dataBean.getLessonName());
        llNotPoint=(LinearLayout) view.findViewById(EUExUtil.getResIdID("ll_not_point"));
        rlContent=(RelativeLayout) view.findViewById(EUExUtil.getResIdID("rl_content"));
        btBookPreview=(Button) view.findViewById(EUExUtil.getResIdID("bt_book_preview"));
        btBookPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到课本预习界面
                onFragmentCallBack.onResultClick(3);
            }
        });
        //小试牛刀
        ivButcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isVip){
                    if("1".equals(menuIndex)) {
                        experienceDialog.pop(new OnClickCallBack() {
                            @Override
                            public void clickCallBack(int type) {
                                if(type > 0) {
                                    onFragmentCallBack.onResultClick(type);
                                } else {
                                    //跳转做题界面
                                    onFragmentCallBack.onDoExerciseCallBack(isErrorBallade,"小试牛刀",balladeRecordId);
                                }
                            }
                        });
                    } else {
                        vipDialog.pop(new OnClickCallBack() {
                            @Override
                            public void clickCallBack(int type) {
                                onFragmentCallBack.onResultClick(type);
                            }
                        });
                    }
                }else {
                      //跳转做题界面
                    onFragmentCallBack.onDoExerciseCallBack(isErrorBallade,"小试牛刀",balladeRecordId);

                }
            }
        });
    }
}
