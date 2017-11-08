package com.gyanendrokh.meiteimayek.keyboard.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.content.Intent;

import com.gyanendrokh.meiteimayek.keyboard.R;

public class SplashActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        startActivity(new Intent(getApplicationContext(), SetupActivity.class));
      }
    }, 1000);
  }

  @Override
  public void onPause() {
    super.onPause();
    finish();
  }

}
