package cn.iie.icm.pojo;

/**
 * Created by Liu Tengyu on 2017/5/14.
 * 用于表示句法分析的节点
 */
public class Node_leader {
    private String name;
    private float weight;
    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
