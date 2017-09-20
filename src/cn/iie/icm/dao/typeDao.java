package cn.iie.icm.dao;

import cn.iie.icm.jdbc.mysql;
import cn.iie.icm.pojo.TypePojo;

import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

interface typeDaoInterface
{
    //添加
    public void addType(TypePojo type);

    //删除
    public void deleteType(int typeCode);

    //修改
    public void updateType(TypePojo type);

    //查询所有
    public List<TypePojo> getAllType();


}
public class typeDao implements typeDaoInterface {

    Connection conn=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;

    @Override
    public void addType(TypePojo type) {
        String sql="insert into tforms values (null,?,?,?)";
        try {
            conn= mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, type.getTypeName());
            pstmt.setInt(2, type.getTypeCode());
            pstmt.setString(3, type.getTypeClass());
            pstmt.executeUpdate();
            System.out.println("添加类别成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
    }

    @Override
    public void deleteType(int typeCode) {
        String sql="delete from tformsTypr where typeCode=?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, typeCode);
            pstmt.executeUpdate();
            System.out.println("删除成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
    }

    @Override
    public void updateType(TypePojo type) {
        String sql="update tformsType set typeName=?,typeCode=?,typeClass=?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, type.getTypeName());
            pstmt.setInt(2, type.getTypeCode());
            pstmt.setString(3,type.getTypeClass());
            pstmt.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
    }

    @Override
    public List<TypePojo> getAllType() {
        List<TypePojo> types=new ArrayList<TypePojo>();
        String sql="select * from tformsType";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                TypePojo type=new TypePojo(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
                types.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
        return types;
    }

}
