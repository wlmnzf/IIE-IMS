package cn.iie.icm.pojo;

public class FormPojo {
    private int id;
    private String userId;
    private String json;
    private String formToken;

    public FormPojo() {
        super();
    }

    public FormPojo(int id, String userId, String json, String formToken) {
        super();
        this.id = id;
        this.userId = userId;
        this.json = json;
        this.formToken = formToken;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUserId() { return userId; }
    public void  setUserId(String userId) {this.userId=userId;}

    public String getJson() { return json; }
    public void  setJson(String json){this.json=json;}

    public String getFormToken() { return formToken; }
    public void  setFormToken(String formToken){this.formToken=formToken;}


}
