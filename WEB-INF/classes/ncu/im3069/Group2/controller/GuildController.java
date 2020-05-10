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
import ncu.im3069.Group2.app.DelegatorBackHelper;
import ncu.im3069.Group2.app.Guild;
import ncu.im3069.Group2.app.GuildHelper;
import ncu.im3069.tools.JsonReader;

/**
 * Servlet implementation class GuildController
 */
@WebServlet("/api/guild.do")
public class GuildController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private GuildHelper gh =  GuildHelper.getHelper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuildController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JsonReader jsr = new JsonReader(request);
		
		JSONObject resp = new JSONObject();
		JSONObject query = null;
		
		query = gh.getAll();
		resp.put("status", "200");
        resp.put("message", "單筆委託資料取得成功!");
        resp.put("response", query);
        
        jsr.response(resp, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        //前端回傳的資料
		int id_delegator_person = Integer.valueOf(jso.getString("id_delegator_person"));
		String brief_description = jso.getString("brief_description");
		String detail_description = jso.getString("detail_description");
		int star_level = Integer.valueOf(jso.getString("star_level"));
		int tbDelegator_back_idtbDelegator_back = Integer.valueOf(jso.getString("tbDelegator_back_idtbDelegator_back"));
        int exp = Integer.valueOf(jso.getString("exp"));
        
        
		//更改委託後台status、exp
		DelegatorBackHelper dbh =  DelegatorBackHelper.getHelper();
		dbh.confirmCommission(exp, tbDelegator_back_idtbDelegator_back);
		
		//CommissionList新增資料
		CommissionHelper ch =  CommissionHelper.getHelper();
		Commission commission = new Commission(id_delegator_person, brief_description, detail_description, star_level, exp, tbDelegator_back_idtbDelegator_back);
		ch.insertCommission(commission);
		
		//Guild刪除資料
		GuildHelper gh = GuildHelper.getHelper();
		gh.deleteGuildCommission(tbDelegator_back_idtbDelegator_back);
		
		//回傳成功訊息
		JSONObject resp = new JSONObject();
		resp.put("status", "200");
		resp.put("message", "更新資料成功");
		jsr.response(resp, response);

	}

}
