package ncu.im3069.Group2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ncu.im3069.Group2.app.TrusteeBackHelper;
import ncu.im3069.tools.JsonReader;

/**
 * Servlet implementation class TrusteeBackController
 */
@WebServlet("/api/trustee_back.do")
public class TrusteeBackController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TrusteeBackHelper tbh =  TrusteeBackHelper.getHelper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrusteeBackController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        /** 若直接透過前端AJAX之data以key=value之字串方式進行傳遞參數，可以直接由此方法取回資料 */
        String id = jsr.getParameter("id");
        String id_commission = jsr.getParameter("id_commission");
        
		JSONObject resp = new JSONObject();
		JSONObject query = null;
		if(!id_commission.isEmpty()) {
			query = tbh.getCommissionByID(id_commission);
			resp.put("status", "200");
	        resp.put("message", "單筆委託資料取得成功!");
	        resp.put("response", query);
			
		}else {
			query = tbh.getAll(id);
			resp.put("status", "200");
	        resp.put("message", "所有委託資料取得成功!");
	        resp.put("response", query);
		}

        jsr.response(resp, response);
	}

}
