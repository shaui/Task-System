package ncu.im3069.Group2.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import ncu.im3069.Group2.util.DBMgr;

public class GuildHelper {
	private GuildHelper() {
		
	}
	
	private static GuildHelper gh;
	private Connection conn = null;
    private PreparedStatement pres = null;

    public static GuildHelper getHelper() {
        /** Singleton檢查是否已經有ProductHelper物件，若無則new一個，若有則直接回傳 */
        if(gh == null) gh = new GuildHelper();
        
        return gh;
    }
    
    public JSONObject getAll() {

		Guild g = null;

		JSONArray jsa = new JSONArray();
		String execute_sql = "";
		long start_time = System.nanoTime();
		int row = 0;
		ResultSet rs = null;
		
		try {
			conn = DBMgr.getConnection();
			String sql = "SELECT * FROM `sa_group6`.`tbguild`";

			pres = conn.prepareStatement(sql);
			rs = pres.executeQuery();

			execute_sql = pres.toString();
			System.out.println(execute_sql);

			while(rs.next()) {
				row += 1;

				int id_delegator_person = rs.getInt("id_delegator_person");
				String brief_description = rs.getString("brief_description");
				String detail_description = rs.getString("detail_description");
				int star_level = rs.getInt("star_level");
				int tbDelegator_back_idtbDelegator_back = rs.getInt("tbDelegator_back_idtbDelegator_back");
				
				g = new Guild(id_delegator_person, brief_description, detail_description, star_level, tbDelegator_back_idtbDelegator_back);
				jsa.put(g.getData());
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
        response.put("sql", execute_sql);
        response.put("time", duration);
        response.put("data", jsa);

		
		return response;
	}
    
    public void deleteGuildCommission(int tbDelegator_back_idtbDelegator_back) {
    	/** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            
            /** SQL指令 */
            String sql = "DELETE FROM `sa_group6`.`tbguild` WHERE `tbDelegator_back_idtbDelegator_back` = ?";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, tbDelegator_back_idtbDelegator_back);

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
