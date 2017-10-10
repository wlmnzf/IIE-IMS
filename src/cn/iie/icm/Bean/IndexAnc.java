package cn.iie.icm.Bean;

import java.util.List;

public class IndexAnc {
    private List<AnnounceMent> list;
    private int i1;
    private int i2;
    private int i3;
    private int i4;

    public IndexAnc(List<AnnounceMent> list,int i1,int i2,int i3,int i4){
        this.list = list;
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;
        this.i4 = i4;
    }
    public List<AnnounceMent> getList() {
        return list;
    }

    public void setList(List<AnnounceMent> list) {
        this.list = list;
    }

    public int getI1() {
        return i1;
    }

    public void setI1(int i1) {
        this.i1 = i1;
    }

    public int getI2() {
        return i2;
    }

    public void setI2(int i2) {
        this.i2 = i2;
    }

    public int getI3() {
        return i3;
    }

    public void setI3(int i3) {
        this.i3 = i3;
    }

    public int getI4() {
        return i4;
    }

    public void setI4(int i4) {
        this.i4 = i4;
    }
}
