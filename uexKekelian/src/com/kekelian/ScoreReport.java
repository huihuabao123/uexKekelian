package com.kekelian;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kekelian.bean.KekelianSuccessBean;
import com.kekelian.net.Api;
import com.kekelian.net.CallBack;
import com.kekelian.net.HttpClient;

import org.json.JSONException;
import org.json.JSONObject;
import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

public class ScoreReport extends Activity {


    private static final String TAG ="ScoreReport" ;
    private RelativeLayout rlScoreReport;
    private RelativeLayout rlBack;
    private ImageView ivImg;
    private TextView tvTitle;
    private ImageView ivStatus;
    private LinearLayout llButtons;
    private Button btContinue;
    private Button btWipe;
    private Button btAgain;
    private TextView tvInfo;
    private EUExKekelian mUexBaseObj;
    private String levelRecordId;
    private  KekelianSuccessBean.MessageBean.DataBean dataBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(EUExUtil.getResLayoutID("activity_score_report"));
        mUexBaseObj =  getIntent().getParcelableExtra(EUExKekelian.BASE);
        levelRecordId=getIntent().getStringExtra(EUExKekelian.INFO);
        initView();
        getkekelianSuccessContent();
    }

    private void getkekelianSuccessContent() {
        String params="?levelRecordId="+levelRecordId;
        HttpClient.get(this, Api.GET_KEKELIAN_LESSON_SUCCESS_CONTENT + params, new CallBack<KekelianSuccessBean>() {
            @Override
            public void onSuccess(KekelianSuccessBean result) {
                if(result==null){
                    return;
                }
                if(result.getMessage().isStatus()){
                    dataBean=result.getMessage().getData();
                    int totalItemCount=dataBean.getTotalItemCount();
                    int correctItemCount=dataBean.getCorrectItemCount();
                    tvTitle.setText(dataBean.getLessonName()+" "+dataBean.getLevelTypeName());
                    if(correctItemCount==0){
                        //全错
                        ivImg.setImageResource(EUExUtil.getResDrawableID("kkl_bg06"));
                        llButtons.setVisibility(View.GONE);
                        btAgain.setText("再试一次");
                        btAgain.setVisibility(View.VISIBLE);
                        ivStatus.setImageResource(EUExUtil.getResDrawableID("frog_01_02"));
                        tvInfo.setText("只要做对题目，就奖励智慧星");
                    }else if(correctItemCount>0 && totalItemCount>0 && correctItemCount==totalItemCount) {
                        //三颗星
                        ivImg.setImageResource(EUExUtil.getResDrawableID("kkl_bg03"));
                        llButtons.setVisibility(View.GONE);
                        btAgain.setText("继续闯关");
                        btAgain.setVisibility(View.VISIBLE);
                        ivStatus.setImageResource(EUExUtil.getResDrawableID("frog_01"));
                        tvInfo.setText("");
                    } else if (totalItemCount>0 && correctItemCount==1 ){
                        //一颗星
                        ivImg.setImageResource(EUExUtil.getResDrawableID("kkl_bg05"));
                        llButtons.setVisibility(View.VISIBLE);
                        btAgain.setVisibility(View.GONE);
                        ivStatus.setImageResource(EUExUtil.getResDrawableID("frog_01"));
                        tvInfo.setText("消灭错题，可以拿满星哦~");
                    }else {
                        //两颗星
                        ivImg.setImageResource(EUExUtil.getResDrawableID("kkl_bg04"));
                        llButtons.setVisibility(View.VISIBLE);
                        btAgain.setVisibility(View.GONE);
                        ivStatus.setImageResource(EUExUtil.getResDrawableID("frog_01"));
                        tvInfo.setText("消灭错题，可以拿满星哦~");
                    }
                }
                rlScoreReport.setVisibility(View.VISIBLE);
            }


        });
    }

    private void initView() {
        rlScoreReport=(RelativeLayout)findViewById(EUExUtil.getResIdID("rl_score_report"));
        rlBack=(RelativeLayout)findViewById(EUExUtil.getResIdID("rl_back"));
        tvTitle=(TextView) findViewById(EUExUtil.getResIdID("tv_title"));
        ivImg=(ImageView) findViewById(EUExUtil.getResIdID("iv_img"));
        ivStatus=(ImageView) findViewById(EUExUtil.getResIdID("iv_status"));
        llButtons=(LinearLayout) findViewById(EUExUtil.getResIdID("ll_button"));
        btContinue=(Button) findViewById(EUExUtil.getResIdID("bt_continue"));
        btWipe=(Button)findViewById(EUExUtil.getResIdID("bt_wipe"));
        btAgain=(Button)findViewById(EUExUtil.getResIdID("bt_again"));
        tvInfo=(TextView) findViewById(EUExUtil.getResIdID("tv_info"));
        //返回
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUexBaseObj.callBackPluginJs(EUExKekelian.CALLBACK_ON_SCORE_REPORT_CLOSE,"");

            }
        });
        //继续闯关
        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUexBaseObj.callBackPluginJs(EUExKekelian.CALLBACK_ON_SCORE_REPORT_CLOSE,"");
            }
        });
         //消灭错题
        btWipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("levelTypeName", dataBean.getLevelTypeName());
                    jsonObject.put("exerciseRecordId",dataBean.getLevelRecordId());
                    mUexBaseObj.callBackPluginJs(EUExKekelian.CALLBACK_ON_FRAGMENT_DO_EXERCISE,jsonObject.toString());
                    mUexBaseObj.closeScoreReport(null);
                } catch (JSONException e) {
                    Log.i(TAG, String.valueOf(e.getMessage()));
                }
            }
        });
        //再试一次或者继续闯关
        btAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("再试一次".equals(btAgain.getText().toString())){
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("levelTypeName", dataBean.getLevelTypeName());
                        jsonObject.put("exerciseRecordId",dataBean.getLevelRecordId());
                        mUexBaseObj.callBackPluginJs(EUExKekelian.CALLBACK_ON_FRAGMENT_DO_EXERCISE,jsonObject.toString());
                        mUexBaseObj.closeScoreReport(null);
                    } catch (JSONException e) {
                        Log.i(TAG, String.valueOf(e.getMessage()));
                    }
                }else{
                    //继续闯关
                    mUexBaseObj.callBackPluginJs(EUExKekelian.CALLBACK_ON_SCORE_REPORT_CLOSE,"");
                }
            }
        });
    }
}
