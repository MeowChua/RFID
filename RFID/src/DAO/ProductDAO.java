/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DTO.Khoa;
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
public class ProductDAO {
    public static Connection conn=Connect.getConnect();
    public static PreparedStatement ps=null;
    public static ResultSet rs=null;
    public static void them(Product sv){
        String sql="insert into product(product_id, product_name, quanity, detail)"+
                "values(?,?,?,?)";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1, sv.getProduct_id());
            ps.setString(2, sv.getProduct_name());
            ps.setInt(3, sv.getQuanity());
            ps.setString(4, sv.getDetail());
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi thông tin!!");
        }
    }
    public static void sua(Product sv, String dk){
        String sql="update product set product_name='"+sv.getProduct_name()+"',quanity='"+sv.getQuanity()+"', detail='"+sv.getDetail()+"'where product_id='"+dk+"'";
        try {
            ps=conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi");
        }
    }
    public static void xoa(Product ID){
        String sql="delete from product where product_id='"+ID.getProduct_id()+"'";
        try {
            ps=conn.prepareStatement(sql);           
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi");
        }
    }
    public List getListProduct(){
        List<Product> dssv=new ArrayList<>();
        String sql="select * from product";
        try {
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Product tv=new Product();
                tv.setProduct_id(rs.getString("product_id"));
                tv.setProduct_name(rs.getString("product_name"));
                tv.setQuanity(rs.getInt("quanity"));
                tv.setDetail(rs.getString("detail"));
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
    public List<Khoa> fill_cbb(){
        List<Khoa> kList = new ArrayList<>();
        try {
        String sql = "select * from khoa";
        ps = conn.prepareStatement(sql);
        rs=ps.executeQuery();
            while(rs.next()){
                Khoa k = new Khoa(rs.getString("MAKHOA"));
                kList.add(k);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi");
        }
        return kList;
    }
    
}
