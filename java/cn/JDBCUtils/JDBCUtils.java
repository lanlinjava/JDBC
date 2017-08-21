package cn.JDBCUtils;

/**
 * Created by TANRAN on 2017/8/21.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 实现JDBC工具类的封装
 * 定义静态方法，直接返回数据库连接对象
 */
public class JDBCUtils {
    //私有化默认的空参构造方法，禁止创建对象
    private JDBCUtils(){};
    //创建一个私有静态的数据库连接对象
    private static Connection con;
    //定义静态代码块，注册驱动，获得数据库连接对象
    // 静态代码块不能抛异常，只能try...catch
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");         //注册驱动
            //定义url（复用代码时注意修改这里）
            String url = "jdbc:mysql:///mydatabase?characterEncoding=utf-8";
            String username = "root";       //数据库的用户名
            String password = "root";       //数据库的密码
            //获取数据库连接的对象
            con = DriverManager.getConnection(url, username, password);
        }catch (Exception ex){
            throw new RuntimeException(ex + "数据库连接失败");
        }
    }
    /**
     * 定义静态方法，返回数据库连接对象
     */
    public static Connection getConnection(){
        return con;
    }

    /**
     * 定义静态方法，关闭资源，此方法为close（）方法的重载形式。关闭ResultSet，因为查询语句有结果集
     */
    public static void close(Connection con,Statement st,ResultSet rs){
        //关闭ResultSet
        if(rs!=null){
            try {
                rs.close();
            }catch (SQLException ex){

            }
        }
        //关闭Statement
        if(st!=null){
            try {
                st.close();
            }catch (SQLException ex){

            }
        }
        //关闭Connection
        if(con!=null){
            try {
                con.close();
            }catch (SQLException ex){

            }
        }

    }
    /**
     * 定义静态方法，关闭资源，此方法为close（）方法的重载形式。不需要关闭ResultSet对象，因为update操作没有结果集
     */
    public static void close(Connection con,Statement st){
        //关闭Statement
        if(st!=null){
            try {
                st.close();
            }catch (SQLException ex){

            }
        }
        //关闭Connection
        if(con!=null){
            try {
                con.close();
            }catch (SQLException ex){

            }
        }

    }
}
