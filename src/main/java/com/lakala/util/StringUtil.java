package com.lakala.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: StringUtil</p>
 * @author lixiaojie lixiaojie@lakala.com
 * @version V1.0
 * @Package com.lakala.util
 * <p>Description: 字符串处理工具类</p>
 * @date 2018/1/24 17:14
 */
public class StringUtil {

    /**
     * <p>Title: isNull</p>
     * <p>Description: 判断字符串是否为null、“ ”、“null”</p>
     * ${tags} return boolean
     */
    public static boolean isNull(String obj) {
        if (obj == null){
            return true;
        }else if (obj.toString().trim().equals("")){
            return true;
        }else if(obj.toString().trim().toLowerCase().equals("null")){
            return true;
        }
        return false;
    }

    /**
     * <p>Title: isNumber</p>
     * <p>Description: 正则验证是否是数字</p>
     * ${tags} return boolean
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[+-]?[0-9]+[0-9]*(\\.[0-9]+)?");
        Matcher match = pattern.matcher(str);
        return match.matches();
    }

    /**
     * <p>Title: longToBytes</p>
     * <p>Description: 将一个长整数转换位字节数组(8个字节)，b[0]存储高位字符，大端 </p>
     * ${tags} 代表长整数的字节数组
     */
    public static byte[] longToBytes(long l) {
        byte[] b = new byte[8];
        b[0] = (byte) (l >>> 56);
        b[1] = (byte) (l >>> 48);
        b[2] = (byte) (l >>> 40);
        b[3] = (byte) (l >>> 32);
        b[4] = (byte) (l >>> 24);
        b[5] = (byte) (l >>> 16);
        b[6] = (byte) (l >>> 8);
        b[7] = (byte) (l);
        return b;
    }
}
