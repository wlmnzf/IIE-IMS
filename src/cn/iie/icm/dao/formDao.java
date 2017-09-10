package cn.iie.icm.dao;

import cn.iie.icm.jdbc.mysql;
import cn.iie.icm.pojo.FormPojo;
import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;


/*
 * StudentDAO接口，定义学生相关的操作
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

}

public class formDao implements formDaoInterface{

    Connection conn=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;

    @Override
    public void addForm(FormPojo form) {
        String sql="insert into tforms values (null,?,?,?)";
        try {
            conn= mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, form.getUserId());
            pstmt.setString(2, form.getJson());
            pstmt.setString(3, form.getFormToken());
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
        String sql="update tforms set userId=?,Json=? where formToken=?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, form.getUserId());
            pstmt.setString(2, form.getJson());
            pstmt.setString(3, form.getFormToken());
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
        String sql="select * from forms";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                FormPojo form=new FormPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
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
        FormPojo stu=null;
        String sql="select * from tforms where formToken=?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, formToken);
            rs=pstmt.executeQuery();
            if(rs.next()){
                stu=new FormPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
        return stu;
    }



}


