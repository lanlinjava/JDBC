package cn.mwxu16;
/**
 * Created by TANRAN on 2017/8/21.
 */
/**
 * 自定义一个类，来存储数据库的查询结果
 * 注意：
 * 类的属性及属性的类型要与数据库表一致
 */
public class user {
    private int id;     //对应数据库表的id属性
    private String username;        //对应数据库表的username属性
    private String password;        //对应数据库表的password属性
    public user() {}        //空参构造器
    //含参构造器（主要使用的就是这个构造器，将查询到的结果传递进来）
    public user(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    //下面是每个属性的set和get方法
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    //重写toString方法，以便于输出显示
    @Override
    public String toString() {
        return "user{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
