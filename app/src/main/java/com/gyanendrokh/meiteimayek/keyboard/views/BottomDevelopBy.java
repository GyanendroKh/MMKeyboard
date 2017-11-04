package com.gyanendrokh.meiteimayek.keyboard.views;

import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Color;

import com.gyanendrokh.meiteimayek.keyboard.R;

public class BottomDevelopBy {

  private final Context mContext;
  private final View mRootView;
  private final RelativeLayout mMainContent;
  private RelativeLayout.LayoutParams mParams;
  private TextView mText1, mText2;

  public BottomDevelopBy(Context context) {
    mContext = context;
    mRootView = getLayoutFromXML();
    mMainContent = mRootView.findViewById(R.id.bottom_develop_by);
    getTextViews();
    setParams();
  }

  private View getLayoutFromXML() {
    LayoutInflater lInflater = (LayoutInflater) mContext.getSystemService(
      Context.LAYOUT_INFLATER_SERVICE);
    return lInflater.inflate(R.layout.developed_w_by, null);
  }

  private void getTextViews() {
    mText1 = mRootView.findViewById(R.id.developed_w_by_1);
    mText2 = mRootView.findViewById(R.id.developed_w_by_2);
  }

  public void setTextColor(int color) {
    mText1.setTextColor(color);
    mText2.setTextColor(color);
  }

  private void setParams() {
    mParams = new RelativeLayout.LayoutParams(
      RelativeLayout.LayoutParams.MATCH_PARENT,
      RelativeLayout.LayoutParams.WRAP_CONTENT
    );
    mParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
  }

  public RelativeLayout getView() {
    return mMainContent;
  }

  public int getId() {
    return mMainContent.getId();
  }

  public RelativeLayout.LayoutParams getParams() {
    return mParams;
  }

}
