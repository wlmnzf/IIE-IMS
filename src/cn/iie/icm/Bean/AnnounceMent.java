package cn.iie.icm.Bean;

//此类作为公告类
public class AnnounceMent {
    private String title;
    private String text;

    public AnnounceMent(){
        this.title = null;
        this.text = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



}
