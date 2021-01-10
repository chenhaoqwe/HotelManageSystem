package javabean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import util.DBConnection;

public class Hotel {
    private int id;
    private String name;
    private String state;

    public Hotel() {
    }

    public Hotel(int id, String name, String state) {
        this.id = id;
        this.name = name;
        this.state = state;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
            String sql = "insert into hotel(name,state) values(?,?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.name);
            ps.setString(2, this.state);
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

    public Hotel getHotelById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Hotel hotel = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "select * from hotel where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setState(rs.getString("state"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.println(hotel.name);
        return hotel;
    }

    public List<Hotel> listAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Hotel> all = new ArrayList<Hotel>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select * from hotel";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Hotel hotel = new Hotel();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String state = rs.getString("state");
                hotel.setId(id);
                hotel.setName(name);
                hotel.setState(state);
                all.add(hotel);
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
            String sql = "update hotel set name=?,state=?where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, this.name);
            ps.setString(2, this.state);
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
            String sql = "delete from hotel where id=?";
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
