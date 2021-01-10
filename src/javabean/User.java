package javabean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import util.DBConnection;

public class User {
    private int id;
    private String name;
    private String password;
    private int age;

    public int getId() {
        return id;
    }

    public User(int id, String name, String password, int age) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public User() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int insert() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            // 获得连接对象
            conn = DBConnection.getConnection();
            // 创建执行sql语句的对象
            String sql = "insert into user(name,password,age) values(?,?,?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.name);
            ps.setString(2, this.password);
            ps.setInt(3, this.age);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBConnection.close(ps);
        return id;

    }

    public User getUserById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "select * from user where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setAge(rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.println(user.name);
        return user;
    }

    public List<User> listAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> all = new ArrayList<User>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select * from user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int age = rs.getInt("age");
                user.setId(id);
                user.setName(name);
                user.setPassword(password);
                user.setAge(age);
                all.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }

    public int update() {

        Connection conn = null;
        PreparedStatement ps = null;
        int changedLines = -1;
        try {
            // 获得连接对象
            conn = DBConnection.getConnection();
            // 创建执行sql语句的对象
            String sql = "update user set name=?,password=?,age=? where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, this.name);
            ps.setString(2, this.password);
            ps.setInt(3, this.age);
            ps.setInt(4, this.id);

            changedLines = ps.executeUpdate();

            DBConnection.close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return changedLines;
    }

    public int delete() {

        Connection conn = null;
        PreparedStatement ps = null;
        int changedLines = -1;
        try {
            // 获得连接对象
            conn = DBConnection.getConnection();
            // 创建执行sql语句的对象
            String sql = "delete from user where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, this.id);

            changedLines = ps.executeUpdate();

            DBConnection.close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return changedLines;
    }
}
