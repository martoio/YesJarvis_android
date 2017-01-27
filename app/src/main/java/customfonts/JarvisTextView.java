package customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Martin on 2017-01-26 for YesJarvis.
 */

public class JarvisTextView extends TextView {
    public JarvisTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public JarvisTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public JarvisTextView(Context context) {
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
