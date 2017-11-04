package com.gyanendrokh.meiteimayek.keyboard.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.graphics.Color;
import android.widget.RelativeLayout;

import com.gyanendrokh.meiteimayek.keyboard.R;
import com.gyanendrokh.meiteimayek.keyboard.views.BottomDevelopBy;

public class OpenSourceFragment extends Fragment {

  private RelativeLayout mMainLayout;

  public static OpenSourceFragment getInstance() {
    return new OpenSourceFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_open_source, container, false);
    mMainLayout = view.findViewById(R.id.os_mainView);
    addBottomDevelopBy();
    return view;
  }

  private void addBottomDevelopBy() {
    BottomDevelopBy bdy = new BottomDevelopBy(getActivity());
    bdy.setTextColor(Color.WHITE);

    mMainLayout.addView(bdy.getView(), bdy.getParams());
  }

}
