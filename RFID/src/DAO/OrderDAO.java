/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Khoa;
import DTO.Order;
import DTO.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class OrderDAO {
    public static Connection conn=Connect.getConnect();
    public static PreparedStatement ps=null;
    public static ResultSet rs=null;
    public static void them(Order m){
        
        String sql="insert into db_order(order_id, order_date, status)"+
                "values(?,?,?)";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1, m.getOrder_id());
            ps.setString(2, m.getOrder_date());
            ps.setInt(3, m.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi thông tin!!");
                    e.printStackTrace();
        }
    }
    public static void sua(Order m, String dk){
        String sql="update db_order set status='"+m.getStatus()+"'where order_id='"+dk+"'";
        try {
            ps=conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi");
        }
    }
    public static void xoa(Order ID){
        String sql="delete from db_order where order_id='"+ID.getOrder_id()+"'";
        try {
            ps=conn.prepareStatement(sql);           
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi");
        }
    }
    public List getListMH(){
        List<Order> dsmh=new ArrayList<>();
        String sql="select * from db_order";
        try {
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Order tv=new Order();
                tv.setOrder_id(rs.getString("order_id"));
                tv.setOrder_date(rs.getString("order_date"));
                tv.setStatus(rs.getInt("status"));
                dsmh.add(tv);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc");
        }
        return dsmh;    
    }
    public static boolean CheckPrimaryKey(String txt){
        String sql="Select * from ThongTinNhanVien where maNV='"+txt+"'";
        try {
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

}
