package com.wx.auth.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.auth.util.AuthUtil;

@WebServlet("/wxLogin")
public class LoginServlet extends HttpServlet {
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("in /WxAuth/wxLogin");

        String backUrl = "http://localhost:8080/WxAuth/callBack";
//        String backUrl = "http://atwjsw.s1.natapp.cc/callBack";           //能够在公网上访问(花生壳银映射)

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + AuthUtil.APPID
                + "&redirect_uri=" + URLEncoder.encode(backUrl)
                + "&response_type=code"
                + "&scope=snsapi_userinfo"
                + "&state=STATE"
                + "#wechat_redirect";
//        重定向用户请求到微信授权URL
        resp.sendRedirect(url);
//        super.doGet(req, resp);
    }

}







