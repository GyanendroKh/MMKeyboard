package com.gyanendrokh.meiteimayek.keyboard.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.LayoutInflater;

import com.gyanendrokh.meiteimayek.keyboard.R;
import com.gyanendrokh.meiteimayek.keyboard.activities.SetupActivity;

public class HomeNotSetupView {

  private final Context mContext;
  private CardView mCardView;
  private final LayoutInflater mInflater;

  private HomeNotSetupView(Context context) {
    mContext = context;
    mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    getCardViewFromXML();
  }

  private void getCardViewFromXML() {
    @SuppressLint("InflateParams") View v = mInflater.inflate(R.layout.home_not_setup, null);
    mCardView = v.findViewById(R.id.card_not_setup);
    v.findViewById(R.id.setUpKey).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        mContext.startActivity(new Intent(mContext, SetupActivity.class));
      }
    });
  }

  public static CardView getCardView(Context con) {
    HomeNotSetupView h = new HomeNotSetupView(con);
    return h.mCardView;
  }

}
