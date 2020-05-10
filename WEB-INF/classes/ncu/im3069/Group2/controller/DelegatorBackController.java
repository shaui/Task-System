package ncu.im3069.Group2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ncu.im3069.Group2.app.CommissionHelper;
import ncu.im3069.Group2.app.DelegatorBack;
import ncu.im3069.Group2.app.DelegatorBackHelper;
import ncu.im3069.Group2.app.Guild;
import ncu.im3069.Group2.app.MemberHelper;
import ncu.im3069.Group2.app.TrusteeBackHelper;
import ncu.im3069.tools.JsonReader;

/**
 * Servlet implementation class TrusteeBackController
 */
@WebServlet("/api/delegator_back.do")
public class DelegatorBackController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DelegatorBackHelper dbh =  DelegatorBackHelper.getHelper();
	private TrusteeBackHelper tbh =  TrusteeBackHelper.getHelper();
	private CommissionHelper ch =  CommissionHelper.getHelper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelegatorBackController() {
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
        String applying = jsr.getParameter("applying");
        
        System.out.println("applying:" + applying);
		JSONObject resp = new JSONObject();
		JSONObject query = null;
		//取得委託資料
		if(!id_commission.isEmpty()) {
			query = dbh.getCommissionByID(id, id_commission);
			resp.put("status", "200");
	        resp.put("message", "單筆委託資料取得成功!");
	        resp.put("response", query);
			
		}else {
			query = dbh.getAll(id);
			resp.put("status", "200");
	        resp.put("message", "所有委託資料取得成功!");
	        resp.put("response", query);
		}
		
		//取得申請人資料
		if(!applying.isEmpty()) {
			query = dbh.getApplying(id_commission);
	        resp.put("applying", query);
		}

        jsr.response(resp, response);
	}
	
	//修改DB
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        String id_commission = jso.getString("id_commission");
        String id_trustee = jso.getString("id_trustee");
        
        System.out.println("id_commission:" + id_commission);
        System.out.println("id_trustee:" + id_trustee);
        
        updateDB(id_commission, id_trustee);
	  
		JSONObject resp = new JSONObject();
		resp.put("status", "200");
		resp.put("message", "更新資料成功");
		jsr.response(resp, response);	

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        String brief_description = jso.getString("brief_description");
        String detail_description = jso.getString("detail_description");
        int star_level = jso.getInt("star_level");
        int submit_status = 0;
        int exp = 0;
        int tbMember_idtbMember = jso.getInt("tbMember_idtbMember");
        
        //提交紀錄在委託後台中
        DelegatorBack delegatorBack = new DelegatorBack(brief_description, detail_description, star_level, submit_status, exp, tbMember_idtbMember);
        int commission_PK = dbh.submitCommission(delegatorBack);
        System.out.println(commission_PK);
        
        //把資料放進公會表中
        Guild guild = new Guild(tbMember_idtbMember, brief_description, detail_description, star_level, commission_PK);
        boolean isSuccess = dbh.insertGuild(guild);
        
		JSONObject resp = new JSONObject();
		resp.put("status", "200");
		resp.put("message", "更新資料成功");
		resp.put("submitStatus", isSuccess);
		jsr.response(resp, response);		
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        int idtbDelegator_back = jso.getInt("idtbDelegator_back");
        int tbMember_idtbMember = jso.getInt("tbMember_idtbMember");
        String brief_description =  jso.getString("brief_description");
        String detail_description = jso.getString("detail_description");
        int star_level = jso.getInt("star_level");
        int exp = jso.getInt("exp");
        //取得委託者和受託地的ID
        int id_Delegator = tbMember_idtbMember;
        int id_trustee = tbh.getMemberIdByCommission(idtbDelegator_back);
        
        //塞到2個人的歷程記錄
        dbh.insertHistory(id_Delegator, id_trustee, brief_description, detail_description, star_level, exp, id_Delegator);
        dbh.insertHistory(id_Delegator, id_trustee, brief_description, detail_description, star_level, exp, id_trustee);
        
        //刪除2人後台的資料
        dbh.deleteDelegatorCompleteCommission(idtbDelegator_back);
        dbh.deleteTrusteeCompleteCommission(idtbDelegator_back);
        
		JSONObject resp = new JSONObject();
		resp.put("status", "200");
		resp.put("message", "更新資料成功");
		jsr.response(resp, response);		
	}
	
	protected void updateDB(String id_commission, String id_trustee) {
		//委託後台status改成2
	  	dbh.updateStatus(id_commission, "2");
	  	//tbTrustee_back被選中的status改成1
	  	tbh.updateStatus(id_trustee, id_commission, "1");
	  	//tbTrustee_back沒被選中的資料刪除
	  	tbh.deleteCommissionByID(id_commission);
	  	//tbApplying中的資料刪除
	  	dbh.deleteApplyingByID(id_commission);
	 	//tbCommission_list中的資料刪除
	  	ch.deleteCommissionByID(id_commission);
	}

}
