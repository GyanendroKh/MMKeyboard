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
import com.gyanendrokh.meiteimayek.keyboard.commons.Commons;

public class OpenSourceFragment extends Fragment {

  private static final String GITHUB_LINK = "";

  private RelativeLayout mMainLayout;
  private Commons mCommons;

  public static OpenSourceFragment getInstance() {
    return new OpenSourceFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_open_source, container, false);
    
    mMainLayout = view.findViewById(R.id.os_mainView);
    mCommons = new Commons(getActivity());

    view.findViewById(R.id.os_github_btn).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        mCommons.openUrl(Commons.GITHUB_LINK_FOR_KEYBOARD_PROJECT);
      }
    });
    
    addBottomDevelopBy();
    return view;
  }

  private void addBottomDevelopBy() {
    BottomDevelopBy bdy = new BottomDevelopBy(getActivity());
    bdy.setTextColor(Color.WHITE);

    mMainLayout.addView(bdy.getView(), bdy.getParams());
  }

}
