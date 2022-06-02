/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.OrderdetailDAO.conn;
import static DAO.OrderdetailDAO.ps;
import static DAO.OrderdetailDAO.rs;
import DTO.Khoa;
import DTO.Order;
import DTO.Product;
import DTO.Tag;
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
public class TagDAO {
    public static Connection conn=Connect.getConnect();
    public static PreparedStatement ps=null;
    public static ResultSet rs=null;
    public static void them(Tag sv){
        String sql="insert into tag_rfid(tag_id, product_id, date, gate)"+
                "values(?,?,?,?)";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1, sv.getTag_id());
            ps.setString(2, sv.getProduct_id()); 
            ps.setString(3, sv.getDate());
            ps.setString(4, sv.getGate());
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi thông tin!!");
        }
    }
    public static void sua(Tag sv, String dk){
        String sql="update tag_rfid set product_id='"+sv.getProduct_id()+"'where tag_id='"+dk+"'";
        try {
            ps=conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi");
        }
    }
    public static void xoa(Tag ID){
        String sql="delete from tag_rfid where tag_id='"+ID.getTag_id()+"'";
        try {
            ps=conn.prepareStatement(sql);           
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi");
        }
    }
    public List getListTag(){
        List<Tag> dssv=new ArrayList<>();
        String sql="select * from tag_rfid";
        try {
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Tag tv=new Tag();
                tv.setTag_id(rs.getString("tag_id"));
                tv.setProduct_id(rs.getString("product_id"));
                tv.setDate(rs.getString("date"));
                tv.setGate(rs.getString("gate"));
                dssv.add(tv);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc");
        }
        return dssv;    
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
    public List<Tag> request(String tf){
        List<Tag> dsp=new ArrayList<>();
        String sql="select * from tag_rfid where tag_id = '"+tf+"'";
        try {
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Tag tag=new Tag();
                tag.setProduct_id(rs.getString("product_id"));          
                dsp.add(tag);
            }
        } catch (SQLException e) {
        }
        return dsp;
    }
    public String query_product_id(String tag_id){   
        List<String> a=new ArrayList<>();
        try {
            String sql="select * from tag_rfid where tag_id='"+tag_id+"'";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                String name=rs.getString("product_id");
                a.add(name);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Khong tim thay");
        }
        return a.get(0).toString();
    }
}
