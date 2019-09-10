package com.asaptemp.provider.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.asaptemp.provider.R;

public class CustomTextView extends AppCompatTextView {

    public TypeFactory mFontFactory;

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context, attrs);
    }

    public CustomTextView(Context context) {
        super(context);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        TypedArray array = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.CustomTextView,
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


}
