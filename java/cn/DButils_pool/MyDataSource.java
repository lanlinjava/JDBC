package cn.DButils_pool;

import cn.JDBCUtils.DButils;

import javax.sql.DataSource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Created by TANRAN on 2017/8/22.
 */

/**
 * 创建一个类实现DataSource接口
 *
 */
public class MyDataSource  implements DataSource{
    //1、创建一个容器，来存储Connection对象。（也就是所谓的连接池）
    //由于连接池需要频繁的放入、取出Connection对象，因此采用LinkedList集合来存储。
    private static LinkedList<Connection> pool = new LinkedList<Connection>();
    //2、初始化5个connection对象，放到连接池中
    static {
        for(int i=0;i<5;i++){
            //通过调用之前编写的DButils类的方法，来获取Connection对象
            Connection con  = DButils.getConnection();
            //将获取到的对象放入连接池
            pool.add(con);
        }
    }
    //3、获取连接（重写getConnection方法）
    public    Connection   getConnection() throws SQLException {
        Connection con = null;
        //返回Connection对象之前先判断连接池中是否还有Connection对象
        if(pool.size()==0){
            //如果连接池中没有Connection对象了，就再创建5个放进去
            for(int i=0;i<5;i++){
                //通过调用之前编写的DButils类的方法，来获取Connection对象
                con  = DButils.getConnection();
                //将获取到的对象放入连接池
                pool.add(con);
            }
        }
        //调用pool集合的remove方法，该方法会从集合中移除一个元素，并将该元素返回
        con = pool.remove(0);
        return con;
    }
    //4、归还Connection对象
    public void backConnection(Connection con){
        pool.add(con);
    }

    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    public void setLoginTimeout(int seconds) throws SQLException {

    }

    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
