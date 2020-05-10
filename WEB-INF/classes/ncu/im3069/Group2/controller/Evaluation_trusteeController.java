package ncu.im3069.Group2.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

import ncu.im3069.Group2.app.Evaluation;
import ncu.im3069.Group2.app.Evaluation_trusteeHelper;
import ncu.im3069.tools.JsonReader;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/evaluation_trustee.do")

public class Evaluation_trusteeController extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    private Evaluation_trusteeHelper e_th =  Evaluation_trusteeHelper.getHelper();
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();

        String id = jso.getString("record_id");
        String evaluation = jso.getString("evaluation");
        System.out.println("5656"+id);
        Evaluation E = new Evaluation(id, evaluation);
        
        if(evaluation.isEmpty())
        {
            String resp = "{\"status\": \'400\', \"message\": \'欄位不能有空值\', \'response\': \'\'}";
            jsr.response(resp, response);
        }
        JSONObject data = e_th.create(E);
       
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "OK");
        resp.put("response", data);
        
        jsr.response(resp, response);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonReader jsr1 = new JsonReader(request);
		
        String id = jsr1.getParameter("member_ID");
        
        JSONObject query = e_th.getById_Trustee_History(id);
        
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "成功");
        resp.put("response", query);
        
        jsr1.response(resp, response);
	}
}