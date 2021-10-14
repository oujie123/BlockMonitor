package com.gxa.blockmonitor;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BadView extends androidx.appcompat.widget.AppCompatTextView {
    public BadView(@NonNull Context context) {
        super(context);
    }

    public BadView(@NonNull Context context, @Nullable  AttributeSet attrs) {
        super(context, attrs);
    }

    public BadView(@NonNull  Context context, @Nullable  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
