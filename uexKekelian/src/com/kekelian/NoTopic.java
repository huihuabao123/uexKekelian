package com.kekelian;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

public class NoTopic extends Activity {
    //返回
    private RelativeLayout rlBack;
    private Button btStudyCourse;
    private EUExKekelian mUexBaseObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(EUExUtil.getResLayoutID("activity_no_topic"));
        mUexBaseObj =  getIntent().getParcelableExtra(EUExKekelian.BASE);
        initView();
    }

    private void initView() {
        rlBack=(RelativeLayout)findViewById(EUExUtil.getResIdID("rl_back"));
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUexBaseObj.callBackPluginJs(EUExKekelian.CALLBACK_ON_NO_TOPIC_CLOSE,"");

            }
        });
        btStudyCourse=(Button)findViewById(EUExUtil.getResIdID("bt_study_course"));
        btStudyCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUexBaseObj.callBackPluginJs(EUExKekelian.CALLBACK_ON_FRAGMENT_VIP_CLICK,""+3);
            }
        });
    }


}
