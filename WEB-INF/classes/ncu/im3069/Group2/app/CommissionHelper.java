package ncu.im3069.Group2.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import ncu.im3069.Group2.util.DBMgr;

public class CommissionHelper {

	public CommissionHelper() {
		
	};
	
	private static CommissionHelper ch;
	private Connection conn = null;
    private PreparedStatement pres = null;
    
    public static CommissionHelper getHelper() {
        /** Singleton檢查是否已經有ProductHelper物件，若無則new一個，若有則直接回傳 */
        if(ch == null) ch = new CommissionHelper();
        
        return ch;
    }
    
    public JSONObject getAll() {
        /** 新建一個 Product 物件之 m 變數，用於紀錄每一位查詢回之商品資料 */
    	Commission c = null;
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
            String sql = "SELECT * FROM `sa_group6`.`tbcommission_list`";
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            while(rs.next()) {
                
                /** 將 ResultSet 之資料取出 */
            	int idtbCommission_list = rs.getInt("idtbCommission_list");
                int id_delegator = rs.getInt("Id_delegator");
                String brief_description = rs.getString("brief_description");
                String detail_description = rs.getString("detail_description");
                int star_level = rs.getInt("star_level");
                int exp = rs.getInt("exp");
                int tbDelegator_back_idtbDelegator_back = rs.getInt("tbDelegator_back_idtbDelegator_back");
                //用id_delegator取得委託者的大頭貼
                MemberHelper mh = MemberHelper.getHelper();
                String img = mh.getImgByID(String.valueOf(id_delegator));
                String name = mh.getNameByID(String.valueOf(id_delegator));

                
                /** 將每一筆商品資料產生一名新Product物件 */
                c = new Commission(idtbCommission_list, id_delegator, brief_description,detail_description,star_level,exp, img, name, tbDelegator_back_idtbDelegator_back);
                /** 取出該項商品之資料並封裝至 JSONsonArray 內 */
                jsa.put(c.getData());
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
    
    public JSONObject getByIdCommission(String id_commission) {
    	/** 新建一個 Product 物件之 m 變數，用於紀錄每一位查詢回之商品資料 */
        Commission c = null;
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
            String sql = "SELECT * FROM `sa_group6`.`tbcommission_list` WHERE `idtbCommission_list` = ?";
            
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
            while(rs.next()) {
                
                /** 將 ResultSet 之資料取出 */
            	int idtbCommission_list = rs.getInt("idtbCommission_list");
                int id_delegator = rs.getInt("Id_delegator");
                String brief_description = rs.getString("brief_description");
                String detail_description = rs.getString("detail_description");
                int star_level = rs.getInt("star_level");
                int exp = rs.getInt("exp");
                int tbDelegator_back_idtbDelegator_back = rs.getInt("tbDelegator_back_idtbDelegator_back");
                //用id_delegator取得委託者的大頭貼
                MemberHelper mh = MemberHelper.getHelper();
                String img = mh.getImgByID(String.valueOf(id_delegator));
                String name = mh.getNameByID(String.valueOf(id_delegator));

                
                /** 將每一筆商品資料產生一名新Product物件 */
                c = new Commission(idtbCommission_list, id_delegator, brief_description,detail_description,star_level,exp, img, name, tbDelegator_back_idtbDelegator_back);
                /** 取出該項商品之資料並封裝至 JSONsonArray 內 */
                jsa.put(c.getData());
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
    
    public void deleteCommissionByID(String id_commission) {
    	/** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            
            /** SQL指令 */
            String sql = "DELETE FROM `sa_group6`.`tbcommission_list` WHERE `tbDelegator_back_idtbDelegator_back` = ?";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, id_commission);

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
    
    public boolean applyingCommission(int id_applicant, Commission commission) {
    	boolean isSuccess = true;
    	/** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";

        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            String sql = "INSERT INTO `sa_group6`.`tbtrustee_back`(`id_member`, `brief_description`, `detail_description`, `star_level`, `accept_status`, `exp`, `tbMember_idtbMember`, `id_commission`)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, commission.getId_delegator());
            pres.setString(2, commission.getBrief_description());
            pres.setString(3, commission.getDetail_description());
            pres.setInt(4, commission.getStar_level());
            pres.setInt(5, 0);
            pres.setInt(6, commission.getExp());
            //誰申請委託
            pres.setInt(7, id_applicant);
            //實際上是哪個後台的委託
            pres.setInt(8, commission.getTbDelegator_back_idtbDelegator_back());
                        
            //execute insert
            pres.executeUpdate();
            
            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
            isSuccess = false;
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
            isSuccess = false;
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(pres, conn);
        }
    	
    	return isSuccess;
    }
    
    public boolean insertApplyingTable(int id_applying_member, int tbDelegator_back_idtbDelegator_back) {
    	boolean isSuccess = true;
    	
    	/** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";

        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            String sql = "INSERT INTO `sa_group6`.`tbapplying`(`id_applying_member`, `tbDelegator_back_idtbDelegator_back`)"
                    + " VALUES(?, ?)";

            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, id_applying_member);
            pres.setInt(2, tbDelegator_back_idtbDelegator_back);

            //execute insert
            pres.executeUpdate();
            
            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
            isSuccess = false;
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
            isSuccess = false;
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(pres, conn);
        }
    	
    	return isSuccess;
    }
    
    public void insertCommission(Commission commission) {
    	/** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";

        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            String sql = "INSERT INTO `sa_group6`.`tbcommission_list`(`Id_delegator`, `brief_description`, `detail_description`, `star_level`, `exp`, `tbDelegator_back_idtbDelegator_back`)"
                    + " VALUES(?, ?, ?, ?, ?, ?)";

            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, commission.getId_delegator());
            pres.setString(2, commission.getBrief_description());
            pres.setString(3, commission.getDetail_description());
            pres.setInt(4, commission.getStar_level());
            pres.setInt(5, commission.getExp());
            pres.setInt(6, commission.getTbDelegator_back_idtbDelegator_back());
            
            //execute insert
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
            DBMgr.close(pres, conn);
        }
    }
    
    public boolean isApplying(int id_applicant, int tbDelegator_back_idtbDelegator_back) {
    	boolean isApply = true;
    	
    	/** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT `idtbTrustee_back` FROM `sa_group6`.`tbtrustee_back` WHERE `tbMember_idtbMember` = ? AND `id_commission` = ?";

            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, id_applicant);
            pres.setInt(2, tbDelegator_back_idtbDelegator_back);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            //如果抓的到資料，代表申請過了
            if(rs.next()) {
            	isApply = true;
            }else {
            	isApply = false;
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
    	return isApply;
    }
}
