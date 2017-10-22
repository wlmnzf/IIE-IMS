package cn.iie.icm.dao;

import cn.iie.icm.jdbc.mysql;
import cn.iie.icm.pojo.PersonPojo;
import cn.iie.icm.pojo.TypePojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

interface personDaoInterface
{
    //添加
    public void addPerson(PersonPojo person);

    //删除
    public void deletePerson(String num);

    //修改
    public void updatePerson( PersonPojo person);

    public PersonPojo getPerson(String num);

    public void updateToken(String num,String Token);

//    //查询所有
//    public List<PersonPojo> getAllType();
//

}
public class PersonDao  implements personDaoInterface {
    Connection conn=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;

    @Override
    public void addPerson(PersonPojo person)
    {
        String sql="insert into tperson values (null,?,?,?,?,?,null,?,?,?)";
        try {
            conn= mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, person.getName());
            pstmt.setInt(2, person.getTgroup_id());
            pstmt.setInt(3, person.getTpersonType_id());
            pstmt.setString(4,person.getPassword());
            pstmt.setString(5, person.getNum());
            pstmt.setInt(6, person.getAuth());
            pstmt.setString(7, person.getHeadUrl());
            pstmt.setInt(8, person.getSex());
            pstmt.executeUpdate();
            System.out.println("添加类别成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
    }

    //删除
    @Override
    public void deletePerson(String num)
    {
        String sql="delete from tperson where num=?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, num);
            pstmt.executeUpdate();
            System.out.println("删除成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
    }

    //修改
    @Override
    public void updatePerson( PersonPojo person)
    {
        String sql="update tperson set password=?,headurl=? where num=?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, person.getPassword());
            pstmt.setString(2, person.getHeadUrl());
            pstmt.setString(3, person.getNum());

            pstmt.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
    }

    @Override
    public PersonPojo getPerson(String num)
    {
        PersonPojo person=null;
        String sql="select * from tperson where num=?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, num);
            rs=pstmt.executeQuery();
            if(rs.next()){
                person=new PersonPojo(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getInt(10));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
        return person;
    }

    @Override
    public void updateToken(String num,String Token)
    {
        String sql="update tperson set token=?  where num=?";
        try {
            conn=mysql.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, Token);
            pstmt.setString(2, num);
            pstmt.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            mysql.closeAll(rs, pstmt, conn);
        }
    }
}
