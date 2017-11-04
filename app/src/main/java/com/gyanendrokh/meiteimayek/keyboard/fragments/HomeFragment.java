package com.gyanendrokh.meiteimayek.keyboard.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.support.v7.widget.CardView;
import android.graphics.Color;

import com.gyanendrokh.meiteimayek.keyboard.R;
import com.gyanendrokh.meiteimayek.keyboard.views.HomeNotSetupView;
import com.gyanendrokh.meiteimayek.keyboard.views.BottomDevelopBy;

public class HomeFragment extends Fragment {

  private RelativeLayout mMainLayout;
  private RelativeLayout mMainContent;
  private CardView mCardNotSetUp;

  public static HomeFragment getInstance() {
    return new HomeFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mCardNotSetUp = HomeNotSetupView.getCardView(getActivity());
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);
    mMainLayout = view.findViewById(R.id.main_layout);
    mMainContent = view.findViewById(R.id.main_content);

    addCardNotSetUp();
    addBottomDevelopBy();

    return view;
  }

  private void addCardNotSetUp() {
    RelativeLayout.LayoutParams cardNotSetUpLParams = new RelativeLayout.LayoutParams(
      RelativeLayout.LayoutParams.MATCH_PARENT,
      RelativeLayout.LayoutParams.WRAP_CONTENT);
    cardNotSetUpLParams.setMargins(5, 0, 5, 0);
    mMainContent.addView(mCardNotSetUp, cardNotSetUpLParams);
  }

  private void removeCardNotSetUp() {
    if(mCardNotSetUp == null) return;
    mCardNotSetUp.setVisibility(View.GONE);
  }

  private void addBottomDevelopBy() {
    BottomDevelopBy bdy = new BottomDevelopBy(getActivity());
    bdy.setTextColor(Color.BLACK);

    mMainLayout.addView(bdy.getView(), bdy.getParams());
  }

}
