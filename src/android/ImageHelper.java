package cordova.ryl.cordovalib;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Created by rongyile on 2017/10/26.
 */

public class ImageHelper {

    /**
     *
     * 需要跟debug 再确认一次
     按占用空间大小对图片压缩
     先压缩图片像素(等比压缩,保证图片宽度不小于300),
     再压缩质量

     压缩失败的情况还没写
     * @param bitMap
     * @param SIGNPIC_MAXSIZE
     * @param minWidth 图片最小宽度,当传入值小于等于0时,最小值取300
     * @return
     */
    public static byte[] getTagSizeBitMap(Bitmap bitMap, int SIGNPIC_MAXSIZE, int minWidth) {

        if (minWidth<=0){
            minWidth=500;
        }

        int size = getBitmapSize(bitMap);

        if (size > SIGNPIC_MAXSIZE) {


            double scal = Math.sqrt((double) SIGNPIC_MAXSIZE / (double) size);

            double tagWith = scal * bitMap.getWidth();
            double tagHeight = scal * bitMap.getHeight();

            if (tagWith < minWidth) {//等比压缩,保证图片宽度不小于 最小尺寸
                tagWith = minWidth;
                tagHeight = (tagWith / bitMap.getWidth()) * bitMap.getHeight();
            }


            Bitmap bitmapTemp = Bitmap.createScaledBitmap(bitMap, (int) tagWith, (int) tagHeight, false);

            //如果不行再对质量压缩

            int resizeBitmap = getBitmapSize(bitmapTemp);

            if (resizeBitmap > SIGNPIC_MAXSIZE) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try {
                    int opeions = 90;
                    while (resizeBitmap > SIGNPIC_MAXSIZE) {
                        if (opeions<=0){
                            break;
                        }
                        baos.reset();
                        bitmapTemp.compress(Bitmap.CompressFormat.JPEG, opeions, baos);
                        resizeBitmap = baos.toByteArray().length;
                        opeions -= 10;

                        Log.e("asd", "Bitmap2Bytes.length: " + baos.toByteArray().length);

                    }

                    Log.e("asd", "Bitmap2Bytes.length: " + baos.toByteArray().length);

                    baos.flush();
                    baos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }


                return baos.toByteArray();
            } else {
                return FileHelper.Bitmap2Bytes(bitmapTemp);
            }

        } else {
            return FileHelper.Bitmap2Bytes(bitMap);
        }

    }

    public static int getBitmapSize(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}
