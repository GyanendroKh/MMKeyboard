package com.gyanendrokh.meiteimayek.keyboard.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.gyanendrokh.meiteimayek.keyboard.R;
import com.gyanendrokh.meiteimayek.keyboard.fragments.HomeFragment;
import com.gyanendrokh.meiteimayek.keyboard.fragments.AboutMeFragment;
import com.gyanendrokh.meiteimayek.keyboard.fragments.OpenSourceFragment;

public class MainActivity extends AppCompatActivity
  implements NavigationView.OnNavigationItemSelectedListener {

  private static final int FRAGMENT_CONTAINER = R.id.main_view;

  private Toolbar mToolbar;
  private DrawerLayout mDrawLayout;
  private NavigationView mNavView;

  private FragmentManager mFragmentManager;

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

    mFragmentManager = getSupportFragmentManager();

    renderFragment(HomeFragment.getInstance());

    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
  }

  private void renderFragment(Fragment fragment) {
    Fragment curFragment = mFragmentManager.findFragmentById(FRAGMENT_CONTAINER);

    if(curFragment == fragment) return;
    mFragmentManager.beginTransaction().replace(FRAGMENT_CONTAINER, fragment).commit();
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

    switch(item.getItemId()) {
      case R.id.sNav_home:
        renderFragment(HomeFragment.getInstance());
        break;
      case R.id.sNav_aboutMe:
        renderFragment(AboutMeFragment.getInstance());
        break;
      case R.id.sNav_openSource:
        renderFragment(OpenSourceFragment.getInstance());
        break;
    }

    mDrawLayout.closeDrawer(GravityCompat.START);
    return true;
  }

}
