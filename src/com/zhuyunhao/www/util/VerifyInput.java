package com.zhuyunhao.www.util;

import java.util.regex.Pattern;

/**
 * 正则校验输入数据工具类
 * @author 朱云皓
 */
public class VerifyInput {

    /**
     * 检查指定长度字符串格式
     * @param input 输入的字符串
     * @param minLength 最小长度
     * @param maxLength 最大长度
     * @return true则格式正确
     */
    public static boolean VerifyString(String input,int minLength,int maxLength){
        String pattern = String.format("^.{%s,%s}$",minLength,maxLength);
        if(input ==null){
            if(minLength == 0){
                input = "";
            }else {
                return false;
            }
        }
        return Pattern.matches(pattern,input);
    }

    /**
     * 检查手机号是否合法
     * @param telephone 手机号
     * @return true则合法
     */
    public static boolean VerifyPhone(String telephone){
        String pattern = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
        return Pattern.matches(pattern,telephone);
    }

    /**
     * 检查是否是非负浮点数
     * @param number 数字
     * @return true则合法
     */
    public static boolean VerifyFloat(String number){
        String pattern = "^\\d+(\\.\\d+)?$";
        return Pattern.matches(pattern,number);
    }

    /**
     * 检查是否是非负整数
     * @param number 数字
     * @return true则合法
     */
    public static boolean VerifyInt(String number){
        String pattern = "^\\d+$";
        return Pattern.matches(pattern,number);
    }
}
