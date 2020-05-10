package ncu.im3069.Group2.app;

import org.json.JSONObject;

public class Applying {

	private int idtbApplying;
	private int id_applying_member;
	
	public Applying(int idtbApplying, int id_applying_member) {
		this.idtbApplying = idtbApplying;
		this.id_applying_member = id_applying_member;
	}

	public int getIdtbApplying() {
		return idtbApplying;
	}

	public int getId_applying_member() {
		return id_applying_member;
	}
	
	public JSONObject getData() {
        /** 透過JSONObject將該項產品所需之資料全部進行封裝*/
        JSONObject jso = new JSONObject();
        jso.put("idtbApplying", getIdtbApplying());
        jso.put("id_applying_member", getId_applying_member());

        return jso;
    }
	
}
