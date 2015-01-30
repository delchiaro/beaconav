package micc.beaconav.gui.animationHelper;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by nagash on 17/01/15.
 */
public class DpHelper
{
    private Context context;
    public DpHelper(Context context)
    {
        this.context = context;
    }
    public int dpToPx(int dimensioninDp)
    {
        return dpToPx(dimensioninDp, this.context);
    }

    public static final int dpToPx(int dimensionInDp, Context context)
    {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimensionInDp, context.getResources().getDisplayMetrics());
        return px;
    }
}
