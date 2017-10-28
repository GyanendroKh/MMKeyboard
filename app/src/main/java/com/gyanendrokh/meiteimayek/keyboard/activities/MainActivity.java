package com.gyanendrokh.meiteimayek.keyboard.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.gyanendrokh.meiteimayek.keyboard.R;

public class MainActivity extends AppCompatActivity
  implements NavigationView.OnNavigationItemSelectedListener {

  private Toolbar mToolbar;
  private FloatingActionButton mFab;
  private DrawerLayout mDraw;
  private ActionBarDrawerToggle mToogle;
  private NavigationView mNavigationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    init();
  }

  private void init() {
    initViews();
    initStates();
  }

  private void initViews() {
    mFab = (FloatingActionButton) findViewById(R.id.fab);
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mDraw = (DrawerLayout) findViewById(R.id.drawer_layout);
    mToogle = new ActionBarDrawerToggle(this, mDraw,
      mToolbar,
      R.string.navigation_drawer_open,
      R.string.navigation_drawer_close
    );
    mNavigationView = (NavigationView) findViewById(R.id.nav_view);
  }

  private void initStates() {
    setSupportActionBar(mToolbar);
    mFab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
      }
    });
    mDraw.setDrawerListener(mToogle);
    mToogle.syncState();
    mNavigationView.setNavigationItemSelectedListener(this);
  }

  @Override
  public void onBackPressed() {
    if (mDraw.isDrawerOpen(GravityCompat.START)) {
      mDraw.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    mDraw.closeDrawer(GravityCompat.START);
    return true;
  }
}
