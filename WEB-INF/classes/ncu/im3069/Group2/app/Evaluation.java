package ncu.im3069.Group2.app;

import org.json.*;

public class Evaluation
{
    private String evaluation;
    private int idtbHistory_record;
    private int id_delegator_member;
    private int id_trustee_member;
    private String brief_description;
    private String detail_description;
    private int star_level;
    private int exp;
    private String delegator_eval;
    private String trustee_eval;
    private int tbMember_idtbMember;

    private Evaluation_trusteeHelper eh =  Evaluation_trusteeHelper.getHelper();
   
    public Evaluation(int idtbHistory_record, int id_delegator_member, int id_trustee_member, String brief_description, String detail_description, int star_level, int exp, String delegator_eval,  String trustee_eval, int tbMember_idtbMember) 
    {
    	this.evaluation = evaluation;
        
        this.idtbHistory_record = idtbHistory_record;
        this.id_delegator_member = id_delegator_member;
        this.id_trustee_member = id_trustee_member;
        this.brief_description = brief_description;
        this.star_level = star_level;
        this.exp = exp;
        this.delegator_eval = delegator_eval;
        this.trustee_eval = trustee_eval;
        this.tbMember_idtbMember = tbMember_idtbMember;     
    }
    
    public Evaluation(int id_delegator_member, int id_trustee_member, String brief_description, String detail_description, int star_level, int exp, String delegator_eval,  String trustee_eval, int tbMember_idtbMember) 
    {
    	this.evaluation = evaluation;
        
        this.id_delegator_member = id_delegator_member;
        this.id_trustee_member = id_trustee_member;
        this.brief_description = brief_description;
        this.star_level = star_level;
        this.exp = exp;
        this.delegator_eval = delegator_eval;
        this.trustee_eval = trustee_eval;
        this.tbMember_idtbMember = tbMember_idtbMember;     
    }
    
    public Evaluation(String id, String evaluation) 
    {
    	this.tbMember_idtbMember =  Integer.parseInt(id);
        this.evaluation = evaluation; 
    }
    
    public Evaluation(int id, String evaluation) 
    {
    	this.tbMember_idtbMember = id;
        this.evaluation = evaluation; 
    }
    
    public int getID() 
    {
        return this.idtbHistory_record;
    }
    
    public int getIdtbHistory_record() 
    {
        return this.idtbHistory_record;
    }
    
    public int getId_delegator_member() 
    {
        return this.id_delegator_member;
    }
    
    public String getBrief_description() 
    {
        return this.brief_description;
    }
    
    public String getDetail_description() 
    {
        return this.detail_description;
    }
    
    public int getStar_level() 
    {
        return this.star_level;
    }
    
    public int getExp() 
    {
        return this.exp;
    }
    
    public String getDelegator_eval() 
    {
        return this.delegator_eval;
    }
    
    public String getTrustee_eval() 
    {
        return this.trustee_eval;
    }
    
    public int getId_trustee_member()
    {
    	return this.id_trustee_member;
    }
    
    public int getTbMember_idtbMember() 
    {
        return this.tbMember_idtbMember;
    }
    
    public String getEvaluation() 
    {
        return this.evaluation;
    }
    
    public JSONObject getData() 
    {
        JSONObject jso = new JSONObject();
        jso.put("evaluation", getEvaluation());
        jso.put("idtbHistory_record", getIdtbHistory_record());
        jso.put("id_delegator_member", getId_delegator_member());
        jso.put("id_trustee_member", getId_trustee_member());
        jso.put("brief_description", getBrief_description());
        jso.put("detail_description", getDetail_description());
        jso.put("star_level", getStar_level());
        jso.put("exp", getExp());
        jso.put("delegator_eval", getDelegator_eval());
        jso.put("trustee_eval", getTrustee_eval());
        jso.put("tbMember_idtbMember", getTbMember_idtbMember());
        
        return jso;
    }
}