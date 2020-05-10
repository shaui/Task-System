package ncu.im3069.Group2.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

import ncu.im3069.Group2.app.Evaluation;
import ncu.im3069.Group2.app.Evaluation_trusteeHelper;
import ncu.im3069.tools.JsonReader;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/evaluation_detail_trustee.do")

public class Evaluation_Detail_TrusteeController extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    private Evaluation_trusteeHelper e_dh =  Evaluation_trusteeHelper.getHelper();
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonReader jsr1 = new JsonReader(request);
		
        String id = jsr1.getParameter("member_tID");
        
        JSONObject query = e_dh.getById_Trustee_History_Detail(id);
        
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "成功");
        resp.put("response", query);
        
        jsr1.response(resp, response);
	}
}