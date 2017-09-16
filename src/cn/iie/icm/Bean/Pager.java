package cn.iie.icm.Bean;


import java.util.List;

public class Pager{
   // private int pageSize;                   //每页显示的记录的条数
    private int currentPage;                //当前页
    private int totalPage;                  //总页数
    private int totalRecord;                //总记录条数
    private List<AnnounceMent> datalist;    //数据集合

    public Pager(List<AnnounceMent> datalist,int currentPage){
        this.totalRecord = datalist.size();
        this.currentPage = currentPage;
        this.totalPage = this.totalRecord / 10;
        if(this.totalRecord % 10 != 0){
            this.totalPage += 1;
        }
        int frontIndex = 10 * (this.currentPage - 1);
        int endIndex = (10 * this.currentPage > this.totalRecord) ? this.totalRecord : 10 * this.currentPage;

        this.datalist = datalist.subList(frontIndex,endIndex);
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<AnnounceMent> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<AnnounceMent> datalist) {
        this.datalist = datalist;
    }



}
