package com.gyanendrokh.meiteimayek.keyboard.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;
import android.support.annotation.Nullable;

import com.gyanendrokh.meiteimayek.keyboard.R;
import com.gyanendrokh.meiteimayek.keyboard.activities.MainActivity;
import com.gyanendrokh.meiteimayek.keyboard.commons.Step;
import com.gyanendrokh.meiteimayek.keyboard.utils.IMEUtils;

public class StepFragment extends Fragment implements View.OnClickListener {

  public static final String STEP_PARCEL = "step_parcel";

  private Step mStep;
  private View mRootView;
  private RelativeLayout mFragmentContainer;
  private ImageView mImage;
  private TextView mTitle;
  private TextView mContent;
  private Button mButton;

  private IMEUtils mImeUtils;

  public static StepFragment createFragment(Step step) {
    Bundle bundle = new Bundle();
    bundle.putParcelable(STEP_PARCEL, step);
    StepFragment fragment = new StepFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mStep = getArguments().getParcelable(STEP_PARCEL);
    mImeUtils = new IMEUtils(getActivity());
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    mRootView = inflater.inflate(R.layout.fragment_step, container, false);

    initViews();
    initData();

    return mRootView;
  }

  private void initViews() {
    mFragmentContainer = mRootView.findViewById(R.id.fragmentContainer);
    mImage = mRootView.findViewById(R.id.stepImage);
    mTitle = mRootView.findViewById(R.id.stepTitle);
    mContent = mRootView.findViewById(R.id.stepContent);
    mButton = mRootView.findViewById(R.id.stepActionBtn);
    mButton.setOnClickListener(this);
  }

  private void initData() {
    mFragmentContainer.setBackgroundColor(mStep.getBackgroundColor());
    mImage.setImageResource(mStep.getImage());
    mTitle.setText(mStep.getTitle());
    mContent.setText(mStep.getContent());
    mButton.setText(mStep.getBtnText());
  }

  @Override
  public void onClick(View view) {
    switch (mStep.getBtnAction()) {
      case IMEUtils.STATE_READY:
        startActivity(new Intent(getActivity(), MainActivity.class));
        break;
      case IMEUtils.STATE_NOT_DEFAULT:
        mImeUtils.openSelectSettings();
        break;
      case IMEUtils.STATE_NOT_ENABLED:
        mImeUtils.openSettingsEnable();
    }
  }

}
