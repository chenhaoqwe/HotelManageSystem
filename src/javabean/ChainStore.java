package javabean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Statement;
import util.DBConnection;

public class ChainStore {
    private int id;// 分店编号
    private String name;// 分店名称
    private String location;// 分店位置
    private int roomcount;// 分店房间数量

    public ChainStore() {
    }

    public ChainStore(int id, String name, String location, int roomcount) {
        super();
        this.id = id;
        this.name = name;
        this.location = location;
        this.roomcount = roomcount;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRoomcount() {
        return roomcount;
    }

    public void setRoomcount(int roomcount) {
        this.roomcount = roomcount;
    }

    /**
     * 插入一条记录
     * 
     * @return 生成的ID
     */
    public int insert() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            // 获得连接对象
            conn = DBConnection.getConnection();
            // 创建执行sql语句的对象
            String sql = "insert into chainStore(name,location,roomcount) values(?,?,?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.name);
            ps.setString(2, this.location);
            ps.setInt(3, this.roomcount);
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

    /**
     * 通过ID查询一条记录
     * 
     * @param id
     * @return 对应的对象
     */
    public ChainStore getChainStoreById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ChainStore chainStore = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "select * from chainStore where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                chainStore = new ChainStore();
                chainStore.setId(rs.getInt("id"));
                chainStore.setName(rs.getString("name"));
                chainStore.setLocation(rs.getString("location"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.println(chainStore.name);
        return chainStore;
    }

    /**
     * 获取所有记录
     * 
     * @return 一个列表
     */
    public List<ChainStore> listAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ChainStore> all = new ArrayList<ChainStore>();
        try {
            conn = DBConnection.getConnection();
            String sql = "select * from chainStore";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChainStore chainStore = new ChainStore();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                int roomcount = rs.getInt("roomcount");
                chainStore.setId(id);
                chainStore.setName(name);
                chainStore.setLocation(location);
                chainStore.setRoomcount(roomcount);
                all.add(chainStore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }

    /**
     * 更新一条记录
     * 
     * @return 影响的行数
     */
    public int update() {

        Connection conn = null;
        PreparedStatement ps = null;
        int changedLines = -1;
        try {
            // 获得连接对象
            conn = DBConnection.getConnection();
            // 创建执行sql语句的对象
            String sql = "update chainStore set name=?,location=?,roomcount=? where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, this.name);
            ps.setString(2, this.location);
            ps.setInt(3, this.roomcount);
            ps.setInt(4, this.id);

            changedLines = ps.executeUpdate();

            DBConnection.close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return changedLines;
    }

    /**
     * 删除一条记录
     * 
     * @return 影响的行数
     */
    public int delete() {

        Connection conn = null;
        PreparedStatement ps = null;
        int changedLines = -1;
        try {
            // 获得连接对象
            conn = DBConnection.getConnection();
            // 创建执行sql语句的对象
            String sql = "delete from chainStore where id=?";
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
