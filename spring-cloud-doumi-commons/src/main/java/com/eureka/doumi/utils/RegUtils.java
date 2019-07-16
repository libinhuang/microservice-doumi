package com.eureka.doumi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//正则验证
public class RegUtils {
    private static String emailReg="^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
    private static String mobileReg="^[1]([3-9])[0-9]{9}$";
    private static String identityCodeReg="^\\w{18}$";

    /**
     * 验证邮箱
     * @param email
     * @return 匹配返回true
     */
    public static boolean checkEmail(String email) {
        Pattern pattern= Pattern.compile(emailReg);
        Matcher matcher= pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 验证电话号码
     * @param mobile
     * @return 匹配返回true
     */
    public static boolean checkMobile(String mobile) {
        Pattern pattern=Pattern.compile(mobileReg);
        Matcher matcher= pattern.matcher(mobile);
        return matcher.matches();
    }

    /**
     * 验证身份证号码
     * @param identityCode
     * @return 匹配返回true
     */
    public static boolean checkIdentityCode(String identityCode) {
        Pattern pattern=Pattern.compile(identityCodeReg);
        Matcher matcher= pattern.matcher(identityCode);
        return matcher.matches();
    }
}
