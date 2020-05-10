package ncu.im3069.Group2.app;

import org.json.JSONObject;

public class DelegatorBack {

	private int idtbDelegator_back;
	private String brief_description;
	private String detail_description;
	private int star_level;
	private int submit_status;
	private int exp;
	private int tbMember_idtbMember;
	private String img;
	private String name;
	
	public DelegatorBack(int idtbDelegator_back, String brief_description, String detail_description,
			int star_level, int submit_status, int exp, int tbMember_idtbMember, String img, String name) {
		this.idtbDelegator_back = idtbDelegator_back;
		this.brief_description = brief_description;
		this.detail_description = detail_description;
		this.star_level = star_level;
		this.submit_status = submit_status;
		this.exp = exp;
		this.tbMember_idtbMember = tbMember_idtbMember;
		this.img = img;
		this.name = name;
	}
	
	public DelegatorBack(String brief_description, String detail_description,
			int star_level, int submit_status, int exp, int tbMember_idtbMember, String img, String name) {
		this.brief_description = brief_description;
		this.detail_description = detail_description;
		this.star_level = star_level;
		this.submit_status = submit_status;
		this.exp = exp;
		this.tbMember_idtbMember = tbMember_idtbMember;
		this.img = img;
		this.name = name;
	}
	
	public DelegatorBack(String brief_description, String detail_description,
			int star_level, int submit_status, int exp, int tbMember_idtbMember) {
		this.brief_description = brief_description;
		this.detail_description = detail_description;
		this.star_level = star_level;
		this.submit_status = submit_status;
		this.exp = exp;
		this.tbMember_idtbMember = tbMember_idtbMember;
	}

	public int getIdtbDelegator_back() {
		return idtbDelegator_back;
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




	public int getSubmit_status() {
		return submit_status;
	}




	public int getExp() {
		return exp;
	}




	public int getTbMember_idtbMember() {
		return tbMember_idtbMember;
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
        jso.put("idtbDelegator_back", getIdtbDelegator_back());
        jso.put("brief_description", getBrief_description());
        jso.put("detail_description", getDetail_description());
        jso.put("star_level", getStar_level());
        jso.put("submit_status", getSubmit_status());
        jso.put("exp", getExp());
        jso.put("tbMember_idtbMember", getTbMember_idtbMember());
        jso.put("img", getImg());
        jso.put("name", getName());
        return jso;
    }
}
