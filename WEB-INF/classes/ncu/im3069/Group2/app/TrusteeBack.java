package ncu.im3069.Group2.app;

import org.json.JSONObject;

public class TrusteeBack {

	private int idtbTrustee_back;
	private int id_member;
	private String brief_description;
	private String detail_description;
	private int star_level;
	private int accept_status;
	private int exp;
	private int tbMember_idtbMember;
	private String img;
	private String name;
	
	public TrusteeBack(int idtbTrustee_back, int id_member, String brief_description, String detail_description,
			int star_level, int accept_status, int exp, int tbMember_idtbMember, String img, String name) {
		this.idtbTrustee_back = idtbTrustee_back;
		this.id_member = id_member;
		this.brief_description = brief_description;
		this.detail_description = detail_description;
		this.star_level = star_level;
		this.accept_status = accept_status;
		this.exp = exp;
		this.tbMember_idtbMember = tbMember_idtbMember;
		this.img = img;
		this.name = name;
	}

	public int getIdtbTrustee_back() {
		return idtbTrustee_back;
	}

	public int getId_member() {
		return id_member;
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

	public int getAccept_status() {
		return accept_status;
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
        jso.put("idtbTrustee_back", getIdtbTrustee_back());
        jso.put("id_member", getId_member());
        jso.put("brief_description", getBrief_description());
        jso.put("detail_description", getDetail_description());
        jso.put("star_level", getStar_level());
        jso.put("accept_status", getAccept_status());
        jso.put("exp", getExp());
        jso.put("tbMember_idtbMember", getTbMember_idtbMember());
        jso.put("img", getImg());
        jso.put("name", getName());
        return jso;
    }
}
