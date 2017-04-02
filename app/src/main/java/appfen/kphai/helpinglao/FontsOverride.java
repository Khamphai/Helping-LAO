package appfen.kphai.helpinglao;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

/**
 * Created by K'Phai on 10/29/2014.
 */
public final class FontsOverride {
    public static void setDefaultFont(Context context) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                "Phetsarath_OT.ttf");
        replaceFont("MONOSPACE", regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        try {
            final Field StaticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            StaticField.setAccessible(true);
            StaticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
