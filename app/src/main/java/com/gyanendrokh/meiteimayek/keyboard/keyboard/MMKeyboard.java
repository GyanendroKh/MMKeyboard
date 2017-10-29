package com.gyanendrokh.meiteimayek.keyboard.keyboard;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.inputmethodservice.Keyboard;

public class MMKeyboard extends Keyboard {

  MMKeyboard(Context context, int xmlLayoutResId) {
    super(context, xmlLayoutResId);
  }

  @Override
  protected Key createKeyFromXml(Resources res, Row parent, int x, int y,
                                 XmlResourceParser parser) {
    return new MMKeyboardKey(res, parent, x, y, parser);
  }

  private class MMKeyboardKey extends Keyboard.Key {
    MMKeyboardKey(Resources res, Keyboard.Row parent, int x, int y, XmlResourceParser parser) {
      super(res, parent, x, y, parser);
    }
  }

}
