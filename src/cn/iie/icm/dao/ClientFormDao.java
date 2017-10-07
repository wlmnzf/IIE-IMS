package cn.iie.icm.dao;

import cn.iie.icm.jdbc.mysql;
import cn.iie.icm.pojo.ClientFormPojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

interface ClientFormDaoInterface {

    //添加
    public void saveFormRes(ClientFormPojo form);

    //删除
    public void deleteFormRes(String formToken,String userId);

    //修改
    public void updateFormRes(ClientFormPojo form);

    //查询所有
    public List<ClientFormPojo> getAllFormRes();


    public ClientFormPojo getFormResById(String formToken,String userid);

       public List<ClientFormPojo> getFormsResWithLimit(int from,int cnt,String formToken);

    public int getTotal();

    public void updateIsChecked(String userId,String formToken,int state);

    public void clearAllChecked(String formToken);



}

public class ClientFormDao implements ClientFormDaoInterface {
    Connection conn=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;

    private ClientFormPojo getClientFormPojo()
    {
        try {
            return new ClientFormPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
        }
        catch(SQLException e)
        {
            e.printStackTrace();;
        }
        return null;
    }
    @Override
    public void saveFormRes(ClientFormPojo form) {
        String sql="insert into tformsRes values (null,?,?,?,?,?,0)";
        try {
            conn= mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, form.getUserid());
            pstmt.setString(2, form.getName());
            pstmt.setString(3, form.getTime());
            pstmt.setString(4,form.getJson());
            pstmt.setString(5,form.getFormtoken());
            pstmt.executeUpdate();
            System.out.println("添加表单成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
    }

    @Override
    public void deleteFormRes(String formToken,String userId) {
        String sql="delete from tformsRes where formtoken=? and userid=?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setObject(1, formToken);
            pstmt.setObject(2, userId);
            pstmt.executeUpdate();
            System.out.println("删除成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
    }

    @Override
    public void updateFormRes(ClientFormPojo form) {
        String sql="update tformsRes set time=?,json=? where formtoken=? and userid=?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, form.getTime());
            pstmt.setString(2, form.getJson());
            pstmt.setString(3,form.getFormtoken());
            pstmt.setString(4,form.getUserid());
            pstmt.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
    }

    @Override
    public List<ClientFormPojo> getAllFormRes() {
        List<ClientFormPojo> forms=new ArrayList<ClientFormPojo>();
        String sql="select * from tformsRes";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                ClientFormPojo form=getClientFormPojo();
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
    public ClientFormPojo getFormResById(String formToken,String userid) {
        ClientFormPojo form=null;
        String sql="select * from tformsRes where formtoken=? and userid=?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, formToken);
            pstmt.setString(2, userid);
            rs=pstmt.executeQuery();
            if(rs.next()){
                form=getClientFormPojo();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
        return form;
    }

    @Override
    public List<ClientFormPojo> getFormsResWithLimit(int from,int cnt,String formToken) {
        List<ClientFormPojo> forms=new ArrayList<ClientFormPojo>();
        String sql="select * from tformsRes where formtoken=? order by time desc limit ?,?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, formToken);
            pstmt.setInt(2, (from-1)*cnt);
            pstmt.setInt(3, cnt);
            rs=pstmt.executeQuery();
            while(rs.next()){
                ClientFormPojo  form=getClientFormPojo();
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
        String sql="select count(1) from tformsRes";
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
    public void updateIsChecked(String userId, String formToken, int state) {
        String sql="update tformsRes set isChecked=? where formtoken=? and userid=?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,state);
            pstmt.setString(2,formToken);
            pstmt.setString(3,userId);
            pstmt.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
    }

    @Override
    public void clearAllChecked(String formToken) {
        String sql="update tformsRes set isChecked=0 where formtoken=?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,formToken);
            pstmt.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
    }




}
