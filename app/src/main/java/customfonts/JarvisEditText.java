package customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Martin on 2017-01-27 for YesJarvis.
 */

public class JarvisEditText extends EditText {
    public JarvisEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public JarvisEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public JarvisEditText(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Quicksand-Regular.ttf");
            setTypeface(tf);
        }
    }
}
