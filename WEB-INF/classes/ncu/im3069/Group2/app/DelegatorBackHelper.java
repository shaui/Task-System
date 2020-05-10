package ncu.im3069.Group2.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import ncu.im3069.Group2.util.DBMgr;

public class DelegatorBackHelper {

	private DelegatorBackHelper() {
		
	}
	
	private static DelegatorBackHelper dbh;
	private Connection conn = null;
    private PreparedStatement pres = null;
    
    public static DelegatorBackHelper getHelper() {
        /** Singleton檢查是否已經有ProductHelper物件，若無則new一個，若有則直接回傳 */
        if(dbh == null) dbh = new DelegatorBackHelper();
        
        return dbh;
    }
    
    public JSONObject getAll(String id) {
        /** 新建一個 Product 物件之 m 變數，用於紀錄每一位查詢回之商品資料 */
    	DelegatorBack db = null;
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
            String sql = "SELECT * FROM `sa_group6`.`tbdelegator_back` WHERE `tbMember_idtbMember` = ?";
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
                int idtbDelegator_back = rs.getInt("idtbDelegator_back");
                String brief_description = rs.getString("brief_description");
                String detail_description = rs.getString("detail_description");
                int star_level = rs.getInt("star_level");
                int submit_status = rs.getInt("submit_status");
                int exp = rs.getInt("exp");
                int tbMember_idtbMember = rs.getInt("tbMember_idtbMember");
                
                MemberHelper mh = MemberHelper.getHelper();
                //直接用傳進來當參數的id因為自己本身就是委託者
                String img = mh.getImgByID(String.valueOf(id));
                String name = mh.getNameByID(String.valueOf(id));

                
                /** 將每一筆商品資料產生一名新Product物件 */
                db = new DelegatorBack(idtbDelegator_back, brief_description,detail_description,star_level,submit_status, exp,tbMember_idtbMember, img, name);
                /** 取出該項商品之資料並封裝至 JSONsonArray 內 */
                jsa.put(db.getData());
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
    
    public JSONObject getCommissionByID(String id, String id_commission) {
    	/** 新建一個 Product 物件之 m 變數，用於紀錄每一位查詢回之商品資料 */
    	DelegatorBack db = null;
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
            String sql = "SELECT * FROM `sa_group6`.`tbdelegator_back` WHERE `idtbDelegator_back` = ?";
            
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
                int idtbDelegator_back = rs.getInt("idtbDelegator_back");
                String brief_description = rs.getString("brief_description");
                String detail_description = rs.getString("detail_description");
                int star_level = rs.getInt("star_level");
                int submit_status = rs.getInt("submit_status");
                int exp = rs.getInt("exp");
                int tbMember_idtbMember = rs.getInt("tbMember_idtbMember");
                
                MemberHelper mh = MemberHelper.getHelper();
                //直接用傳進來當參數的id因為自己本身就是委託者
                String img = mh.getImgByID(String.valueOf(id));
                String name = mh.getNameByID(String.valueOf(id));

                
                /** 將每一筆商品資料產生一名新Product物件 */
                db = new DelegatorBack(idtbDelegator_back, brief_description,detail_description,star_level,submit_status, exp,tbMember_idtbMember, img, name);
                /** 取出該項商品之資料並封裝至 JSONsonArray 內 */
                jsa.put(db.getData());
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
    
    public JSONObject getApplying(String id_commission) {
    	/** 新建一個 Product 物件之 m 變數，用於紀錄每一位查詢回之商品資料 */
    	Applying ap = null;
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
            String sql = "SELECT * FROM `sa_group6`.`tbapplying` WHERE `tbDelegator_back_idtbDelegator_back` = ?";
            
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
            	int idtbApplying = rs.getInt("idtbApplying");
                int id_applying_member = rs.getInt("id_applying_member");
                
                MemberHelper mh = MemberHelper.getHelper();
                Member m = mh.getMemberByID(String.valueOf(id_applying_member));
                /** 取出該項商品之資料並封裝至 JSONsonArray 內 */
                jsa.put(m.getData());
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
    
    public void updateStatus(String id_commission, String status) {
    	
    	/** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";

    	ResultSet rs = null;
    	
    	try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "Update `sa_group6`.`tbdelegator_back` SET `submit_status` = ? WHERE `idtbDelegator_back` = ?";
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            //id_commission是外面傳進來的參數
            pres.setString(1, status);
            pres.setString(2, id_commission);
            
            //update
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
    
    public void deleteApplyingByID(String id_commission) {
    	/** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            
            /** SQL指令 */
            String sql = "DELETE FROM `sa_group6`.`tbapplying` WHERE `tbDelegator_back_idtbDelegator_back` = ?";
            
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
    
    public int submitCommission(DelegatorBack delegatorBack) {
    	boolean isSuccess = true;
    	
    	/** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs_PK = null;
        int commission_PK = 0;
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            String sql = "INSERT INTO `sa_group6`.`tbdelegator_back`(`brief_description`, `detail_description`, `star_level`, `submit_status`, `exp`, `tbMember_idtbMember`)"
                    + " VALUES(?, ?, ?, ?, ?, ?)";

            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pres.setString(1, delegatorBack.getBrief_description());
            pres.setString(2, delegatorBack.getDetail_description());
            pres.setInt(3, delegatorBack.getStar_level());
            pres.setInt(4, delegatorBack.getSubmit_status());
            pres.setInt(5, delegatorBack.getExp());
            pres.setInt(6, delegatorBack.getTbMember_idtbMember());
            
            //execute insert
            pres.executeUpdate();
            //獲取PK
            rs_PK = pres.getGeneratedKeys();
            while(rs_PK.next()) {
            	commission_PK = rs_PK.getInt(1);
           }
            
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
    	
    	return commission_PK;
    }
    
    public boolean insertGuild(Guild guild) {
    	boolean isSuccess = true;
    	
    	/** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        


        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            String sql = "INSERT INTO `sa_group6`.`tbguild`(`id_delegator_person`, `brief_description`, `detail_description`, `star_level`, `tbDelegator_back_idtbDelegator_back`)"
                    + " VALUES(?, ?, ?, ?, ?)";

            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, guild.getIDP());
            pres.setString(2, guild.getBrief());
            pres.setString(3, guild.getDetail());
            pres.setInt(4, guild.getStar());
            pres.setInt(5, guild.getIDB());
            
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
    
    public void confirmCommission(int exp, int idtbDelegator_back) {
    	/** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";

    	ResultSet rs = null;
    	
    	try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "Update `sa_group6`.`tbdelegator_back` SET `submit_status` = ?, `exp` = ? WHERE `idtbDelegator_back` = ?";
            
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            //id_commission是外面傳進來的參數
            pres.setInt(1, 1);
            pres.setInt(2, exp);
            pres.setInt(3, idtbDelegator_back);
            //update
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
    
    public void insertHistory(int id_delegator_member, int id_trustee_member, String brief_description, String detail_description, int star_level, int exp, int tbMember_idtbMember) {
    	/** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";

        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            String sql = "INSERT INTO `sa_group6`.`tbhistory_record`(`id_delegator_member`, `id_trustee_member`, `brief_description`, `detail_description`,`star_level`, `exp`, `delegator_eval`, `trustee_eval`,`tbMember_idtbMember`)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, id_delegator_member);
            pres.setInt(2, id_trustee_member);
            pres.setString(3, brief_description);
            pres.setString(4, detail_description);
            pres.setInt(5, star_level);
            pres.setInt(6, exp);
            pres.setString(7, "");
            pres.setString(8, "");
            pres.setInt(9, tbMember_idtbMember);
            
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
    
    public void deleteDelegatorCompleteCommission(int idtbDelegator_back) {
    	/** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            
            /** SQL指令 */
            String sql = "DELETE FROM `sa_group6`.`tbdelegator_back` WHERE `idtbDelegator_back` = ?" ;
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, idtbDelegator_back);
            
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
    
    public void deleteTrusteeCompleteCommission(int id_commission) {
    	/** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            
            /** SQL指令 */
            String sql = "DELETE FROM `sa_group6`.`tbtrustee_back` WHERE `id_commission` = ?" ;
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, id_commission);
            
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
}
