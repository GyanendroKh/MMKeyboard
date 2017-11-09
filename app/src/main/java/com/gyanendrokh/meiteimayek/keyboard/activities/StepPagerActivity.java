package com.gyanendrokh.meiteimayek.keyboard.activities;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.gyanendrokh.meiteimayek.keyboard.R;
import com.gyanendrokh.meiteimayek.keyboard.adapters.StepPagerAdapter;
import com.gyanendrokh.meiteimayek.keyboard.commons.Step;
import com.gyanendrokh.meiteimayek.keyboard.utils.IMEUtils;

@SuppressLint("Registered")
public class StepPagerActivity extends AppCompatActivity
        implements View.OnClickListener {

  private List<Step> mSteps;
  private StepPagerAdapter mPagerAdapter;

  private RelativeLayout mContainerLayout;
  private ViewPager mPagerView;
  private Button mBtnNext, mBtnPrev;
  private LinearLayout mIndicatorLayout;
  private RelativeLayout mButtonContainer;

  private int mCurrentItemPos;
  private IMEUtils mImeUtils;
  private String mPrevText, mNextText, mFinishText, mCancelText, mSkipText;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTheme(android.support.v7.appcompat.R.style.Theme_AppCompat_Light_NoActionBar);
    setContentView(R.layout.activity_tutorial);
    mSteps = new ArrayList<>();
    mImeUtils = new IMEUtils(this);
    initTexts();
    initViews();
    initAdapter();
    registerIMEChangeListener();
  }

  private void initTexts() {
    mPrevText = "Back";
    mCancelText = "Cancel";
    mFinishText = "Finish";
    mNextText = "Next";
    mSkipText = "Skip";
  }

  private void initAdapter() {
    mPagerAdapter = new StepPagerAdapter(getSupportFragmentManager(), mSteps);
    mPagerView.setAdapter(mPagerAdapter);
    mPagerView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

      @Override
      public void onPageSelected(int position) {
        mCurrentItemPos = position;
        controlPosition(position);
      }

      @Override
      public void onPageScrollStateChanged(int state) {}
    });
  }

  private void controlPosition(int position) {
    notifyIndicator();
    if (position == mSteps.size() - 1) {
      mBtnNext.setText(mFinishText);
      mBtnPrev.setText(mPrevText);
    } else if (position == 0) {
      mBtnPrev.setText(mCancelText);
      if(mSteps.get(position).getBtnAction() == mImeUtils.getState()) mBtnNext.setText(mSkipText);
      else mBtnNext.setText(mNextText);
    } else {
      mBtnPrev.setText(mPrevText);
      if(mSteps.get(position).getBtnAction() == mImeUtils.getState()) mBtnNext.setText(mSkipText);
      else mBtnNext.setText(mNextText);
    }

    mContainerLayout.setBackgroundColor(mSteps.get(position).getBackgroundColor());
    mButtonContainer.setBackgroundColor(mSteps.get(position).getBackgroundColor());
  }

  private void initViews() {
    mCurrentItemPos = 0;

    mPagerView = (ViewPager) findViewById(R.id.viewPager);
    mBtnNext = (Button) findViewById(R.id.btnNext);
    mBtnPrev = (Button) findViewById(R.id.btnPrev);
    mIndicatorLayout = (LinearLayout) findViewById(R.id.indicatorLayout);
    mContainerLayout = (RelativeLayout) findViewById(R.id.containerLayout);
    mButtonContainer = (RelativeLayout) findViewById(R.id.buttonContainer);

    mBtnNext.setOnClickListener(this);
    mBtnPrev.setOnClickListener(this);
  }

  public void addFragment(Step step) {
    mSteps.add(step);
    mPagerAdapter.notifyDataSetChanged();
    notifyIndicator();
    controlPosition(mCurrentItemPos);
  }

  public void notifyIndicator() {
    if (mIndicatorLayout.getChildCount() > 0)
      mIndicatorLayout.removeAllViews();

    for (int i = 0; i < mSteps.size(); i++) {
      ImageView imageView = new ImageView(this);
      imageView.setPadding(8, 8, 8, 8);
      int drawable = R.drawable.circle_black;
      if (i == mCurrentItemPos)
        drawable = R.drawable.circle_white;

      imageView.setImageResource(drawable);

      final int finalI = i;
      imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          changeFragment(finalI);
        }
      });

      mIndicatorLayout.addView(imageView);
    }

  }

  @Override
  public void onBackPressed() {
    if (mCurrentItemPos == 0) {
      super.onBackPressed();
    } else {
      changeFragment(false);
    }
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.btnNext) {
      if(mImeUtils.getState() == mSteps.get(mCurrentItemPos).getBtnAction()
              && mImeUtils.getState() != IMEUtils.STATE_READY)
        Toast.makeText(this, getString(R.string.setup_state_skipped), Toast.LENGTH_SHORT).show();
      changeFragment(true);
    } else if (v.getId() == R.id.btnPrev) {
      changeFragment(false);
    }
  }

  private void changeFragment(int position) {
    mPagerView.setCurrentItem(position, true);
  }

  private void changeFragment(boolean isNext) {
    int item = mCurrentItemPos;
    item = (isNext) ? item + 1 : item - 1;

    if (item < 0 || item == mSteps.size()) {
      startActivity(new Intent(this, MainActivity.class));
      finish();
    }
    else mPagerView.setCurrentItem(item, true);
  }

  private void registerIMEChangeListener() {
    BroadcastReceiver receiver = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        int oldState = mImeUtils.getState();
        mImeUtils = new IMEUtils(StepPagerActivity.this);
        int newState = mImeUtils.getState();

        if(oldState != newState) {
          if(mSteps.get(mCurrentItemPos).getBtnAction() == IMEUtils.STATE_NOT_DEFAULT) {
            if(newState == IMEUtils.STATE_READY) changeFragment(true);
          }
        }
      }
    };

    registerReceiver(receiver, new IntentFilter(Intent.ACTION_INPUT_METHOD_CHANGED));
  }

  @Override
  protected void onResume() {
    super.onResume();

    if(mImeUtils.getState() == IMEUtils.STATE_NOT_ENABLED) {
      if((new IMEUtils(this)).getState() == IMEUtils.STATE_NOT_DEFAULT) changeFragment(true);
    }
  }

  @Override
  protected void onStop() {
    super.onStop();

    if(mImeUtils.getState() == IMEUtils.STATE_READY) finish();
  }
}
