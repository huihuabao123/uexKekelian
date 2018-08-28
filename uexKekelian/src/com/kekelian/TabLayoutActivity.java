//package com.kekelian;
//
//import android.os.Bundle;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//
//import com.kekelian.adapter.MyViewPagerAdapter;
//import com.kekelian.fragment.CourseItemFragment;
//import com.kekelian.fragment.UnitTestFragment;
//import com.kekelian.transformer.CardTransformer;
//
//import org.zywx.wbpalmstar.engine.universalex.EUExUtil;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TabLayoutActivity extends AppCompatActivity {
//
//
//    private TabLayout tabLayout;
//    private ViewPager viewPager;
//
//    private List<String> list = new ArrayList<>();
//    private List<Fragment> fragmentList = new ArrayList<>();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(EUExUtil.getResLayoutID("activity_tab_layout"));
//        init();
//    }
//
//
//
//    private void init() {
//        tabLayout=(TabLayout) findViewById(EUExUtil.getResIdID("timeline_tablayout"));
//        viewPager=(ViewPager)findViewById(EUExUtil.getResIdID("timeline_viewpager"));
//        list.clear();
//        fragmentList.clear();
//        for (int i = 0; i <=6; i++) {
//            list.add("第"+(i+1)+"课时");
//            fragmentList.add(CourseItemFragment.newInstance());
//        }
//        list.add("单元测验");
//        fragmentList.add(UnitTestFragment.newInstance());
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        viewPager.setPageMargin(20);
//        viewPager.setPageTransformer(false, new CardTransformer());
//        MyViewPagerAdapter myViewPagerAdapter=new MyViewPagerAdapter(getSupportFragmentManager(), fragmentList, list);
//        viewPager.setAdapter(myViewPagerAdapter);
//        viewPager.setOffscreenPageLimit(fragmentList.size());
//        tabLayout.setupWithViewPager(viewPager);
//        //设置选中哪个tab
////        tabLayout.getTabAt(list.size()-1).select();
//    }
//}
