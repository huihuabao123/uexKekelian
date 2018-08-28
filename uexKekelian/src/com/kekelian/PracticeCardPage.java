package com.kekelian;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kekelian.adapter.MyViewPagerAdapter;
import com.kekelian.fragment.CourseItemFragment;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 课课练卡片界面
 */
public class PracticeCardPage extends FragmentActivity {

    /**
     * 获取资源的工具类
     */
    private  FragmentManager manager;
    private List<TextView> viewList=new ArrayList<TextView>();
    //存放tabItem的文本
    private List<String> list = new ArrayList<String>();
    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    /**
     * UI控件初始化
     *
     */

    private ViewPager viewPager;
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout llTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(EUExUtil.getResLayoutID("activity_practice_card_page"));
        initViews();

    }

    private void initDatas() {
        list.clear();
        fragmentList.clear();
        for (int i = 0; i <=8; i++) {
            list.add("学期" + (i+1));
            fragmentList.add(CourseItemFragment.newInstance(i));
        }
        for (int i=0;i<list.size();i++){
            //创建textview
            TextView textView = new TextView(PracticeCardPage.this);
            textView.setText(list.get(i));
            textView.setTextSize(20);
            textView.setId(i+1000);
            //点击事件
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = view.getId();
                    viewPager.setCurrentItem(id-1000);
                }
            });
            //刚进程序第一个默认红色,其他为黑色
            if(i ==0){
                textView.setTextColor(Color.RED);
            }else {
                textView.setTextColor(Color.BLACK);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(20,10,20,10);
            //添加到布局中
            llTitle.addView(textView,layoutParams);
            viewList.add(textView);

        }
        MyViewPagerAdapter myViewPagerAdapter=new MyViewPagerAdapter(manager, fragmentList);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.setOffscreenPageLimit(fragmentList.size());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, int i1) {
                //list中存的是 textview
                for (int i = 0; i < viewList.size(); i++) {
                    if(position==i){
                        viewList.get(i).setTextColor(Color.RED);
                    }else{
                        viewList.get(i).setTextColor(Color.BLACK);
                    }
                }
                //获取当前的textview
                TextView textView = viewList.get(position);
                //width是每次滑动的距离
                int width = textView.getWidth()+10;
                //让scrollView滑动   滑动距离是textview之间的间距
                horizontalScrollView.scrollTo(width*position,0);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * 控件初始化
     */
    private void initViews() {
        manager = getSupportFragmentManager();
        horizontalScrollView=(HorizontalScrollView)findViewById(EUExUtil.getResIdID("Scrollview"));
        llTitle=(LinearLayout)findViewById(EUExUtil.getResIdID("Linearlayout"));
        viewPager=(ViewPager)findViewById(EUExUtil.getResIdID("viewPager"));

       initDatas();



    }
}
