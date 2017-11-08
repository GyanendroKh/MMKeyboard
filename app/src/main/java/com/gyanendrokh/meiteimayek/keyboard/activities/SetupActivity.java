package com.gyanendrokh.meiteimayek.keyboard.activities;

import android.content.Intent;
import android.os.Bundle;

import com.gyanendrokh.meiteimayek.keyboard.R;
import com.gyanendrokh.meiteimayek.keyboard.commons.Step;
import com.gyanendrokh.meiteimayek.keyboard.utils.IMEUtils;

public class SetupActivity extends StepPagerActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    IMEUtils imeUtils = new IMEUtils(this);

    if(imeUtils.getState() == IMEUtils.STATE_READY) {
      startActivity(new Intent(this, MainActivity.class));
      finish();
    }

    if(imeUtils.getState() == IMEUtils.STATE_NOT_ENABLED) {
      addFragment(new Step.Builder()
        .setTitle(getString(R.string.setup_state_select_title))
        .setContent(getString(R.string.setup_state_select_content))
        .setBackgroundColor(getResources().getColor(R.color.setup_state_enable))
        .setImage(R.drawable.state_select)
        .setActionBtn(getString(R.string.setup_state_select_btn_text), IMEUtils.STATE_NOT_ENABLED).build());
    }
    if(imeUtils.getState() >= IMEUtils.STATE_NOT_DEFAULT) {
      addFragment(new Step.Builder()
        .setTitle(getString(R.string.setup_state_default_title))
        .setContent(getString(R.string.setup_state_default_content))
        .setBackgroundColor(getResources().getColor(R.color.setup_state_default))
        .setImage(R.drawable.state_make_default)
        .setActionBtn(getString(R.string.setup_state_default_btn_text), IMEUtils.STATE_NOT_DEFAULT).build());
    }
    addFragment(new Step.Builder()
      .setTitle(getString(R.string.setup_state_ready_title))
      .setContent(getString(R.string.setup_state_ready_content))
      .setBackgroundColor(getResources().getColor(R.color.setup_state_ready))
      .setImage(R.drawable.state_ready)
      .setActionBtn(getString(R.string.setup_state_ready_btn_text), IMEUtils.STATE_READY).build());
  }
}
