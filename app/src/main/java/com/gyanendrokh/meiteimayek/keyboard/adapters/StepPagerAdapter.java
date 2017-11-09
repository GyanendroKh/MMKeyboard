package com.gyanendrokh.meiteimayek.keyboard.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import com.gyanendrokh.meiteimayek.keyboard.commons.Step;
import com.gyanendrokh.meiteimayek.keyboard.fragments.StepFragment;

public class StepPagerAdapter extends FragmentPagerAdapter {
    private List<Step> mSteps;

    public StepPagerAdapter(FragmentManager fm, List<Step> stepList) {
        super(fm);
        this.mSteps = stepList;
    }

    @Override
    public Fragment getItem(int position) {
        return StepFragment.createFragment(mSteps.get(position));
    }

    @Override
    public int getCount() {
        return mSteps.size();
    }
}
