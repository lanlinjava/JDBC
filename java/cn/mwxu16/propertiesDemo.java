package cn.mwxu16;

/**
 * Created by TANRAN on 2017/8/21.
 */

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 加载properties配置文件
 * IO读取文件，键值对存储到集合
 * 从集合中以键值对方式获取数据库的连接信息，完成数据库的连接
 */
public class propertiesDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        //使用类加载器，加载配置文件,得到一个InputStream输入流对象
        InputStream in = propertiesDemo.class.getClassLoader().getResourceAsStream("database.properties");
        //properties是属性集，详情参照JDK帮助文档
        Properties pro = new Properties();
        pro.load(in);

        System.out.println(pro);
        //获取集合中的键值对
        String driverClass = pro.getProperty("driverClass");
        String url = pro.getProperty("url");
        String username = pro.getProperty("username");
        String password = pro.getProperty("password");
        System.out.println(driverClass+"..."+url+"..."+username+"..."+password);
        //注册驱动
        Class.forName(driverClass);
        //获取数据库连接
        Connection con = DriverManager.getConnection(url,username,password);
        System.out.println(con);



    }
}
