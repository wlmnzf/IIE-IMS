package cn.iie.icm.pojo;

public class TypePojo {
    private int id;
    private String typeName;
    private int typeCode;
    private String typeClass;



    public TypePojo() {
        super();
    }

    public TypePojo(int id, String typeName, int typeCode, String typeClass) {
        super();
        this.id = id;
        this.typeName = typeName;
        this.typeCode = typeCode;
        this.typeClass = typeClass;

    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTypeName() { return typeName; }
    public void  setTypeName(String typeName) {this.typeName=typeName;}

    public int getTypeCode() { return typeCode; }
    public void  setTyepCode(int typeCode){this.typeCode=typeCode;}

    public String getTypeClass() { return typeClass; }
    public void  setTypeClass(String typeClass){this.typeClass=typeClass;}


}
