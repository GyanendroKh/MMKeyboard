package com.gyanendrokh.meiteimayek.keyboard.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.graphics.Color;

import com.gyanendrokh.meiteimayek.keyboard.R;
import com.gyanendrokh.meiteimayek.keyboard.views.HomeNotSetupView;
import com.gyanendrokh.meiteimayek.keyboard.views.BottomDevelopBy;

public class MainActivity extends AppCompatActivity
  implements NavigationView.OnNavigationItemSelectedListener {

  private Toolbar mToolbar;
  private DrawerLayout mDrawLayout;
  private NavigationView mNavView;

  private RelativeLayout mMainLayout;
  private RelativeLayout mMainContent;
  private CardView mCardNotSetUp;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(mToolbar);

    mDrawLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawLayout,
      mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    mDrawLayout.setDrawerListener(drawerToggle);
    drawerToggle.syncState();

    mNavView = (NavigationView) findViewById(R.id.nav_view);
    mNavView.setNavigationItemSelectedListener(this);

    mMainLayout = (RelativeLayout) findViewById(R.id.main_layout);
    mMainContent = (RelativeLayout) findViewById(R.id.main_content);

    addCardNotSetUp();
    setBottomDevelopBy();
  }

  private void addCardNotSetUp() {
    RelativeLayout.LayoutParams cardNotSetUpLParams = new RelativeLayout.LayoutParams(
      RelativeLayout.LayoutParams.MATCH_PARENT,
      RelativeLayout.LayoutParams.WRAP_CONTENT);
    cardNotSetUpLParams.setMargins(5, 0, 5, 0);
    mCardNotSetUp = HomeNotSetupView.getCardView(this);
    mMainContent.addView(mCardNotSetUp, cardNotSetUpLParams);
  }

  private void removeCardNotSetUp() {
    if(mCardNotSetUp == null) return;
    mCardNotSetUp.setVisibility(View.GONE);
  }

  @Override
  public void onBackPressed() {
    if (mDrawLayout.isDrawerOpen(GravityCompat.START)) {
      mDrawLayout.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    mDrawLayout.closeDrawer(GravityCompat.START);
    return true;
  }

  private void setBottomDevelopBy() {
    BottomDevelopBy bdy = new BottomDevelopBy(this);
    bdy.setTextColor(Color.BLACK);

    mMainLayout.addView(bdy.getView(), bdy.getParams());
  }

}
