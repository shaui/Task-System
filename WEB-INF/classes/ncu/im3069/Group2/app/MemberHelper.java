package ncu.im3069.Group2.app;

import java.sql.*;
import org.json.*;

import ncu.im3069.Group2.util.DBMgr;

public class MemberHelper 
{
	  private MemberHelper() {}
	  private static MemberHelper mh;
	  private Connection conn = null;
	  private PreparedStatement pres = null;

	  public static MemberHelper getHelper()
	  {
	      if(mh == null) 
	      {
	    	  mh = new MemberHelper();
	      }
	      return mh;
	  }
	  
	  public boolean checkDuplicate(Member m)
	  {
	        int row = -1;
	        ResultSet rs = null;
	        
	        try 
	        {
	            conn = DBMgr.getConnection();
	            String sql = "SELECT count(*) FROM `sa_group6`.`tbmember` WHERE `email` = ?";
	            
	            String email = m.getEmail();
	            
	            pres = conn.prepareStatement(sql);
	            pres.setString(1, email);

	            rs = pres.executeQuery();

	            rs.next();
	            row = rs.getInt("count(*)");
	            System.out.print(row);
	        } 
	        catch (SQLException e) 
	        {
	            System.err.format("333SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	        finally 
	        {
	            DBMgr.close(rs, pres, conn);
	        }
	        
	        return (row == 0) ? false : true;
	  }
	  
	  public JSONObject create(Member m) 
	  {
	        String exexcute_sql = "";
	        long start_time = System.nanoTime();
	        int row = 0;
	        
	        try 
	        {
	            conn = DBMgr.getConnection();
	            String name = m.getName();
	            String email = m.getEmail();
	            String password = m.getPassword();
	            int permission = 0;
	            int level = 0;
	            int exp = 0;
	            String img = "img2.jpg";
	            String sql = "INSERT INTO `sa_group6`.`tbmember`(name, email, password, permission, level, member_exp, img)"
	                    + " VALUES('"+name+"','"+email+"', '"+password+"', "+permission+", "+level+", "+exp+", '"+img+"')";
	            pres = conn.prepareStatement(sql);
	            
	            
	            row = pres.executeUpdate();
	            exexcute_sql = pres.toString();
	            System.out.println(exexcute_sql);
	        } 
	        catch (SQLException e)
	        {
	            System.err.format("111SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        } 
	        finally
	        {
	            DBMgr.close(pres, conn);
	        }
	        
	        long end_time = System.nanoTime();
	        long duration = (end_time - start_time);

	        JSONObject response = new JSONObject();
	        response.put("sql", exexcute_sql);
	        response.put("time", duration);
	        response.put("row", row);

	        return response;
	    }
	  
	  public JSONObject getmember_info(String enter_email,String enter_password) {
	        Member m = null;
	        JSONObject data = new JSONObject();
	        JSONArray jsa = new JSONArray();
	        String exexcute_sql = "";
	        long start_time = System.nanoTime();
	        ResultSet rs = null;
	        
	        try {
	           
	        	 conn = DBMgr.getConnection();
	             
	             String sql = "SELECT * FROM `sa_group6`.`tbmember` WHERE email='"+enter_email+"'";
	             pres = conn.prepareStatement(sql);
	             rs=pres.executeQuery();

	             exexcute_sql = pres.toString();
	             System.out.println(exexcute_sql);

	             if(rs.next())//next 一定要(指針)
	             {
	            	 int member_id = rs.getInt("idtbMember");
	            	 String email = rs.getString("email");
	            	 String password = rs.getString("password");
	            	 int permission = rs.getInt("permission");
	            	 int level = rs.getInt("level");
	            	 int exp = rs.getInt("member_exp");
	            	 String img = rs.getString("img");
	            	 m = new Member(member_id,email,password,permission,level,exp,img);
	            	 data.put("idtbMember", member_id);
	            	 data.put("email", email);
	            	 data.put("permission", permission);
	            	 data.put("level", level);
	            	 data.put("exp", exp);
	            	 data.put("img", img);
	            	 if(enter_password.equals(password))
	            	 {
	            		 data.put("verification", true);
	            	 }
	            	 else 
	            	 {
	            		 data.put("verification", false);
	            	 }
	             }
	             jsa.put(data);
	             
	        } catch (SQLException e) {
	            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            DBMgr.close(rs, pres, conn);
	        }
	        long end_time = System.nanoTime();
	        long duration = (end_time - start_time);
	        
	        JSONObject response = new JSONObject();
	        response.put("sql", exexcute_sql);
	        response.put("time", duration);
	        response.put("data", jsa);
	        
	        return response;
	    }
	  
	  public String getImgByID(String idtbmember) {
	    	
	    	ResultSet rs = null;
	    	
	    	String img = "";
	    	
	    	try {
	            /** 取得資料庫之連線 */
	            conn = DBMgr.getConnection();
	            /** SQL指令 */
	            String sql = "SELECT img FROM `sa_group6`.`tbmember` WHERE `idtbMember` = ? LIMIT 1";
	            
	            /** 將參數回填至SQL指令當中 */
	            pres = conn.prepareStatement(sql);
	            pres.setString(1, idtbmember);
	            /** 執行查詢之SQL指令並記錄其回傳之資料 */
	            rs = pres.executeQuery();

	            
	            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
	            /** 正確來說資料庫只會有一筆該會員編號之資料，因此其實可以不用使用 while 迴圈 */
	            while(rs.next()) {
	                /** 每執行一次迴圈表示有一筆資料 */
	                
	                /** 將 ResultSet 之資料取出 */
	                img = rs.getString("img");
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
	    	return img;
	    }
	    
	    public String getNameByID(String idtbmember) {
	    	ResultSet rs = null;
	    	String name = "";
	    	
	    	try {
	            /** 取得資料庫之連線 */
	            conn = DBMgr.getConnection();
	            /** SQL指令 */
	            String sql = "SELECT name FROM `sa_group6`.`tbmember` WHERE `idtbMember` = ? LIMIT 1";
	            
	            /** 將參數回填至SQL指令當中 */
	            pres = conn.prepareStatement(sql);
	            pres.setString(1, idtbmember);
	            /** 執行查詢之SQL指令並記錄其回傳之資料 */
	            rs = pres.executeQuery();

	            
	            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
	            /** 正確來說資料庫只會有一筆該會員編號之資料，因此其實可以不用使用 while 迴圈 */
	            while(rs.next()) {
	                /** 每執行一次迴圈表示有一筆資料 */
	                
	                /** 將 ResultSet 之資料取出 */
	                name = rs.getString("name");
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
	    	
	    	return name;
	    }
	    
	    public Member getMemberByID(String idtbmember) {
	    	 /** 新建一個 Member 物件之 m 變數，用於紀錄每一位查詢回之會員資料 */
	        Member m = null;

	        /** 記錄實際執行之SQL指令 */
	        String exexcute_sql = "";

	        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
	        ResultSet rs = null;
	        
	        try {
	            /** 取得資料庫之連線 */
	            conn = DBMgr.getConnection();
	            /** SQL指令 */
	            String sql = "SELECT * FROM `sa_group6`.`tbmember` WHERE `idtbMember` = ? LIMIT 1";
	            
	            /** 將參數回填至SQL指令當中 */
	            pres = conn.prepareStatement(sql);
	            pres.setString(1, idtbmember);
	            /** 執行查詢之SQL指令並記錄其回傳之資料 */
	            rs = pres.executeQuery();

	            /** 紀錄真實執行的SQL指令，並印出 **/
	            exexcute_sql = pres.toString();
	            System.out.println(exexcute_sql);
	            
	            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
	            /** 正確來說資料庫只會有一筆該會員編號之資料，因此其實可以不用使用 while 迴圈 */
	            while(rs.next()) {
	                
	                /** 將 ResultSet 之資料取出 */
	                int idtbMember = rs.getInt("idtbMember");
	                String name = rs.getString("name");
	                String email = rs.getString("email");
	                String password = rs.getString("password");
	                int permission = rs.getInt("permission");
	                int level = rs.getInt("level");
	                int member_exp = rs.getInt("member_exp");
	                String img = rs.getString("img");
	                
	                /** 將每一筆會員資料產生一名新Member物件 */
	                m = new Member(idtbMember, name, email, password, permission, level, member_exp, img);
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

	        return m;
	    }
}
