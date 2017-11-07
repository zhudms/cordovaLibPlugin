package cordova.ryl.cordovalib;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.net.Uri;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2017/4/13.
 */

public class FileHelper {

    public static final String SDCARD_STORAGE_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "ryl" + File.separator;

    public static final String SDCARD_STORAGE_RECORNITIONROOT = SDCARD_STORAGE_ROOT
            + "liveness" + File.separator;


    public static final String SD_ID_ROOT = SDCARD_STORAGE_ROOT + "ID" + File.separator;
	 public static final String SD_SIGN_ROOT = SDCARD_STORAGE_ROOT + "SIGN" + File.separator;
    // public static final String SD_ID_FRONT = SD_ID_ROOT + "front.png";
    // public static final String SD_ID_BACK = SD_ID_ROOT + "back.png";

    public static final int SAVE_FILE_FINISH = 0;
    public static final int SAVE_FILE_SUCCESS = 1;
    public static final int SAVE_FILE_FAIL = -1;


    public static void saveBitmapToSD(final String path, final SoftHandle handle, final Bitmap date) {
        byte[] bytes = Bitmap2Bytes(date);
        saveByteToSD(path, handle, bytes);

    }

    /**
     * 使用handle将path回传，作为图片的唯一TAG
     * <p>
     * 剩余空间判断（还没做）
     *
     * @param path
     * @param handle
     * @param date
     */
    public static void saveByteToSD(final String path, final SoftHandle handle, final byte[] date) {

     if (date == null||path.isEmpty()) {
          sendResult(path, SAVE_FILE_FAIL, "path or date is null", handle);
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {//这里需要加锁，不会写(这样实现不好)
                try {

                    File file = new File(path);
                    String parentFilePath = file.getParentFile().getAbsolutePath() + File.separator;
                    File parentFile = new File(parentFilePath);
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }

                    if (file.exists()) {
                        file.delete();
                       
                    }
                        file.createNewFile();
                  
                    FileOutputStream fos = null;
                    fos = new FileOutputStream(file);
                    fos.write(date);
                    fos.flush();
                    fos.close();

                    sendResult(path, SAVE_FILE_SUCCESS, null, handle);
                } catch (Exception e) {
                    e.printStackTrace();
                    sendResult(path, SAVE_FILE_FAIL, e.toString(), handle);
                }


            }

        }).start();

    }

    //bitmap转byte
    public static byte[] Bitmap2Bytes(Bitmap bm) {
             try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 80, baos);
//        Log.e("asd", "Bitmap2Bytes.length: "+baos.toByteArray().length);
            baos.flush();

            baos.close();

            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Message 参数:
     * what:类别标示（是什么类别完成（此处为文件保存））
     * arg1:结果：成/败
     *
     * @param path
     * @param result
     * @param errorMessage
     * @param mHandle
     */
    public static void sendResult(String path, int result, String errorMessage, Handler mHandle) {

        Message message = Message.obtain();

        message.what = SAVE_FILE_FINISH;

        if (result == SAVE_FILE_SUCCESS) {

            message.arg1 = SAVE_FILE_SUCCESS;
            message.obj = path;
        } else if (result == SAVE_FILE_FAIL) {

            message.arg1 = SAVE_FILE_FAIL;
            message.obj = errorMessage;

        }

        mHandle.sendMessage(message);

    }

    public static String getFileUriString(String filePath){
      return   Uri.fromFile(new File(filePath)).toString();
    }
}
