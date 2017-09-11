package cn.iie.icm.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mysql {

        //获取数据库连接
        public static Connection getConnection(){
            Connection conn=null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn=DriverManager.getConnection("jdbc:mysql://db.csuncle.com:3306/db_ims?useUnicode=true&characterEncoding=utf8", "root", "iie.ac.cn");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return conn;
        }

        //关闭所有资源
        public static void closeAll(ResultSet rs,Statement stmt,Connection conn){
            try {
                if(rs!=null)
                    rs.close();
                if(stmt!=null)
                    stmt.close();
                if(conn!=null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

}
