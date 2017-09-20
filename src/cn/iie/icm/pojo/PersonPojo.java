package cn.iie.icm.pojo;

public class PersonPojo {
    private int id;
    private String  name;
    private int tgroup_id;
    private int tpersonType_id;
    private String password;
    private String num;
    private String token;
    private String auth;

    public PersonPojo(){super();}

    public PersonPojo(int id,String name,int tgroup_id,int tgroupType_id,String password,String num,String token,String auth)
    {
        this.id=id;
        this.name=name;
        this.tgroup_id=tgroup_id;
        this.tpersonType_id=tgroupType_id;
        this.password=password;
        this.num=num;
        this.token=token;
        this.auth=auth;
    }


    public int  getId(){return id;};
    public void setId(int id){this.id=id;}

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public  int getTgroup_id(){return tgroup_id;}
    public  void setTgroup_id(int tgroup_id){this.tgroup_id=tgroup_id;}

    public int getTpersonType_id(){return tpersonType_id;}
    public void setTpersonType_id(int tpersonType_id){this.tpersonType_id=tpersonType_id;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}

    public String getNum(){return num;}
    public void setNum(String num){this.num=num;}

    public String getToken(){return token;}
    public void setToken(String token){this.token=token;}

    public String getAuth(){return auth;}
    public void setAuth(String auth){this.auth=auth;}


}
