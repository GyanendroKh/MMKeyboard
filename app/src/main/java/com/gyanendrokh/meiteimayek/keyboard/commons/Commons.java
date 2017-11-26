package com.gyanendrokh.meiteimayek.keyboard.commons;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Commons {

  public static final String FB_LINK = "https://facebook.com/gyanendrokh";
  public static final String GITHUB_LINK = "https://github.com/GyanendroKh";
  public static final String GMAIL = "khgyanendro77@gmail.com";
  public static final String GITHUB_LINK_FOR_KEYBOARD_PROJECT = GITHUB_LINK + "/MMKeyboard";

  private Context mContext;
  private Intent mIntent;

  public Commons(Context context) {
    mContext = context;
  }

  public void openUrl(String url) {
    mIntent = new Intent(Intent.ACTION_VIEW);
    mIntent.setData(Uri.parse(url));
    mContext.startActivity(mIntent);
  }

  public void sendEmail(String address, String subject, String body) {
    mIntent = new Intent(Intent.ACTION_SEND);
    
    mIntent.setType("text/html");
    mIntent.putExtra(Intent.EXTRA_EMAIL, address);
    mIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
    mIntent.putExtra(Intent.EXTRA_TEXT, body);

    mContext.startActivity(Intent.createChooser(mIntent, "Send Mail..."));
  }

}