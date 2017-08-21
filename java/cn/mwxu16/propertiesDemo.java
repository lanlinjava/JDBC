package cn.mwxu16;

/**
 * Created by TANRAN on 2017/8/21.
 */

import java.io.InputStream;

/**
 * 加载properties配置文件
 * IO读取文件，键值对存储到集合
 * 从集合中以键值对方式获取数据库的连接信息，完成数据库的连接
 */
public class propertiesDemo {
    public static void main(String[] args) {
        //使用类加载器，加载配置文件
        InputStream in = propertiesDemo.class.getClassLoader().getResourceAsStream("database" +
            ".properties");

    }
}
