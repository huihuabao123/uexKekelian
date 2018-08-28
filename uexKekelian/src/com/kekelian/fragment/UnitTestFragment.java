package com.kekelian.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kekelian.dialog.ExameDialog;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

/**
 * 单元测试的Fragment
 */
public class UnitTestFragment extends Fragment {
    private static final String TAG="HealthPush";
    private LinearLayout llTestFragment;
    private ImageView ivStatus;
    private ImageView ivClock;
    private ExameDialog popExameStatus;
    protected boolean isCreated = false;


    public static UnitTestFragment newInstance() {
        UnitTestFragment fragment = new UnitTestFragment();
        Bundle args = new Bundle();
//        args.putInt(YEAR, year);
//        args.putString(ENROLL_PLAY_ID, enrollPlanId);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG,"UnitTestFragment---->onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        isCreated=true;
        Log.i(TAG,"UnitTestFragment---->onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(EUExUtil.getResLayoutID("unit_test_fragment"), container, false);
        initViews(view);
        Log.i(TAG,"UnitTestFragment---->onCreateView()");
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

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG,"UnitTestFragment---->onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG,"UnitTestFragment---->onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"UnitTestFragment---->onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"UnitTestFragment---->onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"UnitTestFragment---->onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG,"UnitTestFragment---->onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG,"UnitTestFragment---->onDetach()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"UnitTestFragment---->onDestroy()");
    }



    private void initViews(View view) {
        popExameStatus=new ExameDialog(getActivity());
        llTestFragment=(LinearLayout) view.findViewById(EUExUtil.getResIdID("ll_unit_test_fragment"));
        ivStatus=(ImageView) view.findViewById(EUExUtil.getResIdID("iv_status"));
        ivStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popExameStatus.pop();
            }
        });
        ivClock=(ImageView) view.findViewById(EUExUtil.getResIdID("iv_clock"));
        ivClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popExameStatus.pop();
            }
        });
    }
}
