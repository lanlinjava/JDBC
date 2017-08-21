package cn.mwxu16;

import cn.JDBCUtils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TANRAN on 2017/8/21.
 */

/**
 * 将数据表中查询出来的数据封装到对象中，并把对象存储到List集合中
 */

public class TestJDBC {
    public static void main(String[] args) throws SQLException {
        //获取数据库连接驱动
        Connection con = JDBCUtils.getConnection();
        //定义sql语句
        String sql = "SELECT * FROM user";
        //创建数据库执行者对象
        PreparedStatement pst = con.prepareStatement(sql);
        //创建结果集对象，接收查询结果
        ResultSet rs = pst.executeQuery();
        //创建List集合对象，将查询结果封装到对象中
        List<user> list = new ArrayList<user>();
        //遍历结果集，取出查询结果，封装到对象中，并将对象存储到list集合中
        while(rs.next()){
            //创建user对象，并将查询结果作为参数传递给构造方法
            user u = new user(rs.getInt("id"),rs.getString("username"),rs.getString("password"));
            //将user对象存储到list集合中
            list.add(u);
        }
        //调用JDBCUtils类的方法，关闭资源
        JDBCUtils.close(con,pst,rs);
        //使用增强for遍历list集合，输出对象
        for(user u :list){
            System.out.println(u);
        }

    }

}
