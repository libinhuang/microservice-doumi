package com.eureka.doumi.utils;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.context.annotation.PropertySource;

//读取配置信息
@PropertySource("classpath:/aliyun.properties")
public class SMSUtils {
    private static String regionId= ConfigManager.getProperty("regionId");
    private static String accessKeyId= ConfigManager.getProperty("accessKeyId");
    private static String secret= ConfigManager.getProperty("secret");
    private static String domain= ConfigManager.getProperty("domain");
    private static String version= ConfigManager.getProperty("version");
    private static String action= ConfigManager.getProperty("action");
    private static String signName= ConfigManager.getProperty("signName");
    private static String templateCode=ConfigManager.getProperty("templateCode");

    public static boolean sendSms(String phone,String params){
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion(version);
        request.setAction(action);
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", params);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(JSON.toJSONString(response));
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
