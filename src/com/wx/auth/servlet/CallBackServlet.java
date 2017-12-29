package com.wx.auth.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.auth.util.AuthUtil;

import net.sf.json.JSONObject;

@WebServlet("/callBack")
public class CallBackServlet extends HttpServlet{
	
	private String dbUrl = "jdbc:mysql://localhost:3306/auth";
    private String driverName = "com.mysql.jdbc.Driver";
    private String dbUserName = "root";
    private String dbPassword = "qaz518";
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    /*
     * ���ݿ���ֶΣ�id��account��password��openid
     */
    
    @Override
    public void init() throws ServletException {
    	try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			                               throws ServletException, IOException {
		//1. ��ȡ΢�Żص������е�code
        System.out.println("in WxAuth/callBack");
        
        String code = req.getParameter("code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
                    + "appid=" + AuthUtil.APPID
                    + "&secret=" + AuthUtil.APPSECRET
                    + "&code=" + code
                    + "&grant_type=authorization_code";
		
        //2. ��΢�ŷ������󣬴���APPSCECRET��code����ȡopenid��access_toekn
        JSONObject jsonObject = AuthUtil.doGetJson(url);
        
        String openid = jsonObject.getString("openid");
        String token = jsonObject.getString("access_token");
        
       //4. ��ȡ�û���Ϣ
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?"
                        + "access_token=" + token
                        + "&openid=" + openid
                        + "&lang=zh_CN";
        JSONObject userInfo = AuthUtil.doGetJson(infoUrl);
        
        System.out.println("��ȡ�����û���ϢΪ:"+userInfo);
		
      //1. ʹ��΢���û���Ϣֱ�ӵ�¼������ע��Ͱ�
//      req.setAttribute("info", userInfo);
//      req.getRequestDispatcher("/index1.jsp").forward(req, resp);
        
        //2. ��΢���뵱ǰ��ϵͳ�˺Ű�
/*        try {
            String nickName = getNickName(openid);
            if (!"".equals(nickName)){
                //�Ѱ󶨡�ֱ����ת��¼�ɹ�ҳ��
                req.setAttribute("nickname", nickName);
                req.getRequestDispatcher("/index2.jsp").forward(req, resp);
            } else {
//                δ�󶨡� ��ת����ҳ�棬Ҫ���û������˻�����
//                �������ݿ�openid
                req.setAttribute("openid", openid);
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
	}
	
	 @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account =req.getParameter("account");
        String password =req.getParameter("password");
        String openid =req.getParameter("openid");
        try {
            int temp = updateUser(openid, account, password);
            if(temp>0) {
                System.out.println("�˺Ű󶨳ɹ�");
            } else {
                System.out.println("�˺Ű�ʧ��");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
	
	//��ѯ��ǰ�˺���Ϣ
	public String getNickName(String openid) throws SQLException {
        String nickName = "";
        conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        String sql = "select nickname from user where openid=?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, openid);
        rs = ps.executeQuery();
        while (rs.next()) {
            nickName = rs.getString("NICKNAME");
        }
        rs.close();
        ps.close();
        conn.close();
        return nickName;
    }
	
	
	public int updateUser(String openid, String account, String password) throws SQLException {
        conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        String sql = "update user set OPENID=? where ACCOUNT=? and PASSWORD=?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, openid);
        ps.setString(2, account);
        ps.setString(3, password);
        int temp = ps.executeUpdate();
        rs.close();
        ps.close();
        conn.close();
        return temp;
    }
*/
 }
}











