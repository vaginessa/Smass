package id.co.technomotion.smass.utils;

import android.graphics.Typeface;

import java.util.Hashtable;

import id.co.technomotion.smass.application.SmassApp;

public class FontUtils {

    // font file name
    private static final String FONT_PATH="fonts/";
    public static final String FONT_BLACK_DECEMBER = "black_december.ttf";
    public static final String FONT_CAVIAR_DREAMS = "caviar_dreams.ttf";

    // store the opened typefaces(fonts)
    private static final Hashtable<String, Typeface> mCache = new Hashtable<String, Typeface>();

    /**
     * Load the given font from assets
     *
     * @param fontName font name
     * @return Typeface object representing the font painting
     */
    public static Typeface loadFontFromAssets(String fontName) {
        // make sure we load each font only once
        synchronized (mCache) {

            if (! mCache.containsKey(fontName)) {

                Typeface typeface = Typeface.createFromAsset(SmassApp.context.getAssets(),FONT_PATH+fontName);
                System.out.println(typeface.toString());
                mCache.put(fontName, typeface);
            }

            return mCache.get(fontName);

        }

    }

}