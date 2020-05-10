package ncu.im3069.Group2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ncu.im3069.Group2.app.Commission;
import ncu.im3069.Group2.app.CommissionHelper;
import ncu.im3069.Group2.app.MemberHelper;
import ncu.im3069.Group2.app.TrusteeBack;
import ncu.im3069.tools.JsonReader;

/**
 * Servlet implementation class CommissionController
 */
@WebServlet("/api/commission.do")
public class CommissionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CommissionHelper ch =  CommissionHelper.getHelper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommissionController() {
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
        String id_commission = jsr.getParameter("id_commission");
		
		JSONObject resp = new JSONObject();
		JSONObject query = null;
		
		//如果字串不為空代表請求選單一委託的資料
		if(!id_commission.isEmpty()) {
			query = ch.getByIdCommission(id_commission);
			resp.put("status", "200");
	        resp.put("message", "委託資料取得成功!");
	        resp.put("response", query);
		}else {
			query = ch.getAll();
			resp.put("status", "200");
	        resp.put("message", "所有委託資料取得成功!");
	        resp.put("response", query);
		}
       
       

        jsr.response(resp, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        //誰申請
        int id_applicant = jso.getInt("id_applicant");
        //申請哪個委託的資料
        int id_delegator = jso.getInt("id_delegator");
        String brief_description = jso.getString("brief_description");
        String detail_description = jso.getString("detail_description");
        int star_level = jso.getInt("star_level");
        int exp = jso.getInt("exp");
        int tbDelegator_back_idtbDelegator_back = jso.getInt("tbDelegator_back_idtbDelegator_back");
        String img = jso.getString("img");
        String name = jso.getString("name");
        
        /** 將每一筆商品資料產生一名新Commission物件 */
        Commission commission = new Commission(id_delegator, brief_description,detail_description,star_level,exp, img, name, tbDelegator_back_idtbDelegator_back);
        
        //判斷是否已經申請過此委託
        if(!ch.isApplying(id_applicant, tbDelegator_back_idtbDelegator_back)) {
        	/**更新後端資料**/
        	System.out.println("還未申請");
        	
            //更新受託後台資料
            boolean trusteeBackStatus = ch.applyingCommission(id_applicant, commission);
            //更新applyingTable資料
            boolean applyingStatus = ch.insertApplyingTable(id_applicant, tbDelegator_back_idtbDelegator_back);
            
        	JSONObject resp = new JSONObject();
    		resp.put("status", "200");
    		resp.put("message", "更新資料成功");
    		resp.put("trusteeBackStatus", trusteeBackStatus);
    		resp.put("applyingStatus", applyingStatus);
    		jsr.response(resp, response);
    		
        }else {
        	System.out.println("申請過了");
        	JSONObject resp = new JSONObject();
    		resp.put("status", "300");
    		resp.put("message", "委託已申請過了");
    		jsr.response(resp, response);
        }
        
        
		
	}
	
	

}
