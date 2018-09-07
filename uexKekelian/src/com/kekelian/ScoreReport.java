package com.kekelian;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;

import com.kekelian.unit.StatusBarUtil;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

public class ScoreReport extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(EUExUtil.getResLayoutID("activity_score_report"));
        StatusBarUtil.setColor(ScoreReport.this, Color.parseColor("#8ed8bb"),0);
    }
}
