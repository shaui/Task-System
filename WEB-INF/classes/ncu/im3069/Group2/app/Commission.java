package ncu.im3069.Group2.app;

import org.json.JSONObject;

public class Commission {

	private int idtbCommission_list;
	private int id_delegator;
	private String brief_description;
	private String detail_description;
	private int star_level;
	private int exp;
	private int tbDelegator_back_idtbDelegator_back;
	private String img;
	private String name;
	
	public Commission(int idtbCommission_list, int id_delegator, String brief_description, String detail_description, int star_level, int exp, String img, String name, int tbDelegator_back_idtbDelegator_back) {
		this.idtbCommission_list = idtbCommission_list;
		this.id_delegator = id_delegator;
		this.brief_description = brief_description;
		this.detail_description = detail_description;
		this.star_level = star_level;
		this.exp = exp;
		this.img = img;
		this.name = name;
		this.tbDelegator_back_idtbDelegator_back= tbDelegator_back_idtbDelegator_back;
	}
	
	public Commission(int id_delegator, String brief_description, String detail_description, int star_level, int exp, String img, String name, int tbDelegator_back_idtbDelegator_back) {
		this.id_delegator = id_delegator;
		this.brief_description = brief_description;
		this.detail_description = detail_description;
		this.star_level = star_level;
		this.exp = exp;
		this.img = img;
		this.name = name;
		this.tbDelegator_back_idtbDelegator_back= tbDelegator_back_idtbDelegator_back;
	}
	
	public Commission(int id_delegator, String brief_description, String detail_description, int star_level, int exp, int tbDelegator_back_idtbDelegator_back) {
		this.id_delegator = id_delegator;
		this.brief_description = brief_description;
		this.detail_description = detail_description;
		this.star_level = star_level;
		this.exp = exp;
		this.tbDelegator_back_idtbDelegator_back= tbDelegator_back_idtbDelegator_back;
	}
	
	public int getIdtbCommission_list() {
		return idtbCommission_list;
	}
	
	public int getId_delegator() {
		return id_delegator;
	}


	public String getBrief_description() {
		return brief_description;
	}


	public String getDetail_description() {
		return detail_description;
	}


	public int getStar_level() {
		return star_level;
	}


	public int getExp() {
		return exp;
	}
	
	
	public int getTbDelegator_back_idtbDelegator_back() {
		return tbDelegator_back_idtbDelegator_back;
	}


	public String getImg() {
		return img;
	}
	
	public String getName() {
		return name;
	}
	


	public JSONObject getData() {
        /** 透過JSONObject將該項產品所需之資料全部進行封裝*/
        JSONObject jso = new JSONObject();
        jso.put("idtbCommission_list", getIdtbCommission_list());
        jso.put("id_delegator", getId_delegator());
        jso.put("brief_description", getBrief_description());
        jso.put("detail_description", getDetail_description());
        jso.put("star_level", getStar_level());
        jso.put("exp", getExp());
        jso.put("img", getImg());
        jso.put("name", getName());
        jso.put("tbDelegator_back_idtbDelegator_back", getTbDelegator_back_idtbDelegator_back());
        return jso;
    }
	
}
