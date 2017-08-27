package cn.iie.icm.pojo;

/**
 * Created by Liu Tengyu on 2017/5/14.
 * 用于表示句法分析的边关系
 */
public class Node_relation {
    private String relation;
    private String sourcenode;
    private String targetnode;
    public String getRelation() {
        return relation;
    }
    public void setRelation(String relation) {
        this.relation = relation;
    }
    public String getSourcenode() {
        return sourcenode;
    }
    public String getTargetnode() {
        return targetnode;
    }
    public void setSourcenode(String sourcenode) {
        this.sourcenode = sourcenode;
    }
    public void setTargetnode(String targetnode) {
        this.targetnode = targetnode;
    }
}
