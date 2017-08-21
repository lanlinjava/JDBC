package cn.JDBCUtils;

/**
 * Created by TANRAN on 2017/8/21.
 */
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 编写一个JDBC的工具类，该类通过读取配置文件来获取数据库连接所需的参数，并且进行数据库连接。
 其他类只需要调用这个工具类的静态方法，即可获取到数据库连接的对象。
 */
public class DButils {
    //私有化默认的空参构造方法，禁止创建对象
    private DButils(){};
    //创建一个私有静态的数据库连接对象
    private static Connection con;
    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;
    //定义静态代码块，注册驱动，获得数据库连接对象
    // 静态代码块不能抛异常，只能try...catch
    static {
        try {
            //读取配置文件
            readConfig();
            //注册驱动
            Class.forName(driverClass);
            //获取数据库连接
            con = DriverManager.getConnection(url,username,password);
        }catch (Exception ex){
            throw new RuntimeException(ex + "数据库连接失败");
        }
    }
    //定义静态私有方法，读取配置文件
    private static void readConfig() throws IOException {
        //使用类加载器，加载配置文件,得到一个InputStream输入流对象
        InputStream in = DButils.class.getClassLoader().getResourceAsStream("database.properties");
        //properties是属性集，详情参照JDK帮助文档
        Properties pro = new Properties();
        pro.load(in);
        //获取集合中的键值对
        driverClass = pro.getProperty("driverClass");
        url = pro.getProperty("url");
        username = pro.getProperty("username");
        password = pro.getProperty("password");
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
