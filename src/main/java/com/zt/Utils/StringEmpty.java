package com.zt.Utils;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/10/9 20:06
 */
public class StringEmpty {
    public static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
    public static boolean isNotExistEmpty(String[] strs){
        for (String str : strs) {
            if(isEmpty(str)){
                return false;
            }
        }
        return true;
    }
    public static boolean isError(Object[] objects){
        for (Object object : objects) {
            if (object == "error"){
                return false;
            }
        }
        return true;
    }
}
