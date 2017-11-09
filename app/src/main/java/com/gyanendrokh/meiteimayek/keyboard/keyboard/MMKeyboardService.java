package com.gyanendrokh.meiteimayek.keyboard.keyboard;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.IBinder;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.gyanendrokh.meiteimayek.keyboard.views.MMKeyboardView;
import com.gyanendrokh.meiteimayek.keyboard.R;

public class MMKeyboardService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

  private InputMethodManager mInputMethodManager;

  private MMKeyboardView mKeyView;
  private MMKeyboard mMMCharKeyboard, mMMSymbolKeyboard;

  @Override
  public void onCreate() {
    super.onCreate();
    mInputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
  }

  @Override
  public void onInitializeInterface() {
    mMMCharKeyboard = new MMKeyboard(this, R.xml.mmkeyboard_chars);
    mMMSymbolKeyboard = new MMKeyboard(this, R.xml.mmkeyboard_symbols);
  }

  @SuppressLint("InflateParams")
  @Override
  public View onCreateInputView() {
    mKeyView = (MMKeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
    mKeyView.setOnKeyboardActionListener(this);
    setMMKeyboard(mMMCharKeyboard);
    return mKeyView;
  }

  private void setMMKeyboard(MMKeyboard nextKeyboard) {
    mKeyView.setKeyboard(nextKeyboard);
  }

  @Override
  public void onKey(int primaryCode, int[] keyCodes) {
    InputConnection ic = getCurrentInputConnection();
    switch(primaryCode){
      case Keyboard.KEYCODE_DELETE :
          handleBackspace();
          break;
      case 10:
        EditorInfo curEditor = getCurrentInputEditorInfo();
        switch (curEditor.imeOptions & EditorInfo.IME_MASK_ACTION) {
          case EditorInfo.IME_ACTION_DONE:
            getCurrentInputConnection().performEditorAction(EditorInfo.IME_ACTION_DONE);
            break;
          case EditorInfo.IME_ACTION_GO:
            getCurrentInputConnection().performEditorAction(EditorInfo.IME_ACTION_GO);
            break;
          case EditorInfo.IME_ACTION_NEXT:
            keyDownUp(66);
            break;
          case EditorInfo.IME_ACTION_SEARCH:
            getCurrentInputConnection().performEditorAction(EditorInfo.IME_ACTION_SEARCH);
            break;
          case EditorInfo.IME_ACTION_SEND:
            keyDownUp(66);
            break;
          default:
            keyDownUp(66);
            break;
        }
        break;
      case -2:
        handleKeyboardSwap();
        break;
      case MMKeyboardView.KEYCODE_LANGUAGE_SWITCH:
        handleLanguageSwitch();
        break;
      default:
        char code = (char)primaryCode;
        ic.commitText(String.valueOf(code), 1);
    }
  }

  private void keyDownUp(int keyEventCode) {
    getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, keyEventCode));
    getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, keyEventCode));
  }

  private void handleBackspace() {
    keyDownUp(KeyEvent.KEYCODE_DEL);
  }

  private void handleKeyboardSwap() {
    if(mKeyView != null) {
      if(mKeyView.getKeyboard() == mMMCharKeyboard) {
        setMMKeyboard(mMMSymbolKeyboard);
      } else {
        setMMKeyboard(mMMCharKeyboard);
      }
    }
  }

  private IBinder getToken() {
    final Dialog dialog = getWindow();
    if (dialog == null) return null;
    final Window window = dialog.getWindow();
    if (window == null) return null;
    return window.getAttributes().token;
  }

  private void handleLanguageSwitch() {
    IBinder token = getToken();
    if(mInputMethodManager.shouldOfferSwitchingToNextInputMethod(token))
      mInputMethodManager.switchToNextInputMethod(token, false);
    else
      Toast.makeText(getApplicationContext(), "Could not Switch Keyboard. Check Help!!!", Toast.LENGTH_LONG).show();
  }

  @Override
  public void onPress(int primaryCode) {}

  @Override
  public void onRelease(int primaryCode) {}

  @Override
  public void onText(CharSequence text) {}

  @Override
  public void swipeDown() {}

  @Override
  public void swipeLeft() {}

  @Override
  public void swipeRight() {}

  @Override
  public void swipeUp() {}
}
