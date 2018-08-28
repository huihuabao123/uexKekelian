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

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

/**
 * 闯关状态提示框
 */
public class ExameDialog {
    private Dialog dialog;

    private Context context;

    public ExameDialog(Context context) {
        this.context = context;
    }


    public void pop(){
        if (dialog != null){
            dialog.dismiss();
            dialog = null;
            return;
        }
        dialog = new AlertDialog.Builder(context).create();
        final View pView = LayoutInflater.from(context)
                .inflate(EUExUtil.getResLayoutID("pop_exame_status"),null);
        Button btConfirm= (Button) pView.findViewById(EUExUtil.getResIdID("bt_comfirm"));

        //确认
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new BitmapDrawable());
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
//        params.x = 10;
        params.y = 120;
//        params.width = 220;
//        params.height = 200;
        window.setAttributes(params);
        dialog.getWindow().setGravity(Gravity.CENTER);//可以设置显示的位置
        dialog.setContentView(pView);
    }

}


