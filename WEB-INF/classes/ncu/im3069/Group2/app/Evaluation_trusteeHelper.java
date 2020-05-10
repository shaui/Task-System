package ncu.im3069.Group2.app;

import java.sql.*;
import org.json.*;

import ncu.im3069.Group2.util.DBMgr;

public class Evaluation_trusteeHelper 
{

    private Evaluation_trusteeHelper() {}

    private static Evaluation_trusteeHelper eh;
    
    private Connection conn = null;
    
    private PreparedStatement pres = null;
    private PreparedStatement pres1 = null;
    private PreparedStatement pres2 = null;
    
    public static Evaluation_trusteeHelper getHelper() 
    {
        if(eh == null) eh = new Evaluation_trusteeHelper();
        
        return eh;
    }
    
    public JSONObject create(Evaluation E) {
        String exexcute_sql = "";
        long start_time = System.nanoTime();
        int row = 0;
        
        try {
            conn = DBMgr.getConnection();
            String evaluation = E.getEvaluation();
         
            int idtbHistory_record = E.getTbMember_idtbMember();
            
            String trustee_eval = evaluation;
            String sql = "UPDATE `sa_group6`.`tbhistory_record` SET `trustee_eval` = '"+trustee_eval+"'  WHERE `tbhistory_record`.`idtbHistory_record` = "+idtbHistory_record+"";
            pres = conn.prepareStatement(sql);
            
            row = pres.executeUpdate();
            
            idtbHistory_record -= 1;
            sql = "UPDATE `sa_group6`.`tbhistory_record` SET `trustee_eval` = '"+trustee_eval+"'  WHERE `tbhistory_record`.`idtbHistory_record` = "+idtbHistory_record+"";
            pres = conn.prepareStatement(sql);
            
            row = pres.executeUpdate();
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            

        } catch (SQLException e) {
            System.err.format("number0 SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
            System.err.println("5555666");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
    
    public JSONObject getById_Trustee_History(String id) //委託人取得歷程記錄
    {
        JSONObject data;
        JSONArray jsa = new JSONArray();
        String exexcute_sql = "";
        long start_time = System.nanoTime();
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        
        try {
            conn = DBMgr.getConnection();
            String sql1 = "SELECT * FROM `sa_group6`.`tbhistory_record` WHERE tbMember_idtbMember = '"+id+"'";
            
            pres1 = conn.prepareStatement(sql1);
            rs1 = pres1.executeQuery();

            exexcute_sql = pres1.toString();
            System.out.println(exexcute_sql);
            //所需參數//
            int id_delegator_member = 0;
            int idtbHistory_record = 0;
            String brief_description = "";
            String detail_description = "";
            int star_level = 0;
            int exp = 0;
            String name = "";
            
            while(rs1.next()) {
            	data = new JSONObject();
                idtbHistory_record = rs1.getInt("idtbHistory_record");
                id_delegator_member = rs1.getInt("id_delegator_member");
                detail_description = rs1.getString("detail_description");
                brief_description = rs1.getString("brief_description");
                star_level = rs1.getInt("star_level");
                exp = rs1.getInt("exp");   
                int id_trustee_member = rs1.getInt("id_trustee_member");

                
                
                String sql2 = "SELECT * FROM `sa_group6`.`tbmember` WHERE idtbMember = '"+id_delegator_member+"'";
                pres2 = conn.prepareStatement(sql2);
                rs2 = pres2.executeQuery();
                if(rs2.next()) {
                	name = rs2.getString("name");
                }
                data.put("idtbHistory_record", idtbHistory_record);
           	 	data.put("brief_description", brief_description);
           	 	data.put("detail_description", detail_description);
           	 	data.put("star_level", star_level);
           	 	data.put("name", name);
           	 	data.put("exp", exp);
           	 	data.put("id_delegator_member", id_delegator_member);
           	 	data.put("id_trustee_member", id_trustee_member);

           	 	jsa.put(data);
            }

        } catch (SQLException e) {
            System.err.format("number2  SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBMgr.close(rs1, pres1, conn);
            DBMgr.close(rs2, pres2, conn);
        }

        long end_time = System.nanoTime();
        long duration = (end_time - start_time);
        
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
    public JSONObject getById_Trustee_History_Detail(String id) //委託人取得詳細紀錄
    {
        JSONObject data;
        JSONArray jsa = new JSONArray();
        String exexcute_sql = "";
        long start_time = System.nanoTime();
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        
        try {
            conn = DBMgr.getConnection();
            String sql1 = "SELECT * FROM `sa_group6`.`tbhistory_record` WHERE idtbHistory_record = '"+id+"'";
            
            pres1 = conn.prepareStatement(sql1);
            rs1 = pres1.executeQuery();

            exexcute_sql = pres1.toString();
            System.out.println(exexcute_sql);
            //所需參數//
            int id_delegator_member = 0;
            int idtbHistory_record = 0;
            String brief_description = "";
            String detail_description = "";
            int star_level = 0;
            int exp = 0;
            String name = "";
            
            while(rs1.next()) {
            	data = new JSONObject();
                idtbHistory_record = rs1.getInt("idtbHistory_record");
                id_delegator_member = rs1.getInt("id_delegator_member");
                detail_description = rs1.getString("detail_description");
                brief_description = rs1.getString("brief_description");
                star_level = rs1.getInt("star_level");
                exp = rs1.getInt("exp");
                int id_trustee_member = rs1.getInt("id_trustee_member");
                String delegator_eval = rs1.getString("delegator_eval");
                String trustee_eval = rs1.getString("trustee_eval");
                
                
                String sql2 = "SELECT * FROM `sa_group6`.`tbmember` WHERE idtbMember = '"+id_delegator_member+"'";
                pres2 = conn.prepareStatement(sql2);
                rs2 = pres2.executeQuery();
                if(rs2.next()) {
                	name = rs2.getString("name");
                }
                data.put("idtbHistory_record", idtbHistory_record);
           	 	data.put("brief_description", brief_description);
           	 	data.put("detail_description", detail_description);
           	 	data.put("star_level", star_level);
           	 	data.put("name", name);
           	 	data.put("exp", exp);
           	 	data.put("id_delegator_member", id_delegator_member);
           	 	data.put("id_trustee_member", id_trustee_member);
           	 	data.put("delegator_eval", delegator_eval);
        	 	data.put("trustee_eval", trustee_eval);
           	 	jsa.put(data);
            }

        } catch (SQLException e) {
            System.err.format("number2  SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBMgr.close(rs1, pres1, conn);
            DBMgr.close(rs2, pres2, conn);
        }

        long end_time = System.nanoTime();
        long duration = (end_time - start_time);
        
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
}