package com.kekelian;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kekelian.unit.StatusBarUtil;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

public class ScoreReport extends Activity {


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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(EUExUtil.getResLayoutID("activity_score_report"));
        StatusBarUtil.setColor(ScoreReport.this, Color.parseColor("#8ed8bb"),0);
        mUexBaseObj =  getIntent().getParcelableExtra(EUExKekelian.BASE);
        initView();
    }

    private void initView() {
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
                mUexBaseObj.closeScoreReport();

            }
        });
    }
}
