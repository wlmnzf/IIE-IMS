package cn.iie.icm.pojo;

public class ClientFormPojo {
    private int id;
    private String userid;
    private String name;
    private String time;
    private String json;
    private String formtoken;
    private int isChecked;

    public ClientFormPojo(){super();}

    public ClientFormPojo(int id,String userid,String name,String time,String json,String formtoken,int isChecked)
    {
        this.id=id;
        this.userid=userid;
        this.name=name;
        this.time=time;
        this.json=json;
        this.formtoken=formtoken;
        this.isChecked=isChecked;
    }

    public void setId(int id){this.id=id;}
    public int getId(){return id;}

    public void setUserid(String userId){this.userid=userId;}
    public String getUserid(){return userid;}

    public void setName(String name){this.name=name;}
    public String getName(){return name;}

    public void setTime(String time){this.time=time;}
    public String getTime(){return time;}

    public void setJson(String json){this.json=json;}
    public String getJson(){return json;}

    public void setFormtoken(String formToken){this.formtoken=formToken;}
    public String getFormtoken(){return formtoken;}

    public void setIsChecked(int isChecked){this.isChecked=isChecked;}
    public int getIsChecked(){return isChecked;}

}
