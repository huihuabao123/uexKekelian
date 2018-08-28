package com.kekelian.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;



/**
 * fragmentPage适配器
 */
public class IntegralFragmentPagerAdapter extends FragmentPagerAdapter {
	private ArrayList<Fragment> fragments;
	private FragmentManager fm;

	public IntegralFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
		this.fm = fm;
	}

	public IntegralFragmentPagerAdapter(FragmentManager fm,
			ArrayList<Fragment> fragments) {
		super(fm);
		this.fm = fm;
		this.fragments = fragments;
	}

	@Override
	public int getCount() {
		return fragments.size();
	}
	
	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

//	@Override
//	public int getItemPosition(Object object) {
//		return POSITION_NONE;
//	}

	/**从新设置fragment集合
	 * @param fragments
	 */
	public void setFragments(ArrayList<Fragment> fragments) {
		if (this.fragments != null) {
			FragmentTransaction ft = fm.beginTransaction();
			for (Fragment f : this.fragments) {
				ft.remove(f);
			}
			ft.commit();
			ft = null;
			fm.executePendingTransactions();
		}
		if(fragments!=null)
			this.fragments = fragments;
		notifyDataSetChanged();
	}
	public void clearFragment(){
		setFragments(null);
	}
	
//	@Override
//	public Object instantiateItem(ViewGroup container, final int position) {
//		Object obj = super.instantiateItem(container, position);
//		return obj;
//	}

}
