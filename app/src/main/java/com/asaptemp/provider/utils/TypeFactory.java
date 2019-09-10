package com.asaptemp.provider.utils;

import android.content.Context;
import android.graphics.Typeface;

public class TypeFactory {

    public static Typeface poppinsRegular;
    public static Typeface poppinsMedium;
    public static Typeface poppinsBold;
    public static Typeface poppinsSemiBold;
    public static Typeface poppinsBlack;

    private static String POPPINS_REGULAR = "fonts/poppins_regular.ttf";
    private static String POPPINS_MEDIUM = "fonts/poppins_medium.ttf";
    private static String POPPINS_BOLD = "fonts/poppins_bold.ttf";
    private static String POPPINS_SEMI_BOLD = "fonts/poppins_semi_bold.ttf";
    private static String POPPINS_BLACK  = "fonts/poppins_black.ttf";

    public TypeFactory(Context context) {
        poppinsRegular= Typeface.createFromAsset(context.getAssets(), POPPINS_REGULAR);
        poppinsMedium = Typeface.createFromAsset(context.getAssets(), POPPINS_MEDIUM);
        poppinsBold = Typeface.createFromAsset(context.getAssets(), POPPINS_BOLD);
        poppinsSemiBold = Typeface.createFromAsset(context.getAssets(), POPPINS_SEMI_BOLD);
        poppinsBlack = Typeface.createFromAsset(context.getAssets(), POPPINS_BLACK);
    }

}
