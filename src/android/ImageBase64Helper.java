package cordova.ryl.cordovalib;

import android.graphics.Bitmap;
import android.os.Message;
import android.util.Base64;

import static cordova.ryl.cordovalib.ActivityResultHelper.GETBASE64_FAIL;
import static cordova.ryl.cordovalib.ActivityResultHelper.GETBASE64_FINISH;
import static cordova.ryl.cordovalib.ActivityResultHelper.GETBASE64_SUCCESS;

/**
 * Created by Administrator on 2017/4/25.
 */

public class ImageBase64Helper {


    /**
     * 此方法未做数据验证，在传达图片时必然失败，因为有1M限制
     *
     * @param bitmap
     * @param handle
     */
    public static final void getBase64Image(Bitmap bitmap, SoftHandle handle) {


        byte[] bytes = FileHelper.Bitmap2Bytes(bitmap);

        getBase64Image(bytes, handle);

    }

    public static final void getBase64Image(byte[] bytes, SoftHandle handle) {
        try {


            if (bytes == null) {
                sendMessage(GETBASE64_FINISH, GETBASE64_FAIL, "bitmap is null", handle);
            }

            String imageMessage;
            imageMessage = Base64.encodeToString(bytes, Base64.NO_WRAP);
            sendMessage(GETBASE64_FINISH, GETBASE64_SUCCESS, imageMessage, handle);

        } catch (Exception e) {
            sendMessage(GETBASE64_FINISH, GETBASE64_FAIL, e.toString(), handle);

        }

    }


    public static final void sendMessage(int type, int result, String messgae, SoftHandle handle) {

        Message message = Message.obtain();

        message.what = type;
        message.arg1 = result;
        message.obj = messgae;

        handle.sendMessage(message);
    }
}
