package lib.kalu.czxing.jni;

import android.graphics.Bitmap;
import android.graphics.Color;

import androidx.annotation.Keep;

@Keep
public class BarcodeWriter {

    private BarcodeWriter() {
    }

    public static final class Holder {
        private static BarcodeWriter instance = new BarcodeWriter();
    }

    public static BarcodeWriter getInstance() {
        return BarcodeWriter.Holder.instance;
    }

    /**
     * 生成二维码
     *
     * @param text 要生成的文本
     * @param size 边长
     * @return bitmap二维码
     */
    public Bitmap write(String text, int size) {
        return write(text, size, Color.BLACK);
    }

    /**
     * 生成二维码
     *
     * @param text  要生成的文本
     * @param size  边长
     * @param color 要生成的二维码颜色
     * @return bitmap二维码
     */
    public Bitmap write(String text, int size, int color) {
        return write(text, size, color, null);
    }

    /**
     * 生成带logo的二维码
     *
     * @param text  要生成的文本
     * @param size  图片边长
     * @param color 要生成的二维码颜色
     * @param logo  放在中间的logo
     * @return bitmap二维码
     */
    public Bitmap write(String text, int size, int color, Bitmap logo) {
        return write(text, size, size, color, BarcodeFormat.QRCode, logo);
    }

    /**
     * 生成一维码
     *
     * @param text   要生成的文字（不支持中文）
     * @param width  图片宽
     * @param height 图片高
     * @param format 一维码格式
     * @return 一维码bitmap
     */
    public Bitmap writeBarCode(String text, int width, int height, BarcodeFormat format) {
        return write(text, width, height, Color.BLACK, format, null);
    }

    /**
     * 生成图片
     *
     * @param text   要生成的文本
     * @param width  图片宽
     * @param height 图片高
     * @param color  要生成的二维码颜色
     * @param format 要生成的条码格式
     * @param logo   放在中间的logo
     * @return bitmap二维码
     */
    public Bitmap write(String text, int width, int height, int color, BarcodeFormat format, Bitmap logo) {
        Object[] result = new Object[1];
        int resultCode = Native.getInstance().writeBytes(text, width, height, color, format.name(), result);
        Bitmap bitmap = null;
        if (resultCode > -1) {
            int[] pixels = (int[]) result[0];
            bitmap = Bitmap.createBitmap(pixels, width, height, Bitmap.Config.ARGB_8888);
            // 添加logo
            if (logo != null) {
                bitmap = BitmapUtil.addLogoInQRCode(bitmap, logo);
            }
        }
        return bitmap;
    }
}
