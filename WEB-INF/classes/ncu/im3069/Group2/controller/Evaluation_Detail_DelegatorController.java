package ncu.im3069.Group2.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

import ncu.im3069.Group2.app.Evaluation;
import ncu.im3069.Group2.app.Evaluation_delegatorHelper;
import ncu.im3069.tools.JsonReader;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/evaluation_detail_delegator.do")

public class Evaluation_Detail_DelegatorController extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    private Evaluation_delegatorHelper e_dh =  Evaluation_delegatorHelper.getHelper();
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonReader jsr1 = new JsonReader(request);
		
        String id = jsr1.getParameter("member_dID");
        
        JSONObject query = e_dh.getById_Delegator_History_Detail(id);
        
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "成功");
        resp.put("response", query);
        
        jsr1.response(resp, response);
	}
}