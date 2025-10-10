package cn.sylg.wutilsx;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @program: SmartWarehouse
 * @description: 压缩解压JSON GZIP工具
 * @author: Wand Di
 * @create: 2024-03-05 9:35
 */
public class GzipUtils {

    private static final String TAG = "GzipUtils";

    /**
     * 压缩byte数组
     * todo 原使用1024  android生成与解析均可以  js端报undefine 现修改为10240
     */
    public static byte[] uncompress(byte[] inputByte) {
        byte[] output = new byte[10240];
        Deflater deflater = new Deflater(9, true);
        deflater.setInput(inputByte);
        deflater.finish();
        int compressedSize = deflater.deflate(output);
        deflater.end();
        return java.util.Arrays.copyOf(output, compressedSize);
    }

    /**
     * 解压缩为字符串
     * todo 注意如果传递过来的带中文 可以使用URI将中文转为unicode
     */
    public static String decompressToString(byte[] compressedData) {
        byte[] outinput;
        Inflater decompresser = new Inflater(true); //设置为Ture
        decompresser.reset();
        decompresser.setInput(compressedData);
        ByteArrayOutputStream out = new ByteArrayOutputStream(compressedData.length);
        try {
            byte[] buf = new byte[1024];
            while (!decompresser.finished()) {
                int i = decompresser.inflate(buf);
                out.write(buf, 0, i);
            }
            outinput = out.toByteArray();
        } catch (Exception e) {
            outinput = compressedData;
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        decompresser.end();
        return new String(outinput);
    }

}
