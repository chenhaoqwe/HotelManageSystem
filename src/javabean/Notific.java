package javabean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBConnection;

public class Notific {
    private int id;
    private String newscontent;
    private String writer;

    public Notific() {
    }

    public Notific(int id, String newscontent, String writer) {
        this.id = id;
        this.newscontent = newscontent;
        this.writer = writer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewscontent() {
        return newscontent;
    }

    public void setNewscontent(String newscontent) {
        this.newscontent = newscontent;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
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
            String sql = "insert into notific(newscontent,writer) values(?,?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.newscontent);
            ps.setString(2, this.writer);
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

    public Notific getNotificById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Notific notific = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "select * from notific where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                notific = new Notific();
                notific.setId(rs.getInt("id"));
                notific.setNewscontent(rs.getString("newscontent"));
                notific.setWriter(rs.getString("writer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.println(notific.newscontent);
        return notific;
    }

    public List<Notific> listAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Notific> all = new ArrayList<Notific>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select * from notific";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Notific notific = new Notific();
                int id = rs.getInt("id");
                String newscontent = rs.getString("newscontent");
                String writer = rs.getString("writer");
                notific.setId(id);
                notific.setNewscontent(newscontent);
                notific.setWriter(writer);
                all.add(notific);
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
            String sql = "update notific set newscontent=?,writer=?where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, this.newscontent);
            ps.setString(2, this.writer);
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
            String sql = "delete from notific where id=?";
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
