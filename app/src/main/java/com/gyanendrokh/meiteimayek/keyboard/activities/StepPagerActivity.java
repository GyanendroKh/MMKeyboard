package com.gyanendrokh.meiteimayek.keyboard.activities;

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

public class StepPagerActivity extends AppCompatActivity
        implements View.OnClickListener {

  private List<Step> steps;
  private StepPagerAdapter adapter;

  private RelativeLayout mContainerLayout;
  private ViewPager pager;
  private Button next, prev;
  private LinearLayout indicatorLayout;
  private RelativeLayout buttonContainer;

  private int currentItem;
  private IMEUtils mImeUtils;
  private String prevText, nextText, finishText, cancelText, skipText;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTheme(android.support.v7.appcompat.R.style.Theme_AppCompat_Light_NoActionBar);
    setContentView(R.layout.activity_tutorial);
    steps = new ArrayList<>();
    mImeUtils = new IMEUtils(this);
    initTexts();
    initViews();
    initAdapter();
    registerIMEChangeListener();
  }

  private void initTexts() {
    prevText = "Back";
    cancelText = "Cancel";
    finishText = "Finish";
    nextText = "Next";
    skipText = "Skip";
  }

  private void initAdapter() {
    adapter = new StepPagerAdapter(getSupportFragmentManager(), steps);
    pager.setAdapter(adapter);
    pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

      @Override
      public void onPageSelected(int position) {
        currentItem = position;
        controlPosition(position);
      }

      @Override
      public void onPageScrollStateChanged(int state) {}
    });
  }

  private void controlPosition(int position) {
    notifyIndicator();
    if (position == steps.size() - 1) {
      next.setText(finishText);
      prev.setText(prevText);
    } else if (position == 0) {
      prev.setText(cancelText);
      if(steps.get(position).getBtnAction() == mImeUtils.getState()) next.setText(skipText);
      else next.setText(nextText);
    } else {
      prev.setText(prevText);
      if(steps.get(position).getBtnAction() == mImeUtils.getState()) next.setText(skipText);
      else next.setText(nextText);
    }

    mContainerLayout.setBackgroundColor(steps.get(position).getBackgroundColor());
    buttonContainer.setBackgroundColor(steps.get(position).getBackgroundColor());
  }

  private void initViews() {
    currentItem = 0;

    pager = (ViewPager) findViewById(R.id.viewPager);
    next = (Button) findViewById(R.id.btnNext);
    prev = (Button) findViewById(R.id.btnPrev);
    indicatorLayout = (LinearLayout) findViewById(R.id.indicatorLayout);
    mContainerLayout = (RelativeLayout) findViewById(R.id.containerLayout);
    buttonContainer = (RelativeLayout) findViewById(R.id.buttonContainer);

    next.setOnClickListener(this);
    prev.setOnClickListener(this);
  }

  public void addFragment(Step step) {
    steps.add(step);
    adapter.notifyDataSetChanged();
    notifyIndicator();
    controlPosition(currentItem);
  }

  public void notifyIndicator() {
    if (indicatorLayout.getChildCount() > 0)
      indicatorLayout.removeAllViews();

    for (int i = 0; i < steps.size(); i++) {
      ImageView imageView = new ImageView(this);
      imageView.setPadding(8, 8, 8, 8);
      int drawable = R.drawable.circle_black;
      if (i == currentItem)
        drawable = R.drawable.circle_white;

      imageView.setImageResource(drawable);

      final int finalI = i;
      imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          changeFragment(finalI);
        }
      });

      indicatorLayout.addView(imageView);
    }

  }

  @Override
  public void onBackPressed() {
    if (currentItem == 0) {
      super.onBackPressed();
    } else {
      changeFragment(false);
    }
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.btnNext) {
      if(mImeUtils.getState() == steps.get(currentItem).getBtnAction()
              && mImeUtils.getState() != IMEUtils.STATE_READY)
        Toast.makeText(this, getString(R.string.setup_state_skipped), Toast.LENGTH_SHORT).show();
      changeFragment(true);
    } else if (v.getId() == R.id.btnPrev) {
      changeFragment(false);
    }
  }

  private void changeFragment(int position) {
    pager.setCurrentItem(position, true);
  }

  private void changeFragment(boolean isNext) {
    int item = currentItem;
    item = (isNext) ? item + 1 : item - 1;

    if (item < 0 || item == steps.size()) {
      startActivity(new Intent(this, MainActivity.class));
      finish();
    }
    else pager.setCurrentItem(item, true);
  }

  private void registerIMEChangeListener() {
    BroadcastReceiver receiver = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        int oldState = mImeUtils.getState();
        mImeUtils = new IMEUtils(StepPagerActivity.this);
        int newState = mImeUtils.getState();

        if(oldState != newState) {
          if(steps.get(currentItem).getBtnAction() == IMEUtils.STATE_NOT_DEFAULT) {
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
