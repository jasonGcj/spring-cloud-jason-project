package leetcode;

import io.lettuce.core.codec.CRC16;

/**
 * @ClassName Utils
 * @Description TODO
 * @Author GCJ
 * @Date 2021/2/2 15:49
 */
public class Utils {

    /**
     * 十六进制的字符串转换为byte数组
     *
     * @param hex16Str
     *            十六进制字符串
     * @return byte数组
     */
    public static byte[] conver16HexToByte(String hex16Str) {
        char[] c = hex16Str.toCharArray();
        byte[] b = new byte[c.length / 2];
        for (int i = 0; i < b.length; i++) {
            int pos = i * 2;
            b[i] = (byte) ("0123456789ABCDEF".indexOf(c[pos]) << 4 | "0123456789ABCDEF"
                    .indexOf(c[pos + 1]));
            //System.out.print(b[i] + ",");
        }
        return b;
    }

    /**
     * 字节数组转16进制
     *
     * @param bytes
     *            需要转换的byte数组
     * @return 转换后的Hex字符串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString().toUpperCase();
    }


    /**
     * CRC校验码
     *
     * @param hex16Str   十六进制字符串
     * @param format 是否需要高低位互换，佳岚的需要高低位互换
     * @return    CRC校验码
     */
    public static String makeCRC(String hex16Str,boolean format) {
        byte[] bytes = conver16HexToByte(hex16Str);
        int crc = 0x0000ffff;
        for (int i = 0; i < bytes.length; i++) {
            crc ^= ((int) bytes[i] & 0x000000ff);
            for (int j = 0; j < 8; j++) {
                if ((crc & 0x00000001) != 0) {
                    crc >>= 1;
                    crc ^= 0x0000a001;
                } else {
                    crc >>= 1;
                }
            }
        }
        // 高低位互换，输出符合相关工具对Modbus CRC16的运算
        if(format){
            crc = ((crc & 0xff00) >> 8) | ((crc & 0x00ff) << 8);
        }
        return String.format("%04X", crc);
    }

    public static void main(String[] args) {
        String str ="d71438393836303631393134303030393531383036370010000d01050001064e4254473031020";
        System.out.println(makeCRC(bytesToHex(conver16HexToByte(str)),true));
    }
}
