package com.gyanendrokh.meiteimayek.keyboard.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import com.gyanendrokh.meiteimayek.keyboard.R;

public class AboutMeFragment extends Fragment {

  public static AboutMeFragment getInstance() {
    return new AboutMeFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_about_me, container, false);
  }

}
