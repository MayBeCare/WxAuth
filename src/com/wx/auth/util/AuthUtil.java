package com.wx.auth.util;

import net.sf.json.JSONObject;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class AuthUtil {
	
	public static final String APPID = "wx811a35ce636e0234";
    public static final String APPSECRET = "71ecefb123d08f7097dea02cf2bd05a3";
    
	public static JSONObject doGetJson(String url) throws IOException {
        JSONObject jsonObject = null;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);        //发送请求
        HttpEntity entity = response.getEntity();               //获取结果
        if(entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println(result);
            jsonObject = JSONObject.fromObject(result);
        }
        httpGet.releaseConnection();   //释放连接
        return jsonObject;
    }

}
