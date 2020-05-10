package ncu.im3069.Group2.app;

import org.json.*;

public class Member 
{
    private int idtbMember;
    private String email;
    private String name;
    private String password;
    private int permission;
    private int level;
    private int member_exp;
    private String img;
    
    private MemberHelper mh =  MemberHelper.getHelper();
   
    public Member(int idtbMember, String email, String password, String name, int permission, int level, int member_exp, String img) 
    {
        this.idtbMember = idtbMember;
        this.email = email;
        this.password = password;
        this.name = name;
        this.permission = permission;
        this.level = level;
        this.member_exp = member_exp;
        this.img = img;
    }
    
    public Member(String email, String password, String name, int permission, int level, int member_exp, String img) 
    {
        this.email = email;
        this.password = password;
        this.name = name;
        this.permission = permission;
        this.level = level;
        this.member_exp = member_exp;
        this.img = img;
    }
    
    public Member(int idtbMember, String email, String password, int permission, int level, int member_exp, String img)
    {
    	this.idtbMember = idtbMember;
        this.email = email;
        this.password = password;
        this.permission = permission;
        this.level = level;
        this.member_exp = member_exp;
        this.img = img;
    }
    
    public int getID() 
    {
        return this.idtbMember;
    }

    public String getEmail() 
    {
        return this.email;
    }
    
    public String getName()
    {  
        return this.name;
    }

    public String getPassword() 
    {
        return this.password;
    }
    
    public int getPermission() 
    {
        return this.permission;
    }
    
    public int getLevel() 
    {
        return this.level;
    }
    
    public int getMember_exp() 
    {
        return this.member_exp;
    }
    
    public String getImg() 
    {
        return this.img;
    }
    
    public JSONObject getData() 
    {
        JSONObject jso = new JSONObject();
        jso.put("idtbMember", getID());
        jso.put("name", getName());
        jso.put("email", getEmail());
        jso.put("password", getPassword());
        jso.put("permission", getPermission());
        jso.put("level", getLevel());
        jso.put("member_exp", getMember_exp());
        jso.put("img", getImg());
        
        return jso;
    }
}