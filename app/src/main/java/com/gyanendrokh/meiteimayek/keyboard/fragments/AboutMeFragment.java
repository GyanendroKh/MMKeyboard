package com.gyanendrokh.meiteimayek.keyboard.fragments;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.net.Uri;

import com.gyanendrokh.meiteimayek.keyboard.R;
import com.gyanendrokh.meiteimayek.keyboard.commons.Commons;

public class AboutMeFragment extends Fragment implements View.OnClickListener {

  private View mView;
  private Intent mIntent;
  private Commons mCommons;

  public static AboutMeFragment getInstance() {
    return new AboutMeFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mView = inflater.inflate(R.layout.fragment_about_me, container, false);

    mView.findViewById(R.id.dev_con_fb).setOnClickListener(this);
    mView.findViewById(R.id.dev_con_gmail).setOnClickListener(this);
    mView.findViewById(R.id.dev_con_github).setOnClickListener(this);

    mCommons = new Commons(getActivity());

    return mView;
  }

  @Override
  public void onClick(View view) {
    int id = view.getId();

    switch(id) {
      case R.id.dev_con_fb:
        mCommons.openUrl(Commons.FB_LINK);
        break;
      case R.id.dev_con_github:
        mCommons.openUrl(Commons.GITHUB_LINK);
        break;
      case R.id.dev_con_gmail:
        mCommons.sendEmail(Commons.GMAIL, "Feedback", "");
        break;
    }
  }

}
