package ncu.im3069.Group2.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

import ncu.im3069.Group2.app.Member;
import ncu.im3069.Group2.app.MemberHelper;
import ncu.im3069.tools.JsonReader;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/membercontroller.do")

public class MemberController extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    private MemberHelper mh =  MemberHelper.getHelper();
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        String email = jso.getString("email");
        String password = jso.getString("password");
        String name = jso.getString("name");
        int permission=0;
        int level=0;
        int member_exp=0;
        String img="";
        
        Member m = new Member(email, password, name, permission, level, member_exp, img);
        
        if(email.isEmpty() || password.isEmpty() || name.isEmpty())
        {
            String resp = "{\"status\": \'400\', \"message\": \'欄位不能有空值\', \'response\': \'\'}";
            jsr.response(resp, response);
        }
        else if (!mh.checkDuplicate(m)) 
        {
            JSONObject data = mh.create(m);
            
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "成功! 註冊會員資料...");
            resp.put("response", data);
            
            jsr.response(resp, response);
        }
        else 
        {
            String resp = "{\"status\": \'400\', \"message\": \'新增帳號失敗，此E-Mail帳號重複！\', \'response\': \'\'}";
            jsr.response(resp, response);
        }
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        int idtbMember = jso.getInt("idtbMember");
        String email = jso.getString("email");
        String password = jso.getString("password");
        String name = jso.getString("name");
        int permission = jso.getInt("permission");
        int level = jso.getInt("level");
        int member_exp = jso.getInt("member_exp");
        String img = jso.getString("img");

        Member m = new Member(idtbMember, email, password, name, permission, level, member_exp, img);
               
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "成功! 更新會員資料...");
        
        jsr.response(resp, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonReader jsr1 = new JsonReader(request);
        String email = jsr1.getParameter("email");
        String password = jsr1.getParameter("password");
        
        JSONObject query = mh.getmember_info(email,password);
        
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "成功");
        resp.put("response", query);
        
        jsr1.response(resp, response);
	}
}