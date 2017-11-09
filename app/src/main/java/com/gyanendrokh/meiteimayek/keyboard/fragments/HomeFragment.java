package com.gyanendrokh.meiteimayek.keyboard.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.support.v7.widget.CardView;
import android.graphics.Color;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;

import com.gyanendrokh.meiteimayek.keyboard.R;
import com.gyanendrokh.meiteimayek.keyboard.views.HomeNotSetupView;
import com.gyanendrokh.meiteimayek.keyboard.views.BottomDevelopBy;
import com.gyanendrokh.meiteimayek.keyboard.utils.IMEUtils;

public class HomeFragment extends Fragment {

  private RelativeLayout mMainLayout;
  private RelativeLayout mMainContent;
  private CardView mCardNotSetUp;
  private CardView mEditTry;
  private IMEUtils mImeUtils;
  private BroadcastReceiver mReceiver;
  private Context mMainContext;

  public static HomeFragment getInstance() {
    return new HomeFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mImeUtils = new IMEUtils(getActivity());
    mMainContext = getActivity();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, 
    Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);
    mMainLayout = view.findViewById(R.id.main_layout);
    mMainContent = view.findViewById(R.id.main_content);
    mEditTry = view.findViewById(R.id.editTry);

    if(mImeUtils.getState() != IMEUtils.STATE_READY) {
      addCardNotSetUp();
    }
    addBottomDevelopBy();
    registerIMEChangeListener();
    
    return view;
  }

  private void registerIMEChangeListener() {
    mReceiver = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        int oldState = mImeUtils.getState();
        mImeUtils = new IMEUtils(mMainContext);
        int newState = mImeUtils.getState();

        if(oldState != newState) {
          if(newState == IMEUtils.STATE_READY) 
            removeCardNotSetUp();
          else 
            addCardNotSetUp();
        }
      }
    };

    mMainContext.registerReceiver(mReceiver, new IntentFilter(Intent.ACTION_INPUT_METHOD_CHANGED));
  }

  private void addCardNotSetUp() {
    mCardNotSetUp = HomeNotSetupView.getCardView(getActivity());
    LayoutParams cardNotSetUpLParams = new LayoutParams(
      RelativeLayout.LayoutParams.MATCH_PARENT,
      RelativeLayout.LayoutParams.WRAP_CONTENT);
    cardNotSetUpLParams.setMargins(5, 0, 5, 0);
    cardNotSetUpLParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);

    LayoutParams editTryParams = new LayoutParams(
      RelativeLayout.LayoutParams.MATCH_PARENT,
      RelativeLayout.LayoutParams.WRAP_CONTENT
    );
    editTryParams.addRule(RelativeLayout.BELOW, mCardNotSetUp.getId());
    editTryParams.setMargins(5, 15, 5, 15);
    mEditTry.setLayoutParams(editTryParams);

    mMainContent.addView(mCardNotSetUp, cardNotSetUpLParams);
  }

  private void removeCardNotSetUp() {
    if(mCardNotSetUp == null) return;
    mCardNotSetUp.setVisibility(View.GONE);
  }

  private void addBottomDevelopBy() {
    BottomDevelopBy bdy = new BottomDevelopBy(getActivity());
    bdy.setTextColor(Color.WHITE);

    mMainLayout.addView(bdy.getView(), bdy.getParams());
  }

}
