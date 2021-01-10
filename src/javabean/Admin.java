package javabean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import util.DBConnection;

public class Admin {
    private int id;
    private String name;
    private String password;
    private int age;

    public int getId() {
        return id;
    }

    public Admin(int id, String name, String password, int age) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public Admin() {
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
            String sql = "insert into admin(name,password,age) values(?,?,?)";
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

    public Admin getAdminById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Admin admin = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "select * from admin where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setName(rs.getString("name"));
                admin.setPassword(rs.getString("password"));
                admin.setAge(rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.println(admin.name);
        return admin;
    }

    public List<Admin> listAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Admin> all = new ArrayList<Admin>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select * from admin";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Admin admin = new Admin();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int age = rs.getInt("age");
                admin.setId(id);
                admin.setName(name);
                admin.setPassword(password);
                admin.setAge(age);
                all.add(admin);
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
            String sql = "update admin set name=?,password=?,age=? where id=?";
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
            String sql = "delete from admin where id=?";
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
