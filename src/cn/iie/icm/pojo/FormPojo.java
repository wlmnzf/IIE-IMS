package cn.iie.icm.pojo;

public class FormPojo {
    private int id;
    private String userId;
    private String json;
    private String formToken;
    private String time;
    private int type;
    private String title;


    public FormPojo() {
        super();
    }

    public FormPojo(int id, String userId, String json, String formToken,String time,int type,String title) {
        super();
        this.id = id;
        this.userId = userId;
        this.json = json;
        this.formToken = formToken;
        this.time = time;
        this.type = type;
        this.title=title;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUserId() { return userId; }
    public void  setUserId(String userId) {this.userId=userId;}

    public String getJson() { return json; }
    public void  setJson(String json){this.json=json;}

    public String getFormToken() { return formToken; }
    public void  setFormToken(String formToken){this.formToken=formToken;}

    public String getTime() { return time; }
    public void  setTime(String time){this.time=time;}

    public int getType() { return type; }
    public void  setType(int type){this.type=type;}

    public String getTitle() { return title; }
    public void  setTitle(String title){this.title=title;}


}
