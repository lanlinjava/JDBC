package cn.Test_pool;

/**
 * Created by TANRAN on 2017/8/22.
 */

import cn.DButils_pool.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 测试连接池
 */
public class Test_pool {
    public static void main(String[] args) throws SQLException {
        //创建连接池对象
        MyDataSource md = new MyDataSource();
        //从连接池中获取一个连接
        Connection con = md.getConnection();
        //定义一个sql语句
        String sql = "Select * from user";
        //创建一个数据库执行者对象
        PreparedStatement pst = con.prepareStatement(sql);
        //定义结果集，接收查询结果
        ResultSet rs = pst.executeQuery();
        //遍历结果集
        while(rs.next()){

            System.out.println(rs.getInt("id")+"..."+rs.getString("username")+"..."+rs.getString("password"));
        }
        //归还Connection对象，注意，资源没有释放
        md.backConnection(con);
    }
}
