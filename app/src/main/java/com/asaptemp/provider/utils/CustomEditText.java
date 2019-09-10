package com.asaptemp.provider.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.KeyEvent;

import androidx.appcompat.widget.AppCompatEditText;

import com.asaptemp.provider.R;

public class CustomEditText extends AppCompatEditText {

    OnKeyboardDownListener mListener;
    private TypeFactory mFontFactory;

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context, attrs);
    }

    public CustomEditText(Context context) {
        super(context);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {


        TypedArray array = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomTextView,
                0, 0);
        int typefaceType;
        try {
            typefaceType = array.getInteger(R.styleable.CustomTextView_font_name, 0);
        } finally {
            array.recycle();
        }
        if (!isInEditMode()) {
            setTypeface(getTypeFace(typefaceType));
        }

    }

    public Typeface getTypeFace(int type) {
        if (mFontFactory == null)
            mFontFactory = new TypeFactory(getContext());

        switch (type) {
            case Constants.POPPINS_REGULAR:
                return TypeFactory.poppinsRegular;

            case Constants.POPPINS_MEDIUM:
                return TypeFactory.poppinsMedium;

            case Constants.POPPINS_BOLD:
                return TypeFactory.poppinsBold;

            case Constants.POPPINS_SEMI_BOLD:
                return TypeFactory.poppinsSemiBold;

            case Constants.POPPINS_BLACK:
                return TypeFactory.poppinsBlack;

        }

        return TypeFactory.poppinsRegular;
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if (mListener != null)
                mListener.onKeyDown();
            return false;
        }
        return super.dispatchKeyEvent(event);
    }

    public void setListener(OnKeyboardDownListener listener) {
        this.mListener = listener;


    }

    public interface OnKeyboardDownListener {
        void onKeyDown();
    }

}
