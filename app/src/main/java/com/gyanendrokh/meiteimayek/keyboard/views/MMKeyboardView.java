package com.gyanendrokh.meiteimayek.keyboard.views;

import android.inputmethodservice.KeyboardView;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class MMKeyboardView extends KeyboardView {

    public static final int KEYCODE_LANGUAGE_SWITCH = -101;

    public MMKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MMKeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

}
