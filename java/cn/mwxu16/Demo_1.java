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
        //注册驱动、连接数据库
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql:///mydatabase?characterEncoding=utf-8";
        String username = "root";
        String password = "root";
        Connection con = DriverManager.getConnection(url,username,password);

        //查询操作
        String sql_1 = "SELECT * FROM user WHERE id=?";
        //预编译的方式执行SQL语句（防注入攻击）
        PreparedStatement ps_1 = con.prepareStatement(sql_1);
        int s_1 = 1;
        ps_1.setObject(1,s_1);
        ResultSet rs = ps_1.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString("username"));
        }

        //更新操作
        String sql_2 = "update user  SET username=?,password=? WHERE id = 1";
        PreparedStatement ps_2 = con.prepareStatement(sql_2);
        String s_2_1 = "张三";
        String s_2_2 = "987654";
        ps_2.setObject(1,s_2_1);
        ps_2.setObject(2,s_2_2);
        int i = ps_2.executeUpdate();
        System.out.println("共"+i+"条记录受影响");
    }
}
