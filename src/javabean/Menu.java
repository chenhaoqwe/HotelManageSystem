package javabean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBConnection;

public class Menu {
    private int id;
    private String name;
    private String info;

    public Menu() {
    }

    public Menu(int id, String name, String info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }

    public int getId() {
        return id;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
            String sql = "insert into menu(name,info) values(?,?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.name);
            ps.setString(2, this.info);
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

    public Menu getMenuById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Menu menu = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "select * from menu where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                menu = new Menu();
                menu.setId(rs.getInt("id"));
                menu.setName(rs.getString("name"));
                menu.setInfo(rs.getString("info"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.println(menu.name);
        return menu;
    }

    public List<Menu> listAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Menu> all = new ArrayList<Menu>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select * from menu";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Menu menu = new Menu();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String info = rs.getString("info");
                menu.setId(id);
                menu.setName(name);
                menu.setInfo(info);
                all.add(menu);
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
            String sql = "update menu set name=?,info=?where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, this.name);
            ps.setString(2, this.info);
            ps.setInt(3, this.id);

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
            String sql = "delete from menu where id=?";
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
