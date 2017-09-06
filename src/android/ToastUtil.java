package cordova.ryl.cordovalib;


import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/4/13.
 */

public class ToastUtil {

    public static void toast(Context context, String message) {
        if (context == null) {
            return;
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, int message) {
        if (context == null) {
            return;
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
