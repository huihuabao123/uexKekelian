package com.kekelian.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kekelian.callBack.OnClickCallBack;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

/**
 * 免费试用提示框
 */
public class ExperienceDialog {

    private Dialog dialog;

    private Context context;

    public ExperienceDialog( Context context) {
        this.context = context;
    }


    public void pop( final OnClickCallBack clickCallBack){
        if (dialog != null){
            dialog.dismiss();
            dialog = null;
        }
        dialog = new AlertDialog.Builder(context).create();
        final View pView = LayoutInflater.from(context)
                .inflate(EUExUtil.getResLayoutID("pop_experience"),null);
        TextView btnClose= (TextView) pView.findViewById(EUExUtil.getResIdID("bt_continue"));
        TextView buBuy=(TextView) pView.findViewById(EUExUtil.getResIdID("bt_buy"));
        //继续使用
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCallBack.clickCallBack(0);
                dialog.dismiss();
            }
        });
        //购买会员
        buBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCallBack.clickCallBack(2);
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new BitmapDrawable());
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
//        params.x = 10;
//        params.y = 120;
//        params.width = 220;
//        params.height = 200;
//        window.setAttributes(params);
        dialog.getWindow().setGravity(Gravity.CENTER);//可以设置显示的位置
        dialog.setContentView(pView);


    }

}


