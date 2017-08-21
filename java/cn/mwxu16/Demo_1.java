package cn.mwxu16;

/**
 * Created by TANRAN on 2017/8/21.
 */

import java.sql.*;

/**
 * 演示SQL语句的预编译
 */
public class Demo_1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql:///mydatabase";
        String username = "root";
        String password = "root";
        Connection con = DriverManager.getConnection(url,username,password);
        String sql = "SELECT * FROM user WHERE username=?";
        //预编译的方式执行SQL语句（防注入攻击）
        PreparedStatement ps = con.prepareStatement(sql);
        String s = "kang";
        ps.setObject(1,s);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString("username"));
        }

    }
}
