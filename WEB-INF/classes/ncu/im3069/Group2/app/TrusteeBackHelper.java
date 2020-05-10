package ncu.im3069.Group2.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import ncu.im3069.Group2.util.DBMgr;

public class TrusteeBackHelper {

	private TrusteeBackHelper() {
		
	}
	
	private static TrusteeBackHelper tbh;
	private Connection conn = null;
    private PreparedStatement pres = null;
    
    public static TrusteeBackHelper getHelper() {
        /** Singleton檢查是否已經有ProductHelper物件，若無則new一個，若有則直接回傳 */
        if(tbh == null) tbh = new TrusteeBackHelper();
        
        return tbh;
    }
    
    public JSONObject getAll(String id) {
        /** 新建一個 Product 物件之 m 變數，用於紀錄每一位查詢回之商品資料 */
    	TrusteeBack tb = null;
        /** 用於儲存所有檢索回之商品，以JSONArray方式儲存 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();

        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT * FROM `sa_group6`.`tbtrustee_back` WHERE `tbMember_idtbMember` = ?";
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            while(rs.next()) {
                
                /** 將 ResultSet 之資料取出 */
                int idtbTrustee_back = rs.getInt("idtbTrustee_back");
                int id_member = rs.getInt("id_member");
                String brief_description = rs.getString("brief_description");
                String detail_description = rs.getString("detail_description");
                int star_level = rs.getInt("star_level");
                int accept_status = rs.getInt("accept_status");
                int exp = rs.getInt("exp");
                int tbMember_idtbMember = rs.getInt("tbMember_idtbMember");
                
                MemberHelper mh = MemberHelper.getHelper();
                String img = mh.getImgByID(String.valueOf(id_member));
                String name = mh.getNameByID(String.valueOf(id_member));

                
                /** 將每一筆商品資料產生一名新Product物件 */
                tb = new TrusteeBack(idtbTrustee_back, id_member, brief_description,detail_description,star_level,accept_status, exp,tbMember_idtbMember, img, name);
                /** 取出該項商品之資料並封裝至 JSONsonArray 內 */
                jsa.put(tb.getData());
            }

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }
        
        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);
        
        /** 將SQL指令、花費時間、影響行數與所有會員資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
    
    public JSONObject getCommissionByID(String id_commission) {
    	/** 新建一個 Product 物件之 m 變數，用於紀錄每一位查詢回之商品資料 */
    	TrusteeBack tb = null;
        /** 用於儲存所有檢索回之商品，以JSONArray方式儲存 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT * FROM `sa_group6`.`tbtrustee_back` WHERE `idtbTrustee_back` = ?";
            
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            //id_commission是外面傳進來的參數
            pres.setString(1, id_commission);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            while(rs.next()) {
                
                /** 將 ResultSet 之資料取出 */
                int idtbTrustee_back = rs.getInt("idtbTrustee_back");
                int id_member = rs.getInt("id_member");
                String brief_description = rs.getString("brief_description");
                String detail_description = rs.getString("detail_description");
                int star_level = rs.getInt("star_level");
                int accept_status = rs.getInt("accept_status");
                int exp = rs.getInt("exp");
                int tbMember_idtbMember = rs.getInt("tbMember_idtbMember");
                
                MemberHelper mh = MemberHelper.getHelper();
                String img = mh.getImgByID(String.valueOf(id_member));
                String name = mh.getNameByID(String.valueOf(id_member));

                
                /** 將每一筆商品資料產生一名新Product物件 */
                tb = new TrusteeBack(idtbTrustee_back, id_member, brief_description,detail_description,star_level,accept_status, exp,tbMember_idtbMember, img, name);
                /** 取出該項商品之資料並封裝至 JSONsonArray 內 */
                jsa.put(tb.getData());
            }
            

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }
        
        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);
        
        /** 將SQL指令、花費時間、影響行數與所有會員資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
    
    public void updateStatus(String id_trustee, String id_commission, String status) {
    	
    	/** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";

    	ResultSet rs = null;
    	
    	try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "Update `sa_group6`.`tbtrustee_back` SET `accept_status` = ? WHERE `tbMember_idtbMember` = ? AND `id_commission` = ?";
            
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            //id_commission是外面傳進來的參數
            pres.setString(1, status);
            pres.setString(2, id_trustee);
            pres.setString(3, id_commission);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            pres.executeUpdate();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }
    	
    }
    //沒被選中的人要刪除
    public void deleteCommissionByID(String id_commission) {
    	/** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            
            /** SQL指令 */
            String sql = "DELETE FROM `sa_group6`.`tbtrustee_back` WHERE `id_commission` = ? AND `accept_status` = ?" ;
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, id_commission);
            pres.setString(2, "0"); //狀態為零代表沒被選中
            
            //delete
            pres.executeUpdate();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }
    }
    
    public int getMemberIdByCommission(int id_commission) {
    	int tbMember_idtbMember = 0;
    	
    	/** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            
            /** SQL指令 */
            String sql = "SELECT `tbMember_idtbMember` FROM `sa_group6`.`tbtrustee_back` WHERE `id_commission` = ?";
            
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            //id_commission是外面傳進來的參數
            pres.setInt(1, id_commission);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            while(rs.next()) {
                /** 將 ResultSet 之資料取出 */
            	tbMember_idtbMember = rs.getInt("tbMember_idtbMember");
                
            }
            
            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }
        
    	return tbMember_idtbMember;
    	
    }
}
