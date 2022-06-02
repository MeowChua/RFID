/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DTO.Orderdetail;
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
public class OrderdetailDAO {
    public static Connection conn=Connect.getConnect();
    public static PreparedStatement ps=null;
    public static ResultSet rs=null;
    public static void them(Orderdetail sv){
        String sql="insert into orderdetail(order_id, product_id, quanity)"+
                "values(?,?,?)";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1, sv.getOrder_id());
            ps.setString(2, sv.getProduct_id());
            ps.setInt(3, sv.getQuanity());
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi thông tin!!");
            e.printStackTrace();
        }
    }
    public static void sua(Orderdetail sv, String dk, String dk2){
        String sql="update orderdetail set quanity='"+sv.getQuanity()+"'where order_id='"+dk+"'and product_id='"+dk2+"'";
        try {
            ps=conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi");
        }
    }
    public static void xoa(Orderdetail ID){
        String sql="delete from orderdetail where order_id='"+ID.getOrder_id()+"'";
        try {
            ps=conn.prepareStatement(sql);           
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi");
        }
    }
    public List getListD(){
        List<Orderdetail> dsd=new ArrayList<>();
        String sql="select * from orderdetail";
        try {
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
               Orderdetail tv=new Orderdetail();
                tv.setOrder_id(rs.getString("order_id"));
                tv.setProduct_id(rs.getString("product_id"));
                tv.setQuanity(rs.getInt("quanity"));
                dsd.add(tv);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc list");
        }
        return dsd;    
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
    public List<Order> fill_cbb_order(){
        List<Order> mList = new ArrayList<>();
        try {
        String sql = "select * from db_order";
        ps = conn.prepareStatement(sql);
        rs=ps.executeQuery();
            while(rs.next()){
                Order k = new Order(rs.getString("order_id"));
                mList.add(k);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi doc danh sach order");
        }
        return mList;
    }
    public List<Product> fill_cbb_product(){
        List<Product> mList = new ArrayList<>();
        try {
        String sql = "select * from product";
        ps = conn.prepareStatement(sql);
        rs=ps.executeQuery();
            while(rs.next()){
                Product k = new Product(rs.getString("product_id"));
                mList.add(k);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi doc danh sach product");
        }
        return mList;
    }
    public String query_product_name(String product_id){   
        List<String> a=new ArrayList<>();
        try {
            String sql="select * from product, orderdetail where product.product_id='"+product_id+"'";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                String name=rs.getString("product_name");
                a.add(name);
            }
        } catch (Exception e) {
        }
        return a.get(0);
    }
    public int query_quanity(String id){   
        List<Integer> a=new ArrayList<>();
        try {
            String sql="select * from orderdetail where product_id='"+id+"'";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                int quanity=rs.getInt("quanity");
                a.add(quanity);
            }
        } catch (Exception e) {
        }
        return a.get(0);
    }
}
