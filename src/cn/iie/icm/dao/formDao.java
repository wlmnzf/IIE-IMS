package cn.iie.icm.dao;

import cn.iie.icm.jdbc.mysql;
import cn.iie.icm.pojo.FormPojo;
import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;


/*
 * FormDAO接口，定义相关的操作
 */
 interface formDaoInterface {

    //添加
    public void addForm(FormPojo form);

    //删除
    public void deleteForm(String formToken);

    //修改
    public void updateForm(FormPojo form);

    //查询所有
    public List<FormPojo> getAllForm();


    public FormPojo getFormById(String formToken);

    public List<FormPojo> getFormsWithLimit(int from,int cnt);

    public int getTotal();

    //获取用户未完成
    public List<FormPojo> getFormsEmpty(String userId,int from,int cnt);

    public int getEmptyTotal(String userid);

    //修改
    public void updateCheck(String formToken,int flag,String json);
}

public class formDao implements formDaoInterface{

    Connection conn=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;

    private FormPojo getFormPojo()
    {
        try {
            return new FormPojo(rs.getInt(1), rs.getString(2),  rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10));
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addForm(FormPojo form) {
        String sql="insert into tforms values (null,?,?,?,?,?,?,?,0,null)";
        try {
            conn= mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, form.getUserId());
            pstmt.setString(2, form.getJson());
            pstmt.setString(3, form.getFormToken());
            pstmt.setString(4,form.getTime());
            pstmt.setInt(5,form.getType());
            pstmt.setString(6,form.getTitle());
            pstmt.setString(7,form.getDeadline());
            pstmt.executeUpdate();
            System.out.println("添加表单成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
    }

    @Override
    public void deleteForm(String formToken) {
        String sql="delete from tforms where formToken=?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setObject(1, formToken);
            pstmt.executeUpdate();
            System.out.println("删除成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
    }

    @Override
    public void updateForm(FormPojo form) {
        String sql="update tforms set userId=?,Json=?,time=?,type=?,title=? where formToken=?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, form.getUserId());
            pstmt.setString(2, form.getJson());
            pstmt.setString(3,form.getTime());
            pstmt.setInt(4,form.getType());
            pstmt.setString(5,form.getTitle());
            pstmt.setString(6, form.getFormToken());
            pstmt.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
    }

    @Override
    public List<FormPojo> getAllForm() {
        List<FormPojo> forms=new ArrayList<FormPojo>();
        String sql="select * from tforms";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                FormPojo form=getFormPojo();
                forms.add(form);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
           mysql.closeAll(rs, pstmt, conn);
        }
        return forms;
    }

    @Override
    public FormPojo getFormById(String formToken) {
        FormPojo form=null;
        String sql="select * from tforms where formToken=?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, formToken);
            rs=pstmt.executeQuery();
            if(rs.next()){
               form=getFormPojo();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
        return form;
    }

    @Override
    public List<FormPojo> getFormsWithLimit(int from,int cnt) {
        List<FormPojo> forms=new ArrayList<FormPojo>();
        String sql="select * from tforms order by time desc limit ?,?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, (from-1)*cnt);
            pstmt.setInt(2, cnt);
            rs=pstmt.executeQuery();
            while(rs.next()){
              FormPojo  form=getFormPojo();
              forms.add(form);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
        return forms;
    }

    @Override
    public int getTotal() {
        int cnt=0;
        String sql="select count(1) from tforms";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            if(rs.next()){
                cnt=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
        return cnt;
    }


    @Override
    public List<FormPojo> getFormsEmpty(String userId,int from,int cnt) {
        List<FormPojo> forms=new ArrayList<FormPojo>();
        String sql=" SELECT\n" +
                "        *" +
                "        FROM" +
                "                tforms" +
                "        WHERE" +
                "        formToken NOT IN (select formToken from tformsRes where userId= ?)  order by time desc limit ?,?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setInt(2, (from-1)*cnt);
            pstmt.setInt(3, cnt);
            rs=pstmt.executeQuery();

            while(rs.next()){
                FormPojo   form=getFormPojo();
                forms.add(form);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
        return forms;

    }


    @Override
    public int getEmptyTotal(String userId) {
        int cnt=0;
        String sql="select count(1) from tforms where formToken NOT IN (select formToken from tformsRes where userId= ?)";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs=pstmt.executeQuery();
            if(rs.next()){
                cnt=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
        return cnt;
    }


    @Override
    public void updateCheck(String formToken,int flag,String json) {
        String sql = "update tforms set needCheck=?,checkOption=?  where formToken=?";
        try {
            conn = mysql.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, flag);
            pstmt.setString(2, json);
            pstmt.setString(3, formToken);

            pstmt.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysql.closeAll(rs, pstmt, conn);
        }
    }





}


