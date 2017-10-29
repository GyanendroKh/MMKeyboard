package com.gyanendrokh.meiteimayek.keyboard.activities;

import android.os.Bundle;
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

  private DrawerLayout mDrawLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    mDrawLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    mDrawLayout.setDrawerListener(drawerToggle);
    drawerToggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
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
  public boolean onNavigationItemSelected(MenuItem item) {
    mDrawLayout.closeDrawer(GravityCompat.START);
    return true;
  }
}
