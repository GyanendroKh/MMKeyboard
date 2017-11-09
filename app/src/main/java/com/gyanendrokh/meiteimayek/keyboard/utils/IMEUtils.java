package com.gyanendrokh.meiteimayek.keyboard.utils;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.gyanendrokh.meiteimayek.keyboard.keyboard.MMKeyboardService;

import java.util.ArrayList;
import java.util.List;

public class IMEUtils {

  private Context mContext;
  private int mState = 3;
  private InputMethodManager mInputManager;

  public static final int STATE_NOT_ENABLED = 2;
  public static final int STATE_NOT_DEFAULT = 1;
  public static final int STATE_READY = 0;

  public IMEUtils(Context context) {
    mContext = context;
    mInputManager = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
    checkState();
  }

  private void checkState() {
    if(!isMMImeEnabled()) {
      mState = STATE_NOT_ENABLED;
    }else if(!isMMDefaultIME()) {
      mState = STATE_NOT_DEFAULT;
    }else if(isMMDefaultIME()) {
      mState = STATE_READY;
    }
  }

  public String getDefaultIME() {
    String ime = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);
    return ime.substring(ime.lastIndexOf('.') + 1, ime.length());
  }

  public String getMMIME() {
    return MMKeyboardService.class.getSimpleName();
  }

  public boolean isMMDefaultIME() {
    return getDefaultIME().equals(getMMIME());
  }

  public List<String> getAllIMEs() {
    List<String> imes = new ArrayList<>();
    for (InputMethodInfo l : mInputManager.getEnabledInputMethodList()) {
      String packName = l.getServiceName();
      imes.add(packName.substring(packName.lastIndexOf('.') + 1, packName.length()));
    }
    return imes;
  }

  public boolean isMMImeEnabled() {
    for(String ime : getAllIMEs()) {
      if(ime.equals(getMMIME())) return true;
    }
    return false;
  }

  public int getState() {
    return mState;
  }

  public void openSettingsEnable() {
    mContext.startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
  }

  public void openSelectSettings() {
    if (mInputManager != null) {
      mInputManager.showInputMethodPicker();
    } else {
      Toast.makeText(mContext, "There is a problem. Please Try again later." ,
        Toast.LENGTH_LONG).show();
    }
  }

}
