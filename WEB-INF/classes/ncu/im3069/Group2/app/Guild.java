package ncu.im3069.Group2.app;

import org.json.JSONObject;

public class Guild {

	private int id_delegator_person;
	private String brief_description;
	private String detail_description;
	private int star_level;
	private int tbDelegator_back_idtbDelegator_back;

	public Guild(int id_delegator_person, String brief_description, String detail_description, int star_level, int tbDelegator_back_idtbDelegator_back) {
		this.id_delegator_person = id_delegator_person;
		this.brief_description = brief_description;
		this.detail_description = detail_description;
		this.star_level = star_level;
		this.tbDelegator_back_idtbDelegator_back = tbDelegator_back_idtbDelegator_back;
	}

	public int getIDP() {
		return id_delegator_person;
	}

	public String getBrief() {
		return brief_description;
	}

	public String getDetail() {
		return detail_description;
	}

	public int getStar(){
		return star_level;
	}

	public int getIDB() {
		return tbDelegator_back_idtbDelegator_back;
	}

	public JSONObject getData() {
		JSONObject jso = new JSONObject();
        jso.put("id_delegator_person", getIDP());
        jso.put("brief_description", getBrief());
        jso.put("detail_description", getDetail());
        jso.put("star_level", getStar());
        jso.put("tbDelegator_back_idtbDelegator_back", getIDB());
        return jso;
	}
}